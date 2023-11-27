package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentNotifyBinding;

public class NotifyFragment extends BaseFragment {

    private FragmentNotifyBinding binding;

    public static NotifyFragment newInstance() {
        NotifyFragment fragment = new NotifyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotifyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void init() {

    }
}