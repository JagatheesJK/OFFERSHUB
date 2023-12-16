package com.hub.offershub.activity;

import static com.hub.offershub.utils.Constants.CAMERA_REQUEST_CODE;
import static com.hub.offershub.utils.Constants.GALLERY_REQUEST_CODE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.hub.offershub.R;
import com.hub.offershub.adapter.ImageAdapter;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityAddBusinessBinding;
import com.hub.offershub.listener.ImageChooseListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.utils.Constants;
import com.hub.offershub.utils.compress.CompressImage;
import com.permissionx.guolindev.PermissionX;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.toolbarTitle.setText("AddBusiness");
        binding.toolbarLayout.toolbarBack.setOnClickListener(v -> {
            finish();
        });

        setListener();
        setMap();
        setUpRecycler();
    }

    private void setListener() {
        binding.shopImgCard.setOnClickListener(this);
        binding.shopAddImg.setOnClickListener(this);
        binding.shopImgRecycler.setOnClickListener(this);
        binding.shopConstraint.setOnClickListener(this);
    }

    private void setUpRecycler() {
        gridLayoutManager = new GridLayoutManager(this, 3);
        imageAdapter = new ImageAdapter(selectedImages, AddBusinessActivity.this);
        binding.shopImgRecycler.setLayoutManager(gridLayoutManager);
        binding.shopImgRecycler.setAdapter(imageAdapter);
        setNotifyData();
    }

    private void setNotifyData() {
        binding.shopImgRecycler.getRecycledViewPool().clear();
        imageAdapter.notifyDataSetChanged();
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
                        Toast.makeText(AddBusinessActivity.this, "Please Select file below 5MB", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    compressImage();
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
                    binding.shopImgRecycler.setVisibility(View.VISIBLE);
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
                            selectedImages.add(imageUri);
                            setNotifyData();
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
        result.addOnCompleteListener(task -> {
            /*try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                if (ActivityCompat.checkSelfPermission(AddBusinessActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AddBusinessActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddBusinessActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        if (marker != null) {
                            marker.remove();
                        }
                        markerOptions = new MarkerOptions();
                        markerOptions.title("" + location.getLatitude() + ", " + location.getLongitude());
                        markerOptions.draggable(true);
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
                        marker = mMap.addMarker(markerOptions);

//                        mMap.addMarker(new MarkerOptions().position(latLng));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                        binding.locationEd.setText(""+location.getLatitude()+", "+location.getLongitude());
                    }
                });
            } catch (ApiException e) {
                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(
                                    AddBusinessActivity.this,
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException exception) {
                            // Ignore the error.
                        } catch (ClassCastException exception) {
                            // Ignore, should be an impossible error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }*/
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        PermissionX.init(AddBusinessActivity.this)
                .permissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        mMap.setMyLocationEnabled(true);
                        fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddBusinessActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopImgRecycler:
            case R.id.shopImgCard:
            case R.id.shopAddImg:
            case R.id.shopConstraint:
                Log.e("Check_test", "shopImgCard");
                getPermission(this);
                break;
            default:
                break;
        }
    }
}