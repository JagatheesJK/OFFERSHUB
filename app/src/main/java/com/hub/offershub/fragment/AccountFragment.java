package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAccountBinding;

public class AccountFragment extends BaseFragment {

    private FragmentAccountBinding binding;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}