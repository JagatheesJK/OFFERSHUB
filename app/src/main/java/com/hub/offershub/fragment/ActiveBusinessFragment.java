package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.activity.DashActivity;
import com.hub.offershub.adapter.BusinessAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentActiveBusinessBinding;
import com.hub.offershub.listener.CommonListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveBusinessFragment extends BaseFragment implements CommonListener {

    private FragmentActiveBusinessBinding binding;
    private List<BusinessModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private BusinessAdapter adapter;
    private int page_no = 0;

    public static ActiveBusinessFragment newInstance(String tag) {
        ActiveBusinessFragment fragment = new ActiveBusinessFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActiveBusinessBinding.inflate(getLayoutInflater());

        init();
        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getActiveShops(makeRequest());
        });

        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getActiveShops(makeRequest());
        getActiveData();
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
//        loadFragment(new OfferListFragment());
        Intent i = new Intent(getActivity(), DashActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemRemoved(Object obj) {

    }

    private void getActiveData() {
        commonViewModel.getMutableActiveBusiness().observe(getViewLifecycleOwner(), businessModel -> {
            if (businessModel != null) {
                if(businessModel.status.equals("success")) {
                    if (businessModel.data != null) {
                        if (page_no == 0)
                            list.clear();
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

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shopownerid", AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID));
        return requestData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null)
            commonViewModel.getMutableActiveBusiness().removeObservers(this);
    }
}