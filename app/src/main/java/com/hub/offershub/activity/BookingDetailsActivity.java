package com.hub.offershub.activity;

import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;

import com.bumptech.glide.Glide;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityBookingDetailsBinding;
import com.hub.offershub.fragment.ActiveOfferFragment;
import com.hub.offershub.model.BookModel;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class BookingDetailsActivity extends BaseActivity {

    private ActivityBookingDetailsBinding binding;
    BookModel.Data model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        model = getIntent().getParcelableExtra("booking_model");
        getMutableOrderDetails_shopsVisitData();
        getMutableOrderDetails_MobileNumber_ViewData();
        getMutableOrderDetails_shopConfirmStatusData();
        if(model != null) {
            if(0== model.is_shop_read) {
                Map<String, Object> requestData = new HashMap<>();
                requestData.put("shop_id", model.shop_id);
                requestData.put("order_id", model.id);
                commonViewModel.orderDetails_shopsVisit(requestData, myProgressDialog);
            }
            binding.offerNameTxt.setText(model.offer_name);
            binding.offerDescTxt.setText(model.offer_desc);
           // Glide.with(BookingDetailsActivity.this).load().into(binding.offerImg);
            binding.userNameTxt.setText(model.name);
            binding.productNameTxt.setText(model.offer_name);
            if(1 == model.is_mobilenumber_viewed ) {
                binding.userMobileTxt.setText(""+model.fullmobile);
                binding.mobileEye.setVisibility(View.GONE);
            } else {
                binding.userMobileTxt.setText(""+model.mobile);
                binding.mobileEye.setVisibility(View.VISIBLE);
            }
            binding.dateTxt.setText(model.user_ordered_date);

            if ("Pending".equals(model.userstatus)) {

                binding.linearRejAccept.setVisibility(View.VISIBLE);
                binding.statusShowTV.setVisibility(View.GONE);
            } else if ("Canceled".equals(model.userstatus)) {

                binding.linearRejAccept.setVisibility(View.GONE);
                binding.statusShowTV.setVisibility(View.VISIBLE);
                binding.statusShowTV.setText(""+model.userstatus);
                binding.statusShowTV.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        getResources(), R.color.red, null)));
            } else {
                binding.linearRejAccept.setVisibility(View.GONE);
                binding.statusShowTV.setVisibility(View.VISIBLE);
                binding.statusShowTV.setText(""+model.userstatus);
                binding.statusShowTV.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                        getResources(), R.color.green, null)));
            }


        }
        binding.backarrow.setOnClickListener(v->{
            finish();
        });

        binding.mobileEye.setOnClickListener(v->{
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("shop_id", model.shop_id);
            requestData.put("order_id", model.id);
            commonViewModel.orderDetails_mobilenumber_View(requestData, myProgressDialog);
        });

        binding.rejectBtn.setOnClickListener(v->{
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("shop_id", model.shop_id);
            requestData.put("order_id", model.id);
            requestData.put("is_accepted", 0);

            commonViewModel.OrderDetails_shopConfirmStatusData(requestData, myProgressDialog);
        });

        binding.acceptBtn.setOnClickListener(v->{
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("shop_id", model.shop_id);
            requestData.put("order_id", model.id);
            requestData.put("is_accepted", 1);

            commonViewModel.OrderDetails_shopConfirmStatusData(requestData, myProgressDialog);
        });

    }

    private void getMutableOrderDetails_shopsVisitData() {
        commonViewModel.getMutableOrderDetails_shopsVisitData().observe(this, jsonObject -> {
            if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getString("status").equals("success")) {

                        } else {

                        }
                        //Toast.makeText(BookingDetailsActivity.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void getMutableOrderDetails_MobileNumber_ViewData() {
        commonViewModel.getMutableOrderDetails_MobileNumber_ViewData().observe(this, jsonObject -> {
            if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getString("status").equals("success")) {
                            binding.mobileEye.setVisibility(View.GONE);
                            binding.userMobileTxt.setText(""+model.fullmobile);

                        } else {

                        }
                        //Toast.makeText(BookingDetailsActivity.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    private void getMutableOrderDetails_shopConfirmStatusData() {
        commonViewModel.getMutableOrderDetails_shopConfirmStatusData().observe(this, jsonObject -> {
            if (getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getString("status").equals("success")) {


                        } else {

                        }
                        //Toast.makeText(BookingDetailsActivity.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.title.setText("Booking Details");
    }
}