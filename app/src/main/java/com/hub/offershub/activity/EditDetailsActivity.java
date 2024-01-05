package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayoutMediator;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityEditDetailsBinding;
import com.hub.offershub.fragment.EditBasicFragment;
import com.hub.offershub.fragment.EditImageFragment;
import com.hub.offershub.fragment.EditLocationFragment;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;

import java.util.ArrayList;
import java.util.List;

public class EditDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ActivityEditDetailsBinding binding;
    private List<String> labelData = new ArrayList<>();
    private BusinessModel.Data shopData;
    private OfferModel.Data offerData;
    private boolean isShop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();
    }

    private void init() {
        shopData = getIntent().getParcelableExtra("shop_model");
        offerData = getIntent().getParcelableExtra("offer_model");
        isShop = getIntent().getBooleanExtra("isShop", false);
        binding.toolbarLayout.toolbarTitle.setText("Edit Business");
        binding.pager.setAdapter(new ViewPagerFragmentAdapter(EditDetailsActivity.this));
        labelData.add(0, "Basic");
        labelData.add(1, "Image");
        if (isShop)
            labelData.add(2, "Location");

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labelData.get(position));
        }).attach();

        binding.pager.setCurrentItem(0, false);
    }

    private void setListener() {
        binding.toolbarLayout.toolbarBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbarBack:
                finish();
                break;
            default:
                break;
        }
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
                    return EditBasicFragment.newInstance(shopData, offerData, isShop);
                case 1:
                    return EditImageFragment.newInstance(shopData, offerData, isShop);
                case 2:
                    return EditLocationFragment.newInstance(shopData, offerData, isShop);
            }
            return EditBasicFragment.newInstance(shopData, offerData, isShop);
        }

        @Override
        public int getItemCount() {
            return labelData.size();
        }
    }
}