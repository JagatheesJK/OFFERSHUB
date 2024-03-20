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
import com.hub.offershub.activity.AddOfferActivity;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentOfferListBinding;
import com.hub.offershub.dialogfragment.PaymentDialogFragment;
import com.hub.offershub.model.BusinessModel;

public class OfferListFragment extends BaseFragment {

    private FragmentOfferListBinding binding;
    private String[] labels = new String[]{"Active", "InActive"};
    private static String shopID;
    private static BusinessModel.Data model;

    public static OfferListFragment newInstance(BusinessModel.Data bussinessModel) {
        OfferListFragment fragment = new OfferListFragment();
        model = bussinessModel;
        shopID = bussinessModel.id;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOfferListBinding.inflate(getLayoutInflater());
        init();

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        binding.pager.setCurrentItem(0, false);

        binding.floatingBtn.setOnClickListener(v -> {
            if ("Expired".equals(model.subscription_status)) {
                if (!paymentDialogFragment.isAdded())
                    paymentDialogFragment.show(getChildFragmentManager(), PaymentDialogFragment.TAG);
            } else {
                Intent i = new Intent(getActivity(), AddOfferActivity.class);
                i.putExtra("shop_id", shopID);
                startActivity(i);
            }
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
                    return ActiveOfferFragment.newInstance(model);
                case 1:
                    return InActiveOfferFragment.newInstance(model);
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
        getActivity().setTitle("Offers");
    }
}