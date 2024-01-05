package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.adapter.BookingAdaper;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentBookingListBinding;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingListFragment extends BaseFragment {

    private FragmentBookingListBinding binding;
    private List<BookModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private BookingAdaper adapter;
    private int page_no = 0;
    private static String shopID;

    public static BookingListFragment newInstance(String shop_id) {
        BookingListFragment fragment = new BookingListFragment();
        shopID = shop_id;
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
        getBookingData();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getOrderDetailsShops(makeRequest(), myProgressDialog);
        });

        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getOrderDetailsShops(makeRequest(), myProgressDialog);
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

    private void getBookingData() {
        commonViewModel.getMutableBookingData().observe(getViewLifecycleOwner(), bookModel -> {
            if (BookingListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (bookModel != null) {
                    if ("success".equals(bookModel.status)) {
                        if (bookModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.bookingRecycler.setVisibility(View.VISIBLE);
                            list.addAll(bookModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.bookingRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.bookingRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        return requestData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableBookingData().removeObservers(getViewLifecycleOwner());
        }
    }
}