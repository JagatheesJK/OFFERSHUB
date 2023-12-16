package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.adapter.OfferAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentActiveOfferBinding;
import com.hub.offershub.listener.OfferListener;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveOfferFragment extends BaseFragment implements OfferListener {

    private FragmentActiveOfferBinding binding;
    private List<OfferModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private OfferAdapter adapter;
    private int page_no = 0;

    public static ActiveOfferFragment newInstance() {
        ActiveOfferFragment fragment = new ActiveOfferFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActiveOfferBinding.inflate(getLayoutInflater());

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
        commonViewModel.getActiveOffers(makeRequest());
        getActiveOffersData();
    }

    private void setListener() {

    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.offerRecycler.setLayoutManager(linearLayoutManager);
        adapter = new OfferAdapter(getActivity(), list, this);
        binding.offerRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.offerRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private void getActiveOffersData() {
        commonViewModel.getMutableActiveOffers().observe(getViewLifecycleOwner(), offerModel -> {
            if (offerModel != null) {
                if(offerModel.status.equals("success")) {
                    if (offerModel.data != null) {
                        binding.empty.emptyConstraint.setVisibility(View.GONE);
                        binding.offerRecycler.setVisibility(View.VISIBLE);
                        list.addAll(offerModel.data);
                        setNotify();
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.offerRecycler.setVisibility(View.GONE);
                    }
                } else {
                    binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                    binding.offerRecycler.setVisibility(View.GONE);
                }
            }
        });
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", 1);
        return requestData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null)
            commonViewModel.getMutableActiveOffers().removeObservers(this);
    }

    @Override
    public void onOfferSelect() {
//        loadFragment(new ShopDetailsFragment());
    }
}