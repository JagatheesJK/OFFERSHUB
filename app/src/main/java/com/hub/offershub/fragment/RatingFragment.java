package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.adapter.RatingAdaper;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentRatingBinding;
import com.hub.offershub.model.RatingModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingFragment extends BaseFragment implements View.OnClickListener {

    private FragmentRatingBinding binding;
    private List<RatingModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private RatingAdaper adapter;
    private static String shopID;
    private int page_no = 0;

    public static RatingFragment newInstance(String shop_id) {
        RatingFragment fragment = new RatingFragment();
        shopID = shop_id;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRatingBinding.inflate(getLayoutInflater());
        init();
        setListener();
        setUpRecycler();
        getRatingReviewData();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getRatingReview(makeRatingRequest());
        });
        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getRatingReview(makeRatingRequest());
    }

    private void setListener() {

    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.ratingRecycler.setLayoutManager(linearLayoutManager);
        adapter = new RatingAdaper(getActivity(), list);
        binding.ratingRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.ratingRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    private Map<String, Object> makeRatingRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        return requestData;
    }

    private void getRatingReviewData() {
        commonViewModel.getMutableRatingData().observe(getViewLifecycleOwner(), ratingModel -> {
            if (RatingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (ratingModel != null) {
                    if(ratingModel.status.equals("success")) {
                        if (ratingModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.ratingRecycler.setVisibility(View.VISIBLE);
                            list.addAll(ratingModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.ratingRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.ratingRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Rating & Review");
    }

}