package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityTestMain2Binding;
import com.hub.offershub.dialogfragment.ExitDialogFragment;
import com.hub.offershub.fragment.AccountFragment;
import com.hub.offershub.fragment.HomeFragment;
import com.hub.offershub.fragment.NotifyFragment;
import com.hub.offershub.listener.ExitListener;
import com.hub.offershub.listener.PermissionListener;
import com.hub.offershub.model.PushNotifyModel;
import com.hub.offershub.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestMainActivity2 extends BaseActivity implements NavigationBarView.OnItemSelectedListener,
        PermissionListener, ExitListener {

    private ActivityTestMain2Binding binding;
    private int notifyCount = 0;
    private BadgeDrawable badgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getAllPermission(this);
        init();

        initApiClient();
    }

    private void init() {
        notifyCount = getIntent().getIntExtra("notifyCount", 0);
        Log.e("Check_JKNotify", "TestMain notifyCount : "+notifyCount);
        binding.bottomNavigationView.setOnItemSelectedListener(this);
        binding.bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);

        badgeDrawable = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_notify);
        // Set the background color of the badge
        badgeDrawable.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPromo));
        badgeDrawable.setNumber(notifyCount);
        if (notifyCount > 0) {
            badgeDrawable.setVisible(true);
        } else {
            badgeDrawable.setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.bottom_nav_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.bottom_nav_notify:
                selectedFragment = new NotifyFragment();
                break;
            case R.id.bottom_nav_account:
                selectedFragment = new AccountFragment();
                break;
            default:
                break;
        }
        loadFragment(selectedFragment);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fragmentManager.isDestroyed())
                transaction.replace(R.id.flFragment, fragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (!exitDialogFragment.isAdded()) {
            exitDialogFragment.setExitListener(this);
            exitDialogFragment.show(getSupportFragmentManager(), ExitDialogFragment.TAG);
        }
    }

    @Override
    public void onExitCancel() {

    }

    @Override
    public void onExitYes() {
        super.onBackPressed();
    }

    void initApiClient() {
        Log.e("Check_JKLocation", "TestMain initApiClient");
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.e("Check_JKLocation", "TestMain initApiClient onConnected ");
                        mapInit();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.e("Check_JKLocation", "TestMain initApiClient onConnectionSuspended : "+i);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult result) {
                        Log.e("Check_JKLocation", "TestMain initApiClient onConnectionFailed : "+result.getErrorMessage());
                        Log.i("TAG", "onConnectionFailed() connectionResult = [" + result + "]");
                    }
                })
                .build();
        mGoogleApiClient.connect();
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
            try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                if (ActivityCompat.checkSelfPermission(TestMainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(TestMainActivity2.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            } catch (ApiException e) {
                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(
                                    TestMainActivity2.this,
                                    2024);
                        } catch (IntentSender.SendIntentException exception) {
                            // Ignore the error.
                        } catch (ClassCastException exception) {
                            // Ignore, should be an impossible error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

    private int notifyValue = 0;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusNotifyTrigger(String notifyCount) {
        if (notifyCount != null && !"".equals(notifyCount))
            notifyValue = Integer.parseInt(notifyCount);
        else
            notifyValue = 0;

        badgeDrawable.setNumber(notifyValue);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(TestMainActivity2.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(TestMainActivity2.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusTrigger(PushNotifyModel pushNotifyModel) {
        Log.e("Check_JKNotify","onEventBusTrigger pushNotifyModel : "+new Gson().toJson(pushNotifyModel));
        Utils.showNotification(this, pushNotifyModel);
    }
}