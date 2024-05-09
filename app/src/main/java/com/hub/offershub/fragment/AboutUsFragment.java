package com.hub.offershub.fragment;

import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends BaseFragment {

    private FragmentAboutUsBinding binding;

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(getLayoutInflater());
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.dearTxt.setText("Dear ");
        binding.dearNameTxt.setText(Html.fromHtml("<b>"+ AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.NAME) +",</b>"));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("About Us");
    }
}