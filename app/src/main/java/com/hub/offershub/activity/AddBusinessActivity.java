package com.hub.offershub.activity;

import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.adapter.AmenityAdapter;
import com.hub.offershub.adapter.ImageAdapter;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityAddBusinessBinding;
import com.hub.offershub.listener.ImageChooseListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.model.AddShopDataRequestBody;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.utils.Constants;
import com.hub.offershub.utils.Utils;
import com.hub.offershub.utils.compress.CompressImage;
import com.hub.offershub.utils.custommap.WorkaroundMapFragment;
import com.permissionx.guolindev.PermissionX;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.OnSpinnerOutsideTouchListener;
import com.skydoves.powerspinner.SpinnerAnimation;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddBusinessActivity extends BaseActivity implements View.OnClickListener, PermissionListener,
        ImageChooseListener, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private ActivityAddBusinessBinding binding;
    private List<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private GridLayoutManager gridLayoutManager;

    // TODO MAP
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker marker;
    private MarkerOptions markerOptions;
    private static final int REQUEST_CHECK_SETTINGS = 111;
    AmenityAdapter adapter;
    List<Amenity.AmenityItem> amenities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.toolbarTitle.setText("Add Business");
        binding.toolbarLayout.toolbarBack.setOnClickListener(v -> {
            finish();
        });

        binding.amenitiesrecyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // Set number of columns to 3


        adapter = new AmenityAdapter(amenities);
        binding.amenitiesrecyclerView.setAdapter(adapter);

        // Get selected amenity IDs
        //List<Integer> selectedIds = adapter.getSelectedAmenityIds();
        commonViewModel.getMasterAmenities();
        getAmenitiesData();
        getAddShopData();
        setListener();
        setMap();
        setUpRecycler();
    }

    private void setListener() {
        binding.mainLayout.setOnClickListener(this);
        binding.shopImgCard.setOnClickListener(this);
        binding.shopAddImg.setOnClickListener(this);
        binding.shopImgRecycler.setOnClickListener(this);
        binding.shopConstraint.setOnClickListener(this);
        binding.addShopSubmit.setOnClickListener(this);
        binding.categorySpinner.setSpinnerOutsideTouchListener(new OnSpinnerOutsideTouchListener() {
            @Override
            public void onSpinnerOutsideTouch(@NonNull View view, @NonNull MotionEvent motionEvent) {
                if (binding.categorySpinner.isShowing())
                    binding.categorySpinner.dismiss();
            }
        });

        binding.mobileEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that the characters within charSequence are about to be replaced with new text
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within charSequence, the text has been changed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called to notify you that somewhere within editable, the text has been changed
                String input = editable.toString();

                if (input.length() < 10) {
                    binding.mobileEd.setError("Enter Valid Mobile Number");
                } else if ( input.length() == 10) {
                    // Clear any previous validation messages or actions
                    // Example: You can clear any error messages
                    binding.mobileEd.setError(null);
                }
            }
        });

        binding.pincodeEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that the characters within charSequence are about to be replaced with new text
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within charSequence, the text has been changed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called to notify you that somewhere within editable, the text has been changed
                String input = editable.toString();

                if (input.length() < 6) {
                    binding.pincodeEd.setError("Enter Valid PinCode");
                } else if ( input.length() == 6) {
                    // Clear any previous validation messages or actions
                    // Example: You can clear any error messages
                    binding.pincodeEd.setError(null);
                }
            }
        });
    }
    Integer selectedValue;
    private void setUpRecycler() {
        gridLayoutManager = new GridLayoutManager(this, 3);
        imageAdapter = new ImageAdapter(selectedImages, AddBusinessActivity.this);
        binding.shopImgRecycler.setLayoutManager(gridLayoutManager);
        binding.shopImgRecycler.setAdapter(imageAdapter);
        setNotifyData();

        Log.e("Check_Spinner","Size "+AppApplication.getInstance().prefsHelper.getCategory().size());
        // Convert the data to a list of strings (keys)
        List<String> spinnerItems = new ArrayList<>(AppApplication.getInstance().prefsHelper.getCategory().keySet());

        binding.categorySpinner.setSpinnerPopupAnimation(SpinnerAnimation.DROPDOWN);
        binding.categorySpinner.setSpinnerPopupMaxHeight(Utils.dpToPx(AddBusinessActivity.this, 300));
        // Create an adapter and set it to the PowerSpinner
        binding.categorySpinner.setItems(spinnerItems);

        // Set an item selection listener if needed
        binding.categorySpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int position, @Nullable String item, int spinnerIndex, String t1) {
                // Handle item selection, you can get the corresponding value from the HashMap
                Map<String, Integer> categoryMap = AppApplication.getInstance().prefsHelper.getCategory();
                if (categoryMap != null) {
                    selectedValue = categoryMap.get(t1);
                    binding.categorySpinner.setError(null);
                    if (selectedValue != null) {
                        // Do something with the selected value
                        Log.e("Check_Spinner", "selectedValue " + selectedValue);
                    }
                }
            }
        });
    }

    private void setNotifyData() {
        binding.shopImgRecycler.getRecycledViewPool().clear();
        imageAdapter.notifyDataSetChanged();
    }

    private void getAmenitiesData() {
        commonViewModel.getMutableAmenity().observeForever( amenity -> {
            if (amenity != null) {
                if(amenity.getStatus().equals("success")) {
                    amenities.addAll(amenity.getData());
                    adapter.notifyDataSetChanged();
                } else {

                }
            }
        });
    }

    private void getAddShopData() {
        commonViewModel.getMutableAddShop().observeForever( jsonObject -> {
            hideProgress();
            if (jsonObject != null) {
                try {
                    if(jsonObject.getString("status").equals("success")) {
                        Toast.makeText(this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            binding.addShopSubmit.setEnabled(true);
        });
    }
    private File file;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                String path = getPath(AddBusinessActivity.this, cameraUri);
                if (path != null) {
                    file = new File(path);
                    if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                        Toast.makeText(AddBusinessActivity.this, "Please Select file below 2MB", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   // compressImage();
                    showProgress(getString(R.string.please_wait));
                    binding.shopAddImg.setVisibility(View.GONE);
                    Glide.with(AddBusinessActivity.this).load(file).listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hideProgress();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideProgress();
                            return false;
                        }
                    }).into(binding.shopImg);
                } else {
                    Toast.makeText(AddBusinessActivity.this, "path is null", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    binding.shopAddImg.setVisibility(View.GONE);
                    //binding.shopImgRecycler.setVisibility(View.VISIBLE);
                    if (data.getClipData() != null) {
                        // Multiple images selected
                        int count = data.getClipData().getItemCount();
                        int totalSize = 0;
                        if (count <= 2)
                            totalSize = count;
                        else
                            totalSize = 3;
                        for (int i = 0; i < totalSize; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            // Handle each selected image URI
//                            if (selectedImages.size() > 3)
//                                selectedImages.clear();
                            if (imageUri != null) {
                                String path = getPath(AddBusinessActivity.this, imageUri);
                                if (path != null) {
                                    file = new File(path);
                                }
                            }
                           /* selectedImages.add(imageUri);
                            setNotifyData();*/
                            if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                                Toast.makeText(AddBusinessActivity.this, "Please Select file below 2MB", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            showProgress(getString(R.string.please_wait));
                            binding.shopAddImg.setVisibility(View.GONE);
                            Glide.with(AddBusinessActivity.this).load(file).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    hideProgress();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    hideProgress();
                                    binding.shopImg.setVisibility(View.VISIBLE);
                                    return false;
                                }
                            }).into(binding.shopImg);
                        }
                    }
                    /*Uri uri = data.getData();
                    if (uri != null) {
                        String path = getPath(AddBusinessActivity.this, uri);
                        if (path != null) {
                            file = new File(path);
                            if (getImageSizeInKb(file) > Constants.MAXIMUM_FILE_SIZE) {
                                Toast.makeText(AddBusinessActivity.this, "Please Select file below 5MB", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            compressImage();
                            showProgress(getString(R.string.please_wait));
                            binding.shopAddImg.setVisibility(View.GONE);
                            Glide.with(AddBusinessActivity.this).load(file.getAbsolutePath()).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    hideProgress();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    hideProgress();
                                    return false;
                                }
                            }).into(binding.shopImg);
                        } else {
                            Toast.makeText(AddBusinessActivity.this, "path is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddBusinessActivity.this, "uri is null", Toast.LENGTH_SHORT).show();
                    }*/
                } else {
                    Toast.makeText(AddBusinessActivity.this, "data is null", Toast.LENGTH_SHORT).show();
                }
                hideProgress();
            }
        }
    }

    @Override
    public void onPermissionGranted() {
        chooseImageDialog();
    }

    @Override
    public void onPermissionDenied() {
        showPermissionDialog("In order to upload shop image, "+getResources().getString(R.string.app_name) + " " + "needs access to your Camera and Photos & videos. Go to Settings to enable");
    }

    private void compressImage() {
        try {
            boolean res = CompressImage.INSTANCE.compressCurrentBitmapFile(file);
            if (res) {
                CompressImage.INSTANCE.compressBitmap(AddBusinessActivity.this, file, it -> {
                    file = it;
                    return null;
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onImageChoose() {
        getPermission(this);
    }

    @Override
    public void onImageRemove(int size) {
        if (size == 0) {
            binding.shopAddImg.setVisibility(View.VISIBLE);
            binding.shopImgRecycler.setVisibility(View.GONE);
        }
    }

    private void setMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapImg);
        mapFragment.getMapAsync(this);
        initApiClient();

        ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapImg)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                binding.nestedView.requestDisallowInterceptTouchEvent(true);
            }
        });
    }

    private void mapInit() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(16);
        locationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());
        result.addOnCompleteListener(task -> {});

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private LatLng latLng;
    private double currentLat, currentLong;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLng midLatLng = googleMap.getCameraPosition().target;
                if (marker != null) {
                    marker.setPosition(midLatLng);
                    currentLat = marker.getPosition().latitude;
                    currentLong = marker.getPosition().longitude;
                }
            }
        });

        PermissionX.init(AddBusinessActivity.this)
                .permissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        mMap.setMyLocationEnabled(true);
                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);
                        fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddBusinessActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                if (marker != null) {
                                    marker.remove();
                                }
                                markerOptions = new MarkerOptions();
                                markerOptions.title(""+location.getLatitude()+", "+location.getLongitude());
//                                markerOptions.draggable(true);
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
                                marker = mMap.addMarker(markerOptions);
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                            }
                        });

                    } else {

                    }
                });
    }

    void initApiClient() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        mapInit();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult result) {
                        Log.i("TAG", "onConnectionFailed() connectionResult = [" + result + "]");
                    }
                })
                .build();
        mGoogleApiClient.connect();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        if (marker != null) {
            marker.remove();
        }

        markerOptions.title(""+latLng.latitude+", "+latLng.longitude);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.position(new LatLng(latLng.latitude, latLng.longitude));
        marker = mMap.addMarker(markerOptions);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

        currentLat = latLng.latitude;
        currentLong = latLng.longitude;
        Log.e("Check_Location", "Lat : "+latLng.latitude);
        Log.e("Check_Location", "Long : "+latLng.longitude);
    }

    String addressText;
    private String getLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(AddBusinessActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address address = addresses.get(0);
            addressText = address.getAddressLine(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressText;
    }

    boolean isAllFieldsChecked = false;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainLayout:
                break;
            case R.id.shopImgRecycler:
            case R.id.shopImgCard:
            case R.id.shopAddImg:
            case R.id.shopConstraint:
                getPermission(this);
                break;
            case R.id.addShopSubmit:
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    binding.addShopSubmit.setEnabled(false);
                        if(file != null) {
                            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"), file));
                            AddShopDataRequestBody addShopDataRequestBody = new AddShopDataRequestBody();
                            addShopDataRequestBody.shopownerid = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID, 0);
                            addShopDataRequestBody.shopname = "" + binding.shopNameEd.getText().toString();
                            addShopDataRequestBody.mobile = Long.parseLong(binding.mobileEd.getText().toString());
                            addShopDataRequestBody.upi = "" + binding.upiEd.getText().toString();
                            addShopDataRequestBody.shopamenities = adapter.getSelectedAmenityIds();
                            addShopDataRequestBody.address1 = "" + binding.shopAddressEd.getText().toString();
                            addShopDataRequestBody.address2 = "" + binding.shopAddress2Ed.getText().toString();
                            addShopDataRequestBody.city = "" + binding.cityEd.getText().toString();
                            addShopDataRequestBody.state = "" + binding.stateEd.getText().toString();
                            addShopDataRequestBody.pincode = "" + binding.pincodeEd.getText().toString();
                            addShopDataRequestBody.categoryid = selectedValue;
                            addShopDataRequestBody.latitude = currentLat;
                            addShopDataRequestBody.longitude = currentLong;

                            commonViewModel.getAddShop(addShopDataRequestBody,filePart);
                            showProgress("Please Wait...");
                        } else {
                            AddShopDataRequestBody addShopDataRequestBody = new AddShopDataRequestBody();
                            addShopDataRequestBody.shopownerid = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID, 0);
                            addShopDataRequestBody.shopname = "" + binding.shopNameEd.getText().toString();
                            addShopDataRequestBody.mobile = Long.parseLong(binding.mobileEd.getText().toString());
                            addShopDataRequestBody.upi = "" + binding.upiEd.getText().toString();
                            addShopDataRequestBody.shopamenities = adapter.getSelectedAmenityIds();
                            addShopDataRequestBody.address1 = "" + binding.shopAddressEd.getText().toString();
                            addShopDataRequestBody.address2 = "" + binding.shopAddress2Ed.getText().toString();
                            addShopDataRequestBody.city = "" + binding.cityEd.getText().toString();
                            addShopDataRequestBody.state = "" + binding.stateEd.getText().toString();
                            addShopDataRequestBody.pincode = "" + binding.pincodeEd.getText().toString();
                            addShopDataRequestBody.categoryid = selectedValue;
                            addShopDataRequestBody.latitude = currentLat;
                            addShopDataRequestBody.longitude = currentLong;

                            commonViewModel.getAddShop(addShopDataRequestBody,null);
                            showProgress("Please Wait...");                        }
                    }

                break;
            default:
                break;
        }
    }

    public static String getPath(Context context, Uri uri) {
        // just some safety built in
        Cursor cursor = null;
        if (uri == null) {
            return null;
        }
        // java.io.FileNotFoundException: Failed to validate: content://media/external/images/media/1000021058 -- BY SAMU(1.2.5)
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        try {
            String[] projection = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
        }catch (Exception e){
            FirebaseCrashlytics.getInstance().recordException(e);
            return null;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        // java.io.FileNotFoundException: Failed to validate: content://media/external/images/media/1000021058 -- BY SAMU(1.2.5)
        // this is our fallback here
        if(uri.isAbsolute())
            return uri.getPath();
        else
            return null;
    }

    private boolean CheckAllFields() {
        Log.e("Check_Moorthy","CheckAllFields "+binding.mobileEd.length() );
        if (binding.shopNameEd.length() == 0) {
            binding.shopNameEd.setError("Input required");
            binding.shopNameEd.requestFocus();
            return false;
        } else {
            binding.shopNameEd.setError(null);
        }
        Log.e("Check_Moorthy","binding.mobileEd.length() "+binding.mobileEd.length() );

        if (binding.mobileEd.length() == 0) {
            Log.e("Check_Moorthy","binding.mobileEd");
            binding.mobileEd.setError("Input required");
            binding.mobileEd.requestFocus();
            return false;
        }  else if(binding.mobileEd.length() >0 && binding.mobileEd.length() < 10 ) {
            binding.pincodeEd.setError("Enter Valid Mobile Number");
        }


        if (binding.categorySpinner.length() == 0) {
            binding.categorySpinner.setError("Input required");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.categorySpinner.requestFocus();
                    binding.categorySpinner.performClick();
                }
            });
            return false;
        }

        if (binding.shopAddressEd.length() == 0) {
            binding.shopAddressEd.setError("Input required");
            binding.shopAddressEd.requestFocus();
            return false;
        }

        if (binding.cityEd.length() == 0) {
            binding.cityEd.setError("Input required");
            binding.cityEd.requestFocus();
            return false;
        }

        if (binding.stateEd.length() == 0) {
            binding.stateEd.setError("Input required");
            binding.stateEd.requestFocus();
            return false;
        }

        if (binding.pincodeEd.length() == 0) {
            binding.pincodeEd.setError("Input required");
            binding.pincodeEd.requestFocus();
            return false;
        } else if(binding.pincodeEd.length() >0 && binding.pincodeEd.length() < 6 ) {
            binding.pincodeEd.setError("Enter Valid PinCode");
        }

        // after all validation return true.
        return true;
    }
}