package com.hub.offershub.activity;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;

import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityBookingDetailsBinding;
import com.hub.offershub.dialogfragment.PaymentDialogFragment;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.utils.Utils;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class BookingDetailsActivity extends BaseActivity {

    private ActivityBookingDetailsBinding binding;
    BookModel.Data model;
    private boolean isAccept = false;

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
            binding.offerNameTxt.setText(model.offer_name);
            binding.remainingNumberTxt.setText("Remaining " + (10 - model.mobilecount) + " numbers");
            commonMethods.imageLoaderView(this, binding.offerImg, model.image_url);
            if(1 == model.is_mobilenumber_viewed) {
                binding.userMobileTxt.setText(""+model.fullmobile);
                binding.mobileEye.setVisibility(View.GONE);
                binding.remainingNumberTxt.setVisibility(View.GONE);
            } else {
                binding.userMobileTxt.setText(""+model.mobile);
                binding.mobileEye.setVisibility(View.VISIBLE);
                binding.remainingNumberTxt.setVisibility(("Free".equals(model.subscription_status)) ? View.VISIBLE : View.GONE);
            }
            if (model.user_ordered_date != null && !"".equals(model.user_ordered_date))
                binding.dateTxt.setText(Utils.convertDateFormat(model.user_ordered_date));

            if (1 == model.offer_type) {
                Log.e("Check_JK", "Type : "+model.offer_type+" IF ");
                binding.amountLinear.setVisibility(View.VISIBLE);
                binding.discountLinear.setVisibility(View.GONE);
                if (model.amount != null && !"".equals(model.amount))
                    binding.offerPrice.setText("₹ "+Utils.addComma(Integer.parseInt(model.amount)));
                binding.offerPrice.setTextColor(getColor(R.color.black));
            } else if (2 == model.offer_type) {
                Log.e("Check_JK", "Type : "+model.offer_type+" ELSE IF ");
                binding.amountLinear.setVisibility(View.GONE);
                binding.discountLinear.setVisibility(View.VISIBLE);
            } else {
                Log.e("Check_JK", "Type : "+model.offer_type+" ELSE ");
                binding.amountLinear.setVisibility(View.VISIBLE);
                binding.discountLinear.setVisibility(View.GONE);
                binding.offerPrice.setText("Flat "+model.flat_percentage+"%");
                binding.offerPrice.setTextColor(getColor(R.color.green));
            }

            binding.originalAmountTxt.setPaintFlags(binding.originalAmountTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if (model.original_amount != null && !"".equals(model.original_amount))
                binding.originalAmountTxt.setText("₹ "+ Utils.addComma(Integer.parseInt(model.original_amount)));
            if (model.offer_amount != null && !"".equals(model.offer_amount))
                binding.discountPriceTxt.setText("₹ "+Utils.addComma(Integer.parseInt(model.offer_amount)));
            binding.discountOfferTxt.setText("OFF "+model.offer_percentage+"%");

            if ("0".equals(model.userorder_status)) {
                binding.linearRejAccept.setVisibility(View.GONE);
                binding.statusTxt.setText("Order Canceled");
            } else {
                if ("0".equals(model.shoporder_status)) {
                    binding.linearRejAccept.setVisibility(View.VISIBLE);
                    binding.statusTxt.setText("Pending");
                    binding.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                            getResources(), R.color.yellow, null)));
                } else if ("2".equals(model.shoporder_status)) {
                    binding.linearRejAccept.setVisibility(View.GONE);
                    binding.statusTxt.setText("Canceled");
                    binding.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                            getResources(), R.color.red, null)));
                } else {
                    binding.linearRejAccept.setVisibility(View.GONE);
                    binding.statusTxt.setText("Confirmed");
                    binding.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                            getResources(), R.color.green, null)));
                }
            }
        }

        binding.backarrow.setOnClickListener(v->{
            finish();
        });

        binding.mobileEye.setOnClickListener(v->{
            if ("Expired".equals(model.subscription_status)) {
                if (!paymentDialogFragment.isAdded())
                    paymentDialogFragment.show(getSupportFragmentManager(), PaymentDialogFragment.TAG);
            } else if ("Free".equals(model.subscription_status)) {
                if (model.mobilecount < 10) {
                    binding.remainingNumberTxt.setVisibility(View.GONE);
                    commonViewModel.orderDetails_mobilenumber_View(makeRequest(true, 0), myProgressDialog);
                } else {
                    if (!paymentDialogFragment.isAdded())
                        paymentDialogFragment.show(getSupportFragmentManager(), PaymentDialogFragment.TAG);
                }
            } else {
                commonViewModel.orderDetails_mobilenumber_View(makeRequest(true, 0), myProgressDialog);
            }
        });

        binding.rejectBtn.setOnClickListener(v->{
            if ("Expired".equals(model.subscription_status)) {
                if (!paymentDialogFragment.isAdded())
                    paymentDialogFragment.show(getSupportFragmentManager(), PaymentDialogFragment.TAG);
            } else {
                isAccept = false;
                commonViewModel.OrderDetails_shopConfirmStatusData(makeRequest(false, 2), myProgressDialog);
            }
        });

        binding.acceptBtn.setOnClickListener(v->{
            if ("Expired".equals(model.subscription_status)) {
                if (!paymentDialogFragment.isAdded())
                    paymentDialogFragment.show(getSupportFragmentManager(), PaymentDialogFragment.TAG);
            } else {
                isAccept = true;
                commonViewModel.OrderDetails_shopConfirmStatusData(makeRequest(false, 1), myProgressDialog);
            }
        });

    }

    private Map<String, Object> makeRequest(boolean isMobile, int is_accepted) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", model.shop_id);
        requestData.put("order_id", model.id);
        if (!isMobile)
            requestData.put("is_accepted", is_accepted);
        return requestData;
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
                            if (isAccept) {
                                binding.statusTxt.setText("Confirmed");
                                binding.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                                        getResources(), R.color.green, null)));
                            } else {
                                binding.statusTxt.setText("Rejected");
                                binding.statusTxt.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(
                                        getResources(), R.color.red, null)));
                            }
                            binding.linearRejAccept.setVisibility(View.GONE);
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