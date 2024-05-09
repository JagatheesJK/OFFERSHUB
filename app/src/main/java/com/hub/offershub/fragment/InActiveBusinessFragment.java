package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.activity.DashActivity;
import com.hub.offershub.activity.EditDetailsActivity;
import com.hub.offershub.adapter.BusinessAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentInActiveBusinessBinding;
import com.hub.offershub.dialogfragment.PaymentDialogFragment;
import com.hub.offershub.listener.CommonListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InActiveBusinessFragment extends BaseFragment implements View.OnClickListener, CommonListener {

    private FragmentInActiveBusinessBinding binding;
    private List<BusinessModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private BusinessAdapter adapter;
    private int page_no = 0;

    public static InActiveBusinessFragment newInstance() {
        InActiveBusinessFragment fragment = new InActiveBusinessFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInActiveBusinessBinding.inflate(getLayoutInflater());
//        init();
        setListener();
        setUpRecycler();
        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getInActiveShops(makeRequest(), myProgressDialog);
        });
        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getInActiveShops(makeRequest(), myProgressDialog);
        getInActiveData();
        getDeleteData();
    }

    private void setListener() {
        binding.empty.reloadBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.businessRecycler.setLayoutManager(linearLayoutManager);
        adapter = new BusinessAdapter(getActivity(), list, this, false);
        binding.businessRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.businessRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(Object obj) {
        BusinessModel.Data model = (BusinessModel.Data) obj;
        Log.e("Check_JK", "ID : "+model.id);
        Intent i = new Intent(getActivity(), DashActivity.class);
        i.putExtra("model", model);
        startActivity(i);
    }

    @Override
    public void onItemEdited(Object obj) {
        BusinessModel.Data model = (BusinessModel.Data) obj;
        if ("Expired".equals(model.subscription_status)) {
            if (!paymentDialogFragment.isAdded())
                paymentDialogFragment.show(getChildFragmentManager(), PaymentDialogFragment.TAG);
        } else {
            OfferModel.Data offerModel = null;
            Intent i = new Intent(getActivity(), EditDetailsActivity.class);
            i.putExtra("shop_model", model);
            i.putExtra("offer_model", offerModel);
            i.putExtra("isShop", true);
            startActivity(i);
        }
    }

    BusinessModel.Data deleteModel;
    @Override
    public void onItemRemoved(Object obj) {
        deleteModel = (BusinessModel.Data) obj;
        if ("Expired".equals(deleteModel.subscription_status)) {
            if (!paymentDialogFragment.isAdded())
                paymentDialogFragment.show(getChildFragmentManager(), PaymentDialogFragment.TAG);
        } else {
            commonViewModel.deleteShopPermanent(makeDeleteRequest(deleteModel.id), myProgressDialog);
        }
    }

    private void getInActiveData() {
        commonViewModel.getMutableInActiveBusiness().observe(getViewLifecycleOwner(), businessModel -> {
            if (InActiveBusinessFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (businessModel != null) {
                    if(businessModel.status.equals("success")) {
                        if (businessModel.data != null) {
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.businessShimmerLayout.setVisibility(View.GONE);
                            binding.businessShimmerLayout.stopShimmer();
                            binding.businessRecycler.setVisibility(View.VISIBLE);
                            if (page_no == 0) {
                                if (list.size() > 0)
                                    list.clear();
                            }
                            list.addAll(businessModel.data);
                            setNotify();
                        } else {
                            binding.businessShimmerLayout.setVisibility(View.GONE);
                            binding.businessShimmerLayout.stopShimmer();
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.businessRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.businessShimmerLayout.setVisibility(View.GONE);
                        binding.businessShimmerLayout.stopShimmer();
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.businessRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void getDeleteData() {
        commonViewModel.getMutableDeleteShopPermanent().observe(getViewLifecycleOwner(), jsonObject -> {
            if (InActiveBusinessFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getString("status").equals("success")) {
                            adapter.removeData(deleteModel);
                            Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shopownerid", AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID));
        return requestData;
    }

    private Map<String, Object> makeDeleteRequest(String shopID) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        return requestData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableInActiveBusiness().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableDeleteShopPermanent().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reloadBtn:
                if (commonViewModel != null)
                    commonViewModel.getInActiveShops(makeRequest(), myProgressDialog);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (commonViewModel != null) {
            commonViewModel.getMutableInActiveBusiness().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableDeleteShopPermanent().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (list != null) {
                if (list.size() > 0)
                    list.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }
}