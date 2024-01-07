package com.hub.offershub.fragment;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;

import com.hub.offershub.R;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentEditBasicBinding;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.Utils;
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
                binding.upiEd.setText(""+ ((businessModel.upi_details == null || businessModel.upi_details.equals("null") || businessModel.upi_details.isEmpty()) ? "" : businessModel.upi_details));
                binding.shopAddressEd.setText(""+businessModel.address1);
                binding.shopAddress2Ed.setText(""+ ((businessModel.address2 == null || businessModel.address2.equals("null") || businessModel.address2.isEmpty()) ? "" : businessModel.address2));
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
                binding.offerPriceEd.setText("" + ((offerModel.amount == null || offerModel.amount.equals("null") || offerModel.amount.isEmpty()) ? "" : offerModel.amount));
                binding.offerOriginalPriceEd.setText("" + ((offerModel.original_amount == null || offerModel.original_amount.equals("null") || offerModel.original_amount.isEmpty()) ? "" : offerModel.original_amount));
                binding.offerOfferPriceEd.setText("" + ((offerModel.offer_amount == null || offerModel.offer_amount.equals("null") || offerModel.offer_amount.isEmpty()) ? "" : offerModel.offer_amount));
                binding.flatPerEd.setText("" + ((offerModel.flat_percentage == null || offerModel.flat_percentage.equals("null") || offerModel.flat_percentage.isEmpty()) ? "" : offerModel.flat_percentage));
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

        binding.mobileEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that the characters within charSequence are about to be replaced with new text
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within charSequence, the text has been changed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (input.length() < 10) {
                    binding.mobileEd.setError("Enter Valid Mobile Number");
                } else if ( input.length() == 10) {
                    binding.mobileEd.setError(null);
                }
            }
        });

        binding.pincodeEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that the characters within charSequence are about to be replaced with new text
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within charSequence, the text has been changed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (input.length() < 6) {
                    binding.pincodeEd.setError("Enter Valid PinCode");
                } else if ( input.length() == 6) {
                    binding.pincodeEd.setError(null);
                }
            }
        });
    }

    boolean isShopAllFieldsChecked = false, isOfferAllFieldsChecked = false;
    private int priceAmount = 0, originalPrice = 0, offerPrice = 0, flatPer = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopSubmit:
                isShopAllFieldsChecked = checkShopAllFields();
                if (isShopAllFieldsChecked) {
                    commonViewModel.updateShopDetails(makeShopRequest(), myProgressDialog);
                }
                break;
            case R.id.offerSubmitBtn:
                isOfferAllFieldsChecked = checkOfferAllFields();
                if (binding.offerPriceEd.length() == 0)
                    priceAmount = 0;
                else
                    priceAmount = Integer.parseInt(binding.offerPriceEd.getText().toString());
                if (binding.offerOriginalPriceEd.length() == 0)
                    originalPrice = 0;
                else
                    originalPrice = Integer.parseInt(binding.offerOriginalPriceEd.getText().toString());
                if (binding.offerOfferPriceEd.length() == 0)
                    offerPrice = 0;
                else
                    offerPrice = Integer.parseInt(binding.offerOfferPriceEd.getText().toString());
                if (binding.flatPerEd.length() == 0)
                    flatPer = 0;
                else
                    flatPer = Integer.parseInt(binding.flatPerEd.getText().toString());
                if (isOfferAllFieldsChecked) {
                    commonViewModel.updateOfferDetails(makeOfferRequest(), myProgressDialog);
                }
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

    private boolean checkShopAllFields() {
        if (binding.shopNameEd.length() == 0) {
            binding.shopNameEd.setError("Input required");
            binding.shopNameEd.requestFocus();
            return false;
        } else {
            binding.shopNameEd.setError(null);
        }

        if (binding.mobileEd.length() == 0) {
            Log.e("Check_Moorthy","binding.mobileEd");
            binding.mobileEd.setError("Input required");
            binding.mobileEd.requestFocus();
            return false;
        }  else if(binding.mobileEd.length() >0 && binding.mobileEd.length() < 10 ) {
            binding.pincodeEd.setError("Enter Valid Mobile Number");
        }

        if (binding.shopAddressEd.length() == 0) {
            binding.shopAddressEd.setError("Input required");
            binding.shopAddressEd.requestFocus();
            return false;
        }

        if (binding.cityEd.length() == 0) {
            binding.cityEd.setError("Input required");
            binding.cityEd.requestFocus();
            return false;
        }

        if (binding.stateEd.length() == 0) {
            binding.stateEd.setError("Input required");
            binding.stateEd.requestFocus();
            return false;
        }

        if (binding.pincodeEd.length() == 0) {
            binding.pincodeEd.setError("Input required");
            binding.pincodeEd.requestFocus();
            return false;
        } else if(binding.pincodeEd.length() >0 && binding.pincodeEd.length() < 6 ) {
            binding.pincodeEd.setError("Enter Valid PinCode");
        }
        return true;
    }

    private boolean checkOfferAllFields() {
        if (binding.offerNameEd.length() == 0) {
            binding.offerNameEd.setError("Input required");
            binding.offerNameEd.setFocusableInTouchMode(true);
            binding.offerNameEd.requestFocus();
            return false;
        }

        if (binding.offerDescEd.length() == 0) {
            binding.offerDescEd.setError("Input required");
            binding.offerDescEd.requestFocus();
            return false;
        }

        if (binding.offerCategorySpinner.length() == 0) {
            binding.offerCategorySpinner.setError("Input required");
            getActivity().runOnUiThread(() -> {
                binding.offerCategorySpinner.requestFocus();
                binding.offerCategorySpinner.performClick();
            });
            return false;
        }

        if (binding.plainLinear.getVisibility() == View.VISIBLE && binding.offerPriceEd.length() == 0) {
            binding.offerPriceEd.setError("Input required");
            binding.offerPriceEd.requestFocus();
            return false;
        }

        if (binding.discountLinear.getVisibility() == View.VISIBLE && binding.offerOriginalPriceEd.length() == 0) {
            binding.offerOriginalPriceEd.setError("Input required");
            binding.offerOriginalPriceEd.requestFocus();
            return false;
        }

        if (binding.discountLinear.getVisibility() == View.VISIBLE && binding.offerOfferPriceEd.length() == 0) {
            binding.offerOfferPriceEd.setError("Input required");
            binding.offerOfferPriceEd.requestFocus();
            return false;
        }

        if (binding.flatPerLinear.getVisibility() == View.VISIBLE && binding.flatPerEd.length() == 0) {
            binding.flatPerEd.setError("Input required");
            binding.flatPerEd.requestFocus();
            return false;
        } else if (binding.flatPerLinear.getVisibility() == View.VISIBLE && "0".equals(binding.flatPerEd.getText().toString())) {
            binding.flatPerEd.setError("Flat % applicable between 0 and 100");
        }
        return true;
    }
}