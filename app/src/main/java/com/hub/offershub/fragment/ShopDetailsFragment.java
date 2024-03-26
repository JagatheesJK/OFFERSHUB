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
import com.hub.offershub.databinding.FragmentShopDetailsBinding;
import com.hub.offershub.model.BusinessModel;

public class ShopDetailsFragment extends Fragment {

    private FragmentShopDetailsBinding binding;
    private String[] labels = new String[]{"Shop Dashboard", "Offer Dashboard"};
    private static BusinessModel.Data businessModel;

    public static ShopDetailsFragment newInstance(BusinessModel.Data model) {
        ShopDetailsFragment fragment = new ShopDetailsFragment();
        businessModel = model;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShopDetailsBinding.inflate(getLayoutInflater());
        init();

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        binding.pager.setCurrentItem(0, false);

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
                    return ShopDashboardFragment.newInstance(businessModel);
                case 1:
                    return new OfferDashboardFragment();
            }
            return ShopDashboardFragment.newInstance(businessModel);
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Dashboard");
    }

}