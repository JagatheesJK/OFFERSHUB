package com.hub.offershub.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentPaymentBinding;

public class PaymentFragment extends BaseFragment {

    private FragmentPaymentBinding binding;

    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();
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

    }

    private void setListener() {

    }
}