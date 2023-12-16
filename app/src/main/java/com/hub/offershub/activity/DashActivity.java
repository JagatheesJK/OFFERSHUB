package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.hub.offershub.R;
import com.hub.offershub.databinding.ActivityDashBinding;
import com.hub.offershub.fragment.BookingListFragment;
import com.hub.offershub.fragment.OfferDashboardFragment;
import com.hub.offershub.fragment.OfferListFragment;
import com.hub.offershub.fragment.ShopDetailsFragment;

public class DashActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityDashBinding binding;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.navBar.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open,
                R.string.nav_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OfferListFragment()).commit();
            binding.navBar.setCheckedItem(R.id.nav_offer);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_offer:
                fragment = new OfferListFragment();
                break;
            case R.id.nav_shop_dash:
                fragment = new ShopDetailsFragment();
                break;
            case R.id.nav_offer_dash:
                fragment = new OfferDashboardFragment();
                break;
            case R.id.nav_booking_details:
                fragment = new BookingListFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}