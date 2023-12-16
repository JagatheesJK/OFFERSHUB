package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityTestMain2Binding;
import com.hub.offershub.fragment.AccountFragment;
import com.hub.offershub.fragment.HomeFragment;
import com.hub.offershub.fragment.NotifyFragment;
import com.hub.offershub.listener.PermissionListener;

public class TestMainActivity2 extends BaseActivity implements NavigationBarView.OnItemSelectedListener, PermissionListener {

    private ActivityTestMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getAllPermission(this);
        init();
    }

    private void init() {
        binding.bottomNavigationView.setOnItemSelectedListener(this);
        binding.bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
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
}