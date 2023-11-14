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
        list.add(new OfferModel("JK", "adsavssav", "Fashion", "878", "https://img.freepik.com/free-vector/cartoon-style-cafe-front-shop-view_134830-697.jpg?size=626&ext=jpg&ga=GA1.1.1880011253.1699574400&semt=ais", true));
        list.add(new OfferModel("JK", "adsavssav", "Fashion", "388", "https://img.freepik.com/free-vector/cartoon-style-cafe-front-shop-view_134830-697.jpg?size=626&ext=jpg&ga=GA1.1.1880011253.1699574400&semt=ais", false));
        list.add(new OfferModel("JK", "adsavssav", "Fashion", "500", "https://img.freepik.com/free-vector/cartoon-style-cafe-front-shop-view_134830-697.jpg?size=626&ext=jpg&ga=GA1.1.1880011253.1699574400&semt=ais", false));
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