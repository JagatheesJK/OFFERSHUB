package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityDashBinding;
import com.hub.offershub.fragment.BookingListFragment;
import com.hub.offershub.fragment.OfferDashboardFragment;
import com.hub.offershub.fragment.OfferListFragment;
import com.hub.offershub.fragment.ShopDetailsFragment;
import com.hub.offershub.model.BusinessModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityDashBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private BusinessModel.Data model;

    private CircleImageView navUserImg;
    private AppCompatTextView userNameTxt, userDesTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.navBar.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open,
                R.string.nav_close);
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShopDetailsFragment()).commit();
            binding.navBar.setCheckedItem(R.id.nav_offer);
        }
        View headerView = binding.navBar.getHeaderView(0);
        navUserImg = headerView.findViewById(R.id.userImg);
        userNameTxt = headerView.findViewById(R.id.userNameTxt);
        userDesTxt = headerView.findViewById(R.id.userDesTxt);

        init();
    }

    private void init() {
        model = getIntent().getParcelableExtra("model");

        initUI();
    }

    private void initUI() {
        if (model != null) {
            userNameTxt.setText(""+model.shop_name);
            userDesTxt.setText(""+ AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.MOBILE));
            commonMethods.imageCircle(DashActivity.this, navUserImg, model.image_url);
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
            case R.id.nav_shop_dash:
                fragment = new ShopDetailsFragment();
                break;
            case R.id.nav_home:
                Intent intent = new Intent(DashActivity.this, TestMainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_offer:
                fragment = OfferListFragment.newInstance(model.id);
                break;
            case R.id.nav_booking_details:
                fragment = new BookingListFragment();
                break;
            case R.id.nav_rating_review:
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.paymant:
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
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