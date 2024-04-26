package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.hub.offershub.fragment.FeedbackFragment;
import com.hub.offershub.fragment.OfferListFragment;
import com.hub.offershub.fragment.PaymentFragment;
import com.hub.offershub.fragment.RatingFragment;
import com.hub.offershub.fragment.ShopDashboardFragment;
import com.hub.offershub.fragment.ShopDetailsFragment;
import com.hub.offershub.model.BusinessModel;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

public class DashActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, PaymentResultWithDataListener {

    private ActivityDashBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private BusinessModel.Data model;

    private AppCompatImageView navUserImg;
    private AppCompatTextView userNameTxt, userDesTxt, subcriptionStatusTxt;

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

        View headerView = binding.navBar.getHeaderView(0);
        navUserImg = headerView.findViewById(R.id.userImg);
        userNameTxt = headerView.findViewById(R.id.userNameTxt);
        userDesTxt = headerView.findViewById(R.id.userDesTxt);
        subcriptionStatusTxt = headerView.findViewById(R.id.subcriptionStatusTxt);

        init();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ShopDashboardFragment.newInstance(model)).commit();
            binding.navBar.setCheckedItem(R.id.nav_offer);
        }
    }

    private void init() {
        model = getIntent().getParcelableExtra("model");

        initUI();
    }

    private void initUI() {
        if (model != null) {
            userNameTxt.setText(""+model.shop_name);
            userDesTxt.setText(""+ AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.MOBILE));
            subcriptionStatusTxt.setText(""+ model.subscription_status);
            if ("Free".equals(model.subscription_status)) {
                subcriptionStatusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        getResources(), R.color.yellow, null)));
                subcriptionStatusTxt.setTextColor(Color.WHITE);
            } else if ("Paid".equals(model.subscription_status)) {
                subcriptionStatusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        getResources(), R.color.green, null)));
                subcriptionStatusTxt.setTextColor(Color.WHITE);
            } else {
                subcriptionStatusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        getResources(), R.color.white, null)));
                subcriptionStatusTxt.setTextColor(Color.RED);
            }
            commonMethods.imageLoaderView(DashActivity.this, navUserImg, model.image_url);
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
            case R.id.nav_shop_dash -> fragment = ShopDashboardFragment.newInstance(model);
            case R.id.nav_home -> {
                Intent intent = new Intent(DashActivity.this, TestMainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            case R.id.nav_offer -> fragment = OfferListFragment.newInstance(model);
            case R.id.nav_booking_details -> fragment = BookingListFragment.newInstance(model);
            case R.id.nav_rating_review -> fragment = RatingFragment.newInstance(model.id);
            case R.id.nav_faq -> fragment = FeedbackFragment.newInstance(model.id);
            case R.id.paymant -> fragment = PaymentFragment.newInstance();
//            case R.id.about -> fragment = PaymentFragment.newInstance();
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

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Log.e("Check_pay ","onPaymentSuccess "+s);
        Intent intent = new Intent(DashActivity.this, TestMainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Log.e("Check_pay ","onPaymentError "+s);

    }
}