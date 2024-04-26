package com.hub.offershub.dialogfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hub.offershub.R;
import com.hub.offershub.adapter.ViewMorePlanAdapter;
import com.hub.offershub.databinding.FragmentViewMorePlanDialogBinding;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.Utils;

public class ViewMorePlanDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private FragmentViewMorePlanDialogBinding binding;
    private SubscriptionPackageResponse.SubscriptionPackage planModel;
    ViewMorePlanAdapter adapter;

    public static ViewMorePlanDialogFragment newInstance() {
        ViewMorePlanDialogFragment fragment = new ViewMorePlanDialogFragment();
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
        binding = FragmentViewMorePlanDialogBinding.inflate(getLayoutInflater());
        init();
        setListener();
        setUpRecycler();
        return binding.getRoot();
    }

    private void init() {
        binding.planNameTxt.setText(""+planModel.packageName);
        binding.planCode.setText(""+planModel.packageCode);
        binding.planAmt.setText("₹ "+ Utils.addComma(planModel.price));
        binding.planDays.setText(" / "+planModel.days+" days");

        binding.closeBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    private void setListener() {
        binding.continueBtn.setOnClickListener(this);
    }

    public void setPlanModel(SubscriptionPackageResponse.SubscriptionPackage model) {
        planModel = model;
    }

    private void setUpRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.planRecycler.setLayoutManager(linearLayoutManager);
        adapter = new ViewMorePlanAdapter(getActivity(), planModel.desc);
        binding.planRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.planRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueBtn:
                dismiss();
                break;
            default:
                break;
        }
    }
}