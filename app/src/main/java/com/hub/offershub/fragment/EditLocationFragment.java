package com.hub.offershub.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;

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
import com.google.android.gms.tasks.Task;
import com.hub.offershub.R;
import com.hub.offershub.adapter.AmenityAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentEditLocationBinding;
import com.hub.offershub.model.Amenity;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.custommap.WorkaroundMapFragment;
import com.permissionx.guolindev.PermissionX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditLocationFragment extends BaseFragment implements View.OnClickListener,
        OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private FragmentEditLocationBinding binding;
    private static BusinessModel.Data businessModel;
    private static OfferModel.Data offerModel;
    private static boolean isShop = false;

    // TODO MAP
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker marker;
    private MarkerOptions markerOptions;

    // TODO AMINITIES
    private AmenityAdapter adapter;
    private List<Amenity.AmenityItem> amenities = new ArrayList<>();

    public static EditLocationFragment newInstance(BusinessModel.Data model, OfferModel.Data offer, boolean isShopData) {
        EditLocationFragment fragment = new EditLocationFragment();
        businessModel = model;
        offerModel = offer;
        isShop = isShopData;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditLocationBinding.inflate(getLayoutInflater());
        init();
        setListener();
        setUpRecycler();
        getAmenitiesData();
        getUpdateShopAmenities();
        getUpdateShopLocation();
        return binding.getRoot();
    }

    private void init() {
        if (businessModel != null) {
            currentLat = Double.parseDouble(businessModel.latitude);
            currentLong = Double.parseDouble(businessModel.longitude);
        }
        binding.amenitiesProgress.setVisibility(View.VISIBLE);
        commonViewModel.getMasterAmenities();
        setMap();
    }

    private void setListener() {
        binding.amititiesSubmitBtn.setOnClickListener(this);
        binding.mapSubmitBtn.setOnClickListener(this);
    }

    private void setMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapImg);
        mapFragment.getMapAsync(this);
        initApiClient();

        ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mapImg)).setListener(() -> {
            binding.nestedView.requestDisallowInterceptTouchEvent(true);
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

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getActivity().getApplicationContext())
                .checkLocationSettings(builder.build());
        result.addOnCompleteListener(task -> {});

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    private void setUpRecycler() {
        binding.amenitiesrecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new AmenityAdapter(amenities);
        binding.amenitiesrecyclerView.setAdapter(adapter);
    }

    private LatLng latLng;
    private double currentLat, currentLong;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        mMap.setOnCameraMoveListener(() -> {
            LatLng midLatLng = googleMap.getCameraPosition().target;
            if (marker != null) {
                marker.setPosition(midLatLng);
                currentLat = marker.getPosition().latitude;
                currentLong = marker.getPosition().longitude;
            }
        });

        PermissionX.init(EditLocationFragment.this)
                .permissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
//                        mMap.setMyLocationEnabled(true);
                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);
                        fusedLocationProviderClient.getLastLocation().addOnFailureListener(e -> {
                            Toast.makeText(getActivity(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }).addOnSuccessListener(location ->  {
                            latLng = new LatLng(currentLat, currentLong);
                            if (marker != null) {
                                marker.remove();
                            }
                            markerOptions = new MarkerOptions();
                            markerOptions.title(""+currentLat+", "+currentLong);
//                                markerOptions.draggable(true);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                            markerOptions.position(new LatLng(currentLat, currentLong));
                            marker = mMap.addMarker(markerOptions);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                        });
                    } else {

                    }
                });
    }

    void initApiClient() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
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
                .addOnConnectionFailedListener(result -> {
                    Log.i("TAG", "onConnectionFailed() connectionResult = [" + result + "]");
                })
                .build();
        mGoogleApiClient.connect();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        if (marker != null) {
            marker.remove();
        }

        if (businessModel != null) {
            markerOptions.title(""+businessModel.latitude+", "+businessModel.longitude);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            markerOptions.position(new LatLng(Long.parseLong(businessModel.latitude), Long.parseLong(businessModel.longitude)));
            marker = mMap.addMarker(markerOptions);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

            currentLat = latLng.latitude;
            currentLong = latLng.longitude;
            Log.e("Check_Location", "Lat : "+latLng.latitude);
            Log.e("Check_Location", "Long : "+latLng.longitude);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.amititiesSubmitBtn:
                showDialog();
                commonViewModel.updateShopAmenities(businessModel.id, adapter.getSelectedAmenityIds());
                break;
            case R.id.mapSubmitBtn:
                showDialog();
                commonViewModel.updateShopLocation(makeRequest(businessModel.id));
                break;
            default:
                break;
        }
    }

    private Map<String, Object> makeRequest(String shopID) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        requestData.put("latitude", currentLat);
        requestData.put("longitude", currentLong);
        return requestData;
    }

    private void getUpdateShopAmenities() {
        commonViewModel.getMutableUpdateShopAmenitiesData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditLocationFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if ("success".equals(jsonObject.getString("status"))) {
                            getActivity().finish();
                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                closeDialog();
            }
        });
    }

    private void getUpdateShopLocation() {
        commonViewModel.getMutableUpdateShopLocationData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditLocationFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if ("success".equals(jsonObject.getString("status"))) {
                            getActivity().finish();
                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                closeDialog();
            }
        });
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
            binding.amenitiesProgress.setVisibility(View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableAmenity().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableUpdateShopAmenitiesData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableUpdateShopLocationData().removeObservers(getViewLifecycleOwner());
        }
    }
}