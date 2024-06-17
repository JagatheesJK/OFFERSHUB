package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.adapter.AdminOfferAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAdminOfferRejectBinding;
import com.hub.offershub.model.AdminOfferImageModel;
import com.hub.offershub.model.AdminOfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.List;

public class AdminOfferRejectFragment extends BaseFragment implements View.OnClickListener, AdminOfferAdapter.AdminOfferPendingListener {

    FragmentAdminOfferRejectBinding binding;
    private List<AdminOfferModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private AdminOfferAdapter adapter;
    private int page_no = 0;
    private static boolean isShopList = false;

    private List<AdminOfferImageModel.Data> shopImageList = new ArrayList<>();

    public static AdminOfferRejectFragment newInstance(boolean isList) {
        AdminOfferRejectFragment fragment = new AdminOfferRejectFragment();
        isShopList = isList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminOfferRejectBinding.inflate(getLayoutInflater());

        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            if (isShopList) {
                list.clear();
                commonViewModel.getAdminOfferRejected(myProgressDialog);
            } else {
                shopImageList.clear();
                commonViewModel.getAdminOfferImagesRejected(myProgressDialog);
            }
        });

        return binding.getRoot();
    }

    private void init() {
        if (commonViewModel != null) {
            if (isShopList) {
                commonViewModel.getAdminOfferRejected(myProgressDialog);
                getAdminOfferRejected();
            } else {
                commonViewModel.getAdminOfferImagesRejected(myProgressDialog);
                getAdminOfferImageRejected();
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
            adapter = new AdminOfferAdapter(getActivity(), list, null, isShopList, this, false);
        else
            adapter = new AdminOfferAdapter(getActivity(), null, shopImageList, isShopList, this, false);
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
                        commonViewModel.getAdminOfferRejected(myProgressDialog);
                    else
                        commonViewModel.getAdminOfferImagesRejected(myProgressDialog);
                }
                break;
            default:
                break;
        }
    }

    private void getAdminOfferRejected() {
        commonViewModel.getMutableAdminOfferRejectData().observe(getViewLifecycleOwner(), adminOfferModel -> {
            if (AdminOfferRejectFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (adminOfferModel != null) {
                    if(adminOfferModel.status.equals("success")) {
                        if (adminOfferModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.shopRecycler.setVisibility(View.VISIBLE);
                            list.addAll(adminOfferModel.data);
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

    private void getAdminOfferImageRejected() {
        commonViewModel.getMutableAdminOfferImageRejected().observe(getViewLifecycleOwner(), adminOfferImageModel -> {
            if (AdminOfferRejectFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (adminOfferImageModel != null) {
                    if(adminOfferImageModel.status.equals("success")) {
                        if (adminOfferImageModel.data != null) {
                            if (page_no == 0)
                                shopImageList.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.shopRecycler.setVisibility(View.VISIBLE);
                            shopImageList.addAll(adminOfferImageModel.data);
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
            commonViewModel.getMutableAdminOfferRejectData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferImageRejected().removeObservers(getViewLifecycleOwner());
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
            commonViewModel.getMutableAdminOfferRejectData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferImageRejected().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onApproval(String type, AdminOfferModel.Data model, AdminOfferImageModel.Data imageModel) {

    }

    @Override
    public void onReject(String type, AdminOfferModel.Data model, AdminOfferImageModel.Data imageModel) {

    }
}