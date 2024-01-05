package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.adapter.FeedbackAdaper;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentFeedbackBinding;
import com.hub.offershub.dialogfragment.QueryDialogFragment;
import com.hub.offershub.listener.FeedbackListener;
import com.hub.offershub.model.FeedbackModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackFragment extends BaseFragment implements View.OnClickListener, FeedbackListener {

    private FragmentFeedbackBinding binding;
    private List<FeedbackModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private FeedbackAdaper adapter;
    private static String shopID;
    private int page_no = 0;

    public static FeedbackFragment newInstance(String shop_id) {
        FeedbackFragment fragment = new FeedbackFragment();
        shopID = shop_id;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeedbackBinding.inflate(getLayoutInflater());
        init();
        setListener();
        setUpRecycler();
        getShopFeedbackData();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getShopFeedback(makeFeedbackRequest(), myProgressDialog);
        });
        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getShopFeedback(makeFeedbackRequest(), myProgressDialog);
    }

    private void setListener() {
        binding.createBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.feedbackRecycler.setLayoutManager(linearLayoutManager);
        adapter = new FeedbackAdaper(getActivity(), list);
        binding.feedbackRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.feedbackRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createBtn:
                if (!queryDialogFragment.isAdded()) {
                    queryDialogFragment.setData(shopID, this);
                    queryDialogFragment.show(getChildFragmentManager(), QueryDialogFragment.TAG);
                }
                break;
            default:
                break;
        }
    }

    private Map<String, Object> makeFeedbackRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        return requestData;
    }

    private void getShopFeedbackData() {
        commonViewModel.getMutableShopFeedbackData().observe(getViewLifecycleOwner(), feedbackModel -> {
            if (FeedbackFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (feedbackModel != null) {
                    if("success".equals(feedbackModel.status)) {
                        if (feedbackModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.feedbackRecycler.setVisibility(View.VISIBLE);
//                            adapter.addAll(ratingModel.data);
                            list.addAll(feedbackModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.feedbackRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.feedbackRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null)
            commonViewModel.getMutableShopFeedbackData().removeObservers(getViewLifecycleOwner());
    }

    @Override
    public void onFeedbackSuccess() {
        list.clear();
        page_no = 0;
        commonViewModel.getShopFeedback(makeFeedbackRequest(), myProgressDialog);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Feedback");
    }
}