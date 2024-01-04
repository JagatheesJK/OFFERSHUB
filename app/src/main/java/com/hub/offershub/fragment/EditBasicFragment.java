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
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentEditBasicBinding;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.Utils;
import com.hub.offershub.utils.custommap.WorkaroundMapFragment;
import com.permissionx.guolindev.PermissionX;
import com.skydoves.powerspinner.SpinnerAnimation;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class EditBasicFragment extends BaseFragment implements View.OnClickListener {

    private FragmentEditBasicBinding binding;
    private static BusinessModel.Data businessModel;
    private static OfferModel.Data offerModel;
    private static boolean isShop = false;
    private int selectedType;

    public static EditBasicFragment newInstance(BusinessModel.Data model, OfferModel.Data offer, boolean isShopData) {
        EditBasicFragment fragment = new EditBasicFragment();
        businessModel = model;
        offerModel = offer;
        isShop = isShopData;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditBasicBinding.inflate(getLayoutInflater());
        init();
        setListener();
        getUpdateShopData();
        getUpdateOfferData();
        return binding.getRoot();
    }

    private void init() {
        if (isShop) {
            binding.shopNestedView.setVisibility(View.VISIBLE);
            binding.offerNestedView.setVisibility(View.GONE);
            if (businessModel != null) {
                binding.shopNameEd.setText(""+businessModel.shop_name);
                binding.mobileEd.setText(""+businessModel.mobile);
                binding.upiEd.setText(""+businessModel.upi_details);
                binding.shopAddressEd.setText(""+businessModel.address1);
                binding.shopAddress2Ed.setText(""+businessModel.address2);
                binding.cityEd.setText(""+businessModel.city);
                binding.stateEd.setText(""+businessModel.state);
                binding.pincodeEd.setText(""+businessModel.pincode);
            }
        } else {
            binding.shopNestedView.setVisibility(View.GONE);
            binding.offerNestedView.setVisibility(View.VISIBLE);
            if (offerModel != null) {
                selectedType = offerModel.offer_type;
                binding.offerNameEd.setText("" + offerModel.offer_name);
                binding.offerDescEd.setText("" + offerModel.offer_desc);
                binding.offerCategorySpinner.selectItemByIndex((selectedType - 1));
                binding.offerPriceEd.setText("" + ((offerModel.amount == null || offerModel.amount.equals("null") || offerModel.amount.isEmpty()) ? "0" : offerModel.amount));
                binding.offerOriginalPriceEd.setText("" + ((offerModel.original_amount == null || offerModel.original_amount.equals("null") || offerModel.original_amount.isEmpty()) ? "0" : offerModel.original_amount));
                binding.offerOfferPriceEd.setText("" + ((offerModel.offer_amount == null || offerModel.offer_amount.equals("null") || offerModel.offer_amount.isEmpty()) ? "0" : offerModel.offer_amount));
                binding.flatPerEd.setText("" + ((offerModel.flat_percentage == null || offerModel.flat_percentage.equals("null") || offerModel.flat_percentage.isEmpty()) ? "0" : offerModel.flat_percentage));
                if (selectedType == 1)
                    binding.plainLinear.setVisibility(View.VISIBLE);
                else if (selectedType == 2)
                    binding.discountLinear.setVisibility(View.VISIBLE);
                else
                    binding.flatPerLinear.setVisibility(View.VISIBLE);

                binding.offerCategorySpinner.setSpinnerPopupAnimation(SpinnerAnimation.DROPDOWN);
                binding.offerCategorySpinner.setSpinnerPopupMaxHeight(Utils.dpToPx(getActivity(), 300));

                binding.offerCategorySpinner.setOnSpinnerItemSelectedListener((position, item, spinnerIndex, t1) -> {
                    if (spinnerIndex == 0) {
                        binding.plainLinear.setVisibility(View.VISIBLE);
                        binding.discountLinear.setVisibility(View.GONE);
                        binding.flatPerLinear.setVisibility(View.GONE);
                    } else if (spinnerIndex == 1) {
                        binding.plainLinear.setVisibility(View.GONE);
                        binding.discountLinear.setVisibility(View.VISIBLE);
                        binding.flatPerLinear.setVisibility(View.GONE);
                    } else if (spinnerIndex == 2) {
                        binding.plainLinear.setVisibility(View.GONE);
                        binding.discountLinear.setVisibility(View.GONE);
                        binding.flatPerLinear.setVisibility(View.VISIBLE);
                    }
                    selectedType = (spinnerIndex + 1);
                });
            }
        }
    }

    private void setListener() {
        binding.shopSubmit.setOnClickListener(this);
        binding.offerSubmitBtn.setOnClickListener(this);

        binding.offerCategorySpinner.setSpinnerOutsideTouchListener((view, motionEvent) -> {
            if (binding.offerCategorySpinner.isShowing())
                binding.offerCategorySpinner.dismiss();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopSubmit:
                commonViewModel.updateShopDetails(makeShopRequest());
                break;
            case R.id.offerSubmitBtn:
                commonViewModel.updateOfferDetails(makeOfferRequest());
                break;
            default:
                break;
        }
    }

    private Map<String, Object> makeShopRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", businessModel.id);
        requestData.put("shopname", binding.shopNameEd.getText().toString());
        requestData.put("mobile", binding.mobileEd.getText().toString());
        requestData.put("upi", binding.upiEd.getText().toString());
        requestData.put("address1", binding.shopAddressEd.getText().toString());
        requestData.put("address2", binding.shopAddress2Ed.getText().toString());
        requestData.put("city", binding.cityEd.getText().toString());
        requestData.put("state", binding.stateEd.getText().toString());
        requestData.put("pincode", binding.pincodeEd.getText().toString());
        return requestData;
    }

    private Map<String, Object> makeOfferRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("offer_id", offerModel.offer_id);
        requestData.put("offer_name", binding.offerNameEd.getText().toString());
        requestData.put("offer_desc", binding.offerDescEd.getText().toString());
        requestData.put("offer_type", selectedType);
        if (selectedType == 1)
            requestData.put("amount", binding.offerPriceEd.getText().toString());
        else if (selectedType == 2) {
            requestData.put("original_amount", binding.offerOriginalPriceEd.getText().toString());
            requestData.put("offer_amount", binding.offerOfferPriceEd.getText().toString());
        } else
            requestData.put("flat_percentage", binding.flatPerEd.getText().toString());
        return requestData;
    }

    private void getUpdateShopData() {
        commonViewModel.getMutableUpdateShopDetails().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditBasicFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            getActivity().finish();
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void getUpdateOfferData() {
        commonViewModel.getMutableUpdateOfferDetails().observe(getViewLifecycleOwner(), jsonObject -> {
            if (EditBasicFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if("success".equals(jsonObject.getString("status"))) {
                            getActivity().finish();
                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableUpdateShopDetails().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableUpdateOfferDetails().removeObservers(getViewLifecycleOwner());
        }
    }
}