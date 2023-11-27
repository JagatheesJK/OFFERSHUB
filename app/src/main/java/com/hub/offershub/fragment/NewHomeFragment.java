package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentNewHomeBinding;

public class NewHomeFragment extends Fragment {

    private FragmentNewHomeBinding binding;

    public static NewHomeFragment newInstance() {
        NewHomeFragment fragment = new NewHomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}