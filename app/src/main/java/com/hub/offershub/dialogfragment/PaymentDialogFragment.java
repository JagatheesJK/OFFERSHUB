package com.hub.offershub.dialogfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hub.offershub.R;
import com.hub.offershub.activity.PaymentConfirmActivity;
import com.hub.offershub.adapter.PopupSubScriptionAdapter;
import com.hub.offershub.adapter.SubScriptionAdapter;
import com.hub.offershub.databinding.FragmentPaymentDialogBinding;
import com.hub.offershub.listener.onPlanSelectListener;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;
import com.hub.offershub.utils.loading.MyProgressDialog;
import com.hub.offershub.viewmodel.CommonViewModel;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener, onPlanSelectListener {

    public static final String TAG = PaymentDialogFragment.class.getSimpleName();
    private FragmentPaymentDialogBinding binding;
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    PopupSubScriptionAdapter adapter;
    public String mobileRandom;
    public CommonViewModel commonViewModel;
    public MyProgressDialog myProgressDialog;

    public static PaymentDialogFragment newInstance() {
        PaymentDialogFragment fragment = new PaymentDialogFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,  R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentDialogBinding.inflate(getLayoutInflater());

        init();
        setListener();

        return binding.getRoot();
    }

    private void init() {
        myProgressDialog = new MyProgressDialog(getActivity());
        commonViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(CommonViewModel.class);
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
        adapter = new PopupSubScriptionAdapter(getActivity(),this, list);
        binding.planRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.planRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private List<SubscriptionPackageResponse.SubscriptionPackage> list = new ArrayList<>();
    private void getInActiveData() {
        commonViewModel.getMutableSubscriptionData().observe(getViewLifecycleOwner(), subscriptionPackageResponse -> {
            if (PaymentDialogFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (subscriptionPackageResponse != null) {
                    Log.e("Check_JK3456435", "InActiveShopsFrag getInActiveData Status : "+subscriptionPackageResponse.status);
                    if(subscriptionPackageResponse.status.equals("success")) {
                        if (subscriptionPackageResponse.data != null) {
                            Log.e("Check_JK3456435", "InActiveShopsFrag getInActiveData");
                            // binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.planRecycler.setVisibility(View.VISIBLE);
                            list.clear();
                            list.addAll(subscriptionPackageResponse.data);
                            setNotify();
                            Log.e("Check_JK3456435", "InActiveShopsFrag getInActiveData list : "+list.size());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueBtn:
                if (adapter != null) {
                    Intent i = new Intent(getActivity(), PaymentConfirmActivity.class);
                    i.putExtra("model", adapter.getSelectedModel());
                    startActivity(i);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlanViewMore(SubscriptionPackageResponse.SubscriptionPackage model) {
        /*GenerateOrder generateOrder = new GenerateOrder(model);
        generateOrder.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "order_generation");*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableSubscriptionData().removeObservers(getViewLifecycleOwner());
        }
    }
}