package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAccountBinding;
import com.hub.offershub.utils.Utils;

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
        binding.logout.setOnClickListener(v -> {
            Utils.logout(getActivity(), AppApplication.getInstance().prefsHelper);
        });
        binding.userNameTxt.setText(""+AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID));
        return binding.getRoot();
    }
}