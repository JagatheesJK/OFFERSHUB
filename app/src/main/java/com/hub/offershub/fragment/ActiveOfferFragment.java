package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.adapter.BusinessAdapter;
import com.hub.offershub.adapter.OfferAdapter;
import com.hub.offershub.databinding.FragmentActiveOfferBinding;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.List;

public class ActiveOfferFragment extends Fragment {

    private FragmentActiveOfferBinding binding;
    private List<OfferModel> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private OfferAdapter adapter;

    public static ActiveOfferFragment newInstance() {
        ActiveOfferFragment fragment = new ActiveOfferFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActiveOfferBinding.inflate(getLayoutInflater());

        init();
        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeRefresh.setRefreshing(false);
            }
        });

        return binding.getRoot();
    }

    private void init() {
        list.add(new OfferModel("JK", "adsavssav", "Fashion", "878.00", "https://previews.123rf.com/images/denisined/denisined2009/denisined200900175/155692670-sale-15-off-discount-banner-design-template-promo-tag-special-offer-vector-illustration.jpg", true));
        list.add(new OfferModel("JK", "adsavssav", "Fashion", "388.00", "https://previews.123rf.com/images/denisined/denisined2009/denisined200900175/155692670-sale-15-off-discount-banner-design-template-promo-tag-special-offer-vector-illustration.jpg", false));
        list.add(new OfferModel("JK", "adsavssav", "Fashion", "500.00", "https://img.freepik.com/premium-vector/sale-15-off-special-offer-discount-banner-design-template_579179-1052.jpg", false));
    }

    private void setListener() {

    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.offerRecycler.setLayoutManager(linearLayoutManager);
        adapter = new OfferAdapter(getActivity(), list);
        binding.offerRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.offerRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }
}