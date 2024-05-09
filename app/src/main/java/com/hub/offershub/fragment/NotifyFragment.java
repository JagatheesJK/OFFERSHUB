package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.activity.BookingDetailsActivity;
import com.hub.offershub.adapter.NotifyAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentNotifyBinding;
import com.hub.offershub.listener.NotifyListener;
import com.hub.offershub.model.BookModel;
import com.hub.offershub.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifyFragment extends BaseFragment implements View.OnClickListener, NotifyListener {

    private FragmentNotifyBinding binding;
    private List<BookModel.Data> list = new ArrayList<>();
    private NotifyAdapter adapter;
    private int page_no = 0;

    public static NotifyFragment newInstance() {
        NotifyFragment fragment = new NotifyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotifyBinding.inflate(getLayoutInflater());

//        init();
        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getNotify(makeRequest(), myProgressDialog);
        });
        return binding.getRoot();
    }

    private void init() {
        if (commonViewModel != null) {
            commonViewModel.getNotify(makeRequest(), myProgressDialog);
            getNotifyData();
        }
    }

    private void setListener() {
        binding.empty.reloadBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.notifyRecycler.setLayoutManager(linearLayoutManager);
        adapter = new NotifyAdapter(getActivity(), list, this);
        binding.notifyRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.notifyRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private void getNotifyData() {
        commonViewModel.getMutableNotifyData().observe(getViewLifecycleOwner(), bookModel -> {
            if (NotifyFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (bookModel != null) {
                    if(bookModel.status.equals("success")) {
                        // For Notify Count in TestMainActivity2
                        EventBus.getDefault().post(""+bookModel.count);
                        AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.NOTIFY_COUNT, bookModel.count);
                        binding.totalNotifyCountTxt.setText("Total Orders : "+ Utils.addComma(bookModel.count));
                        binding.totalNotifyCountTxt.setVisibility((bookModel.count > 0) ? View.VISIBLE : View.GONE);
                        if (bookModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.notifyRecycler.setVisibility(View.VISIBLE);
                            list.addAll(bookModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.notifyRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        EventBus.getDefault().post(""+0);
                        AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.NOTIFY_COUNT, 0);
                        binding.totalNotifyCountTxt.setVisibility(View.GONE);
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.notifyRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shopownerid", AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID));
        return requestData;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (list != null) {
                if (list.size() > 0)
                    list.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableNotifyData().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onNotifySelect(BookModel.Data model) {
        Intent i = new Intent(getActivity(), BookingDetailsActivity.class);
        i.putExtra("booking_model", model);
        getActivity().startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reloadBtn:
                if (commonViewModel != null)
                    commonViewModel.getNotify(makeRequest(), myProgressDialog);
                break;
            default:
                break;
        }
    }
}