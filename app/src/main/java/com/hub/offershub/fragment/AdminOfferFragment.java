package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAdminOfferBinding;
import com.hub.offershub.utils.Constants;

public class AdminOfferFragment extends BaseFragment {

    FragmentAdminOfferBinding binding;
    private static boolean isOfferList = false;

    private String[] labels = new String[]{"Pending", "Rejected"};

    public static AdminOfferFragment newInstance(boolean isList) {
        AdminOfferFragment fragment = new AdminOfferFragment();
        isOfferList = isList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminOfferBinding.inflate(getLayoutInflater());

        init();

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        binding.pager.setCurrentItem(0, false);

        return binding.getRoot();
    }

    private void init() {
        Constants.IsTabCheck = 1;
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
                    return AdminOfferPendingFragment.newInstance(isOfferList);
                case 1:
                    return AdminOfferRejectFragment.newInstance(isOfferList);
            }
            return AdminOfferPendingFragment.newInstance(isOfferList);
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Admin Offers");
    }
}