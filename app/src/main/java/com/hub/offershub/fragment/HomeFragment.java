package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.hub.offershub.R;
import com.hub.offershub.activity.AddBusinessActivity;
import com.hub.offershub.adapter.PagerAdapter;
import com.hub.offershub.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PagerAdapter adapter;

    private String[] labels = new String[]{"Active", "In-Active"};

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        init();

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        binding.pager.setCurrentItem(0, false);

        binding.floatingBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddBusinessActivity.class));
        });

        return binding.getRoot();
    }

    private void init() {
        binding.pager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ActiveBusinessFragment();
                case 1:
                    return new InActiveBusinessFragment();
            }
            return new ActiveBusinessFragment();
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.app_name));
    }
}