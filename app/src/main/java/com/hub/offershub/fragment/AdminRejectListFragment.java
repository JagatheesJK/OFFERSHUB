package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.adapter.AdminShopAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAdminRejectListBinding;
import com.hub.offershub.model.AdminShopImageModel;
import com.hub.offershub.model.AdminShopModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.List;

public class AdminRejectListFragment extends BaseFragment implements View.OnClickListener, AdminShopAdapter.AdminShopPendingListener {

    FragmentAdminRejectListBinding binding;
    private List<AdminShopModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private AdminShopAdapter adapter;
    private int page_no = 0;
    private static boolean isShopList = false;

    private List<AdminShopImageModel.Data> shopImageList = new ArrayList<>();

    public static AdminRejectListFragment newInstance(boolean isList) {
        AdminRejectListFragment fragment = new AdminRejectListFragment();
        isShopList = isList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminRejectListBinding.inflate(getLayoutInflater());

        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            if (isShopList) {
                list.clear();
                commonViewModel.getAdminShopsRejected(myProgressDialog);
            } else {
                shopImageList.clear();
                commonViewModel.getAdminShopImagesRejected(myProgressDialog);
            }
        });

        return binding.getRoot();
    }

    private void init() {
        if (commonViewModel != null) {
            if (isShopList) {
                commonViewModel.getAdminShopsRejected(myProgressDialog);
                getAdminShopsRejected();
            } else {
                commonViewModel.getAdminShopImagesRejected(myProgressDialog);
                getAdminShopImageRejected();
            }
        }
    }

    private void setListener() {
        binding.empty.reloadBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.shopRecycler.setLayoutManager(linearLayoutManager);
        if (isShopList)
            adapter = new AdminShopAdapter(getActivity(), list, null, isShopList, this, false);
        else
            adapter = new AdminShopAdapter(getActivity(), null, shopImageList, isShopList, this, false);
        binding.shopRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.shopRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reloadBtn:
                if (commonViewModel != null) {
                    if (isShopList)
                        commonViewModel.getAdminShopsRejected(myProgressDialog);
                    else
                        commonViewModel.getAdminShopImagesRejected(myProgressDialog);
                }
                break;
            default:
                break;
        }
    }

    private void getAdminShopsRejected() {
        commonViewModel.getMutableAdminShopsRejected().observe(getViewLifecycleOwner(), adminShopModel -> {
            if (AdminRejectListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (adminShopModel != null) {
                    if(adminShopModel.status.equals("success")) {
                        if (adminShopModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.shopRecycler.setVisibility(View.VISIBLE);
                            list.addAll(adminShopModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.shopRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.shopRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void getAdminShopImageRejected() {
        commonViewModel.getMutableAdminShopImageRejected().observe(getViewLifecycleOwner(), adminShopImageModel -> {
            if (AdminRejectListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (adminShopImageModel != null) {
                    if(adminShopImageModel.status.equals("success")) {
                        if (adminShopImageModel.data != null) {
                            if (page_no == 0)
                                shopImageList.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.shopRecycler.setVisibility(View.VISIBLE);
                            shopImageList.addAll(adminShopImageModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.shopRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.shopRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (commonViewModel != null) {
            commonViewModel.getMutableAdminShopsRejected().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopImageRejected().removeObservers(getViewLifecycleOwner());
        }
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
            commonViewModel.getMutableAdminShopsRejected().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopImageRejected().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onApproval(String type, AdminShopModel.Data model, AdminShopImageModel.Data imageModel) {

    }

    @Override
    public void onReject(String type, AdminShopModel.Data model, AdminShopImageModel.Data imageModel) {

    }
}