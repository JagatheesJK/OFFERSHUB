package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hub.offershub.R;
import com.hub.offershub.activity.PaymentConfirmActivity;
import com.hub.offershub.adapter.SubScriptionAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentPaymentBinding;
import com.hub.offershub.dialogfragment.ViewMorePlanDialogFragment;
import com.hub.offershub.listener.onPlanSelectListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.apache.commons.lang3.RandomStringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends BaseFragment  implements View.OnClickListener, onPlanSelectListener {

    private FragmentPaymentBinding binding;
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    SubScriptionAdapter adapter;
    public String mobileRandom;
    private static BusinessModel.Data businessModel;
    private SubscriptionPackageResponse.SubscriptionPackage model;

    public static PaymentFragment newInstance(BusinessModel.Data model) {
        PaymentFragment fragment = new PaymentFragment();
        businessModel = model;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(getLayoutInflater());
        init();
        setListener();
        return binding.getRoot();
    }

    private void init() {
        mobileRandom = "6"+generateRandomNumbers(9);
        setUpRecycler();
        commonViewModel.getSubscriptionDetails(myProgressDialog);
        getInActiveData();
    }

    public String generateRandomNumbers(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    private void setListener() {
        binding.continueBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.planRecycler.setLayoutManager(linearLayoutManager);
        adapter = new SubScriptionAdapter(this, list);
        binding.planRecycler.setAdapter(adapter);
        setNotify();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continueBtn:
                if (adapter != null) {
                    Intent i = new Intent(getActivity(), PaymentConfirmActivity.class);
                    i.putExtra("model", model);
                    i.putExtra("businessModel", businessModel);
                    startActivity(i);
                }
                break;
            default:
                break;
        }
    }

    private void setNotify() {
        binding.planRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private List<SubscriptionPackageResponse.SubscriptionPackage> list = new ArrayList<>();
    private void getInActiveData() {
        commonViewModel.getMutableSubscriptionData().observe(getViewLifecycleOwner(), subscriptionPackageResponse -> {
            if (PaymentFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (subscriptionPackageResponse != null) {
                    Log.e("Check_JK", "InActiveShopsFrag getInActiveData Status : "+subscriptionPackageResponse.status);
                    if(subscriptionPackageResponse.status.equals("success")) {
                        if (subscriptionPackageResponse.data != null) {
                           // binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.planRecycler.setVisibility(View.VISIBLE);

                            list.addAll(subscriptionPackageResponse.data);
                            setNotify();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onPlanViewMore(SubscriptionPackageResponse.SubscriptionPackage model) {
        ViewMorePlanDialogFragment planDialogFragment = new ViewMorePlanDialogFragment();
        planDialogFragment.setPlanModel(model);
        planDialogFragment.show(getChildFragmentManager(), "");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Payment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusTrigger(SubscriptionPackageResponse.SubscriptionPackage subscriptionPackage) {
        model = subscriptionPackage;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(PaymentFragment.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(PaymentFragment.this);
    }
}