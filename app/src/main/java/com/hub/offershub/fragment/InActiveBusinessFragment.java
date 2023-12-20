package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.activity.DashActivity;
import com.hub.offershub.adapter.BusinessAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentInActiveBusinessBinding;
import com.hub.offershub.listener.CommonListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InActiveBusinessFragment extends BaseFragment implements CommonListener {

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
        init();
        setListener();
        setUpRecycler();
        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getInActiveShops(makeRequest());
        });
        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getInActiveShops(makeRequest());
        getInActiveData();
        getDeleteData();
    }

    private void setListener() {

    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.businessRecycler.setLayoutManager(linearLayoutManager);
        adapter = new BusinessAdapter(getActivity(), list, this);
        binding.businessRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.businessRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(Object obj) {
//        loadFragment(new ShopDetailsFragment());
        Intent i = new Intent(getActivity(), DashActivity.class);
        startActivity(i);
    }

    BusinessModel.Data deleteModel;
    @Override
    public void onItemRemoved(Object obj) {
        deleteModel = (BusinessModel.Data) obj;
        commonViewModel.getDeleteShop(makeDeleteRequest(deleteModel.id));
    }

    private void getInActiveData() {
        commonViewModel.getMutableInActiveBusiness().observe(getViewLifecycleOwner(), businessModel -> {
            if (businessModel != null) {
                if(businessModel.status.equals("success")) {
                    if (businessModel.data != null) {
                        binding.empty.emptyConstraint.setVisibility(View.GONE);
                        binding.businessRecycler.setVisibility(View.VISIBLE);
                        list.addAll(businessModel.data);
                        setNotify();
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.businessRecycler.setVisibility(View.GONE);
                    }
                } else {
                    binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                    binding.businessRecycler.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getDeleteData() {
        commonViewModel.getMutableDeleteShop().observe(getViewLifecycleOwner(), jsonObject -> {
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
            commonViewModel.getMutableInActiveBusiness().removeObservers(this);
            commonViewModel.getMutableDeleteShop().removeObservers(this);
        }
    }
}