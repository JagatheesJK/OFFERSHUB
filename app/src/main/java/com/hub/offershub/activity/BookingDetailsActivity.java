package com.hub.offershub.activity;

import android.os.Bundle;

import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityBookingDetailsBinding;

public class BookingDetailsActivity extends BaseActivity {

    private ActivityBookingDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("Booking Details");
    }
}