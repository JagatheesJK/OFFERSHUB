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
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentActiveBusinessBinding;
import com.hub.offershub.listener.CommonListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.List;

public class ActiveBusinessFragment extends BaseFragment implements CommonListener {

    private FragmentActiveBusinessBinding binding;
    private List<BusinessModel> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private BusinessAdapter adapter;

    public static ActiveBusinessFragment newInstance(String tag) {
        ActiveBusinessFragment fragment = new ActiveBusinessFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActiveBusinessBinding.inflate(getLayoutInflater());

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
        list.add(new BusinessModel("JK", "adsavssav", "Cloth"));
        list.add(new BusinessModel("JK", "adsavssav", "Cloth"));
        list.add(new BusinessModel("JK", "adsavssav", "Cloth"));
        list.add(new BusinessModel("JK", "adsavssav", "Cloth"));
    }

    private void setListener() {

    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.businessRecycler.setLayoutManager(linearLayoutManager);
        adapter = new BusinessAdapter(getActivity(), list, this);
        binding.businessRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.businessRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(Object obj) {
        loadFragment(new ShopDetailsFragment());
        /*getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.Layout_container, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();*/
    }
}