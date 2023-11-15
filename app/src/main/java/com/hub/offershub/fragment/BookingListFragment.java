package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.adapter.BookingAdaper;
import com.hub.offershub.adapter.BusinessAdapter;
import com.hub.offershub.databinding.FragmentBookingListBinding;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.List;

public class BookingListFragment extends Fragment {

    private FragmentBookingListBinding binding;
    private List<BookModel> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private BookingAdaper adapter;

    public static BookingListFragment newInstance() {
        BookingListFragment fragment = new BookingListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookingListBinding.inflate(getLayoutInflater());

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
        list.add(new BookModel("Offer1", "JK", "9898574636", "10-11-2023"));
        list.add(new BookModel("Offer2", "KJ", "9898574636", "10-11-2023"));
        list.add(new BookModel("Offer3", "DJ", "9898574636", "10-11-2023"));
        list.add(new BookModel("Offer4", "JD", "9898574636", "10-11-2023"));
    }

    private void setListener() {

    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.bookingRecycler.setLayoutManager(linearLayoutManager);
        adapter = new BookingAdaper(getActivity(), list);
        binding.bookingRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.bookingRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Booking Details");
    }
}