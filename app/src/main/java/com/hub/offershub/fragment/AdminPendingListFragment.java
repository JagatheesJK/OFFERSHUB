package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hub.offershub.R;
import com.hub.offershub.adapter.AdminShopAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAdminPendingListBinding;
import com.hub.offershub.model.AdminShopImageModel;
import com.hub.offershub.model.AdminShopModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPendingListFragment extends BaseFragment implements View.OnClickListener, AdminShopAdapter.AdminShopPendingListener {

    FragmentAdminPendingListBinding binding;
    private List<AdminShopModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private AdminShopAdapter adapter;
    private int page_no = 0;
    private static boolean isShopList = false;

    private List<AdminShopImageModel.Data> shopImageList = new ArrayList<>();

    public static AdminPendingListFragment newInstance(boolean isList) {
        AdminPendingListFragment fragment = new AdminPendingListFragment();
        isShopList = isList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentAdminPendingListBinding.inflate(getLayoutInflater());

        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            if (isShopList) {
                list.clear();
                commonViewModel.getAdminShop(myProgressDialog);
            } else {
                shopImageList.clear();
                commonViewModel.getAdminShopImages(myProgressDialog);
            }
        });
        return binding.getRoot();
    }

    private void init() {
        if (commonViewModel != null) {
            if (isShopList) {
                commonViewModel.getAdminShop(myProgressDialog);
                getAdminShopData();
                getAdminShopPendingApproval();
            } else {
                commonViewModel.getAdminShopImages(myProgressDialog);
                getAdminShopImageData();
                getAdminShopImagePendingApproval();
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
            adapter = new AdminShopAdapter(getActivity(), list, null, isShopList, this, true);
        else
            adapter = new AdminShopAdapter(getActivity(), null, shopImageList, isShopList, this, true);
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
                        commonViewModel.getAdminShop(myProgressDialog);
                    else
                        commonViewModel.getAdminShopImages(myProgressDialog);
                }
                break;
            default:
                break;
        }
    }

    private void getAdminShopData() {
        commonViewModel.getMutableAdminShopData().observe(getViewLifecycleOwner(), adminShopModel -> {
            if (AdminPendingListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
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

    private void getAdminShopPendingApproval() {
        commonViewModel.getMutableAdminShopPendingApprovalData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (AdminPendingListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getString("status").equals("success")) {
                            adapter.removeData(approvalModel);
                            Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                hideProgress();
            }
        });
    }

    private void getAdminShopImageData() {
        commonViewModel.getMutableAdminShopImageData().observe(getViewLifecycleOwner(), adminShopImageModel -> {
            if (AdminPendingListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
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

    private void getAdminShopImagePendingApproval() {
        commonViewModel.getMutableAdminShopImagePendingApprovalData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (AdminPendingListFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getString("status").equals("success")) {
                            adapter.removeData(approvalImageModel);
                            Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                hideProgress();
            }
        });
    }

    private Map<String, Object> makeRequest(String shopID, String type) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        requestData.put("type", type);
        return requestData;
    }

    private Map<String, Object> makeRequestImage(String imageID, String type) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("image_id", imageID);
        requestData.put("type", type);
        return requestData;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (commonViewModel != null) {
            commonViewModel.getMutableAdminShopData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopPendingApprovalData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopImageData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopImagePendingApprovalData().removeObservers(getViewLifecycleOwner());
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
            commonViewModel.getMutableAdminShopData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopPendingApprovalData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopImageData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminShopImagePendingApprovalData().removeObservers(getViewLifecycleOwner());
        }
    }

    AdminShopModel.Data approvalModel;
    AdminShopImageModel.Data approvalImageModel;
    @Override
    public void onApproval(String type, AdminShopModel.Data model, AdminShopImageModel.Data imageModel) {
        showProgress();
        if (model != null) {
            approvalModel = model;
            commonViewModel.getAdminShopPendingApproval(makeRequest(model.id, type), myProgressDialog);
        } else {
            approvalImageModel = imageModel;
            commonViewModel.getAdminShopImagePendingApproval(makeRequestImage(""+imageModel.id, type), myProgressDialog);
        }
    }

    @Override
    public void onReject(String type, AdminShopModel.Data model, AdminShopImageModel.Data imageModel) {
        showProgress();
        if (model != null) {
            approvalModel = model;
            commonViewModel.getAdminShopPendingApproval(makeRequest(model.id, type), myProgressDialog);
        } else {
            approvalImageModel = imageModel;
            commonViewModel.getAdminShopImagePendingApproval(makeRequestImage(""+imageModel.id, type), myProgressDialog);
        }
    }
}