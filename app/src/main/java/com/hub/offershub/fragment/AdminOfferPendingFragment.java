package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hub.offershub.R;
import com.hub.offershub.adapter.AdminOfferAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentAdminOfferPendingBinding;
import com.hub.offershub.model.AdminOfferImageModel;
import com.hub.offershub.model.AdminOfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminOfferPendingFragment extends BaseFragment implements View.OnClickListener, AdminOfferAdapter.AdminOfferPendingListener {

    FragmentAdminOfferPendingBinding binding;
    private List<AdminOfferModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private AdminOfferAdapter adapter;
    private int page_no = 0;
    private static boolean isOfferList = false;

    private List<AdminOfferImageModel.Data> shopImageList = new ArrayList<>();

    public static AdminOfferPendingFragment newInstance(boolean isList) {
        AdminOfferPendingFragment fragment = new AdminOfferPendingFragment();
        isOfferList = isList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminOfferPendingBinding.inflate(getLayoutInflater());

        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            if (isOfferList) {
                list.clear();
                commonViewModel.getAdminOfferPending(myProgressDialog);
            } else {
                shopImageList.clear();
                commonViewModel.getAdminOfferImagesPending(myProgressDialog);
            }
        });

        return binding.getRoot();
    }

    private void init() {
        if (commonViewModel != null) {
            if (isOfferList) {
                commonViewModel.getAdminOfferPending(myProgressDialog);
                getAdminOfferData();
                getAdminOfferPendingApproval();
            } else {
                commonViewModel.getAdminOfferImagesPending(myProgressDialog);
                getAdminOfferImageData();
                getAdminOfferImagePendingApproval();
            }
        }
    }

    private void setListener() {
        binding.empty.reloadBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.shopRecycler.setLayoutManager(linearLayoutManager);
        if (isOfferList)
            adapter = new AdminOfferAdapter(getActivity(), list, null, isOfferList, this, true);
        else
            adapter = new AdminOfferAdapter(getActivity(), null, shopImageList, isOfferList, this, true);
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
                    if (isOfferList)
                        commonViewModel.getAdminOfferPending(myProgressDialog);
                    else
                        commonViewModel.getAdminOfferImagesPending(myProgressDialog);
                }
                break;
            default:
                break;
        }
    }

    private void getAdminOfferData() {
        commonViewModel.getMutableAdminOfferPendingData().observe(getViewLifecycleOwner(), adminOfferModel -> {
            if (AdminOfferPendingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
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

    private void getAdminOfferPendingApproval() {
        commonViewModel.getMutableAdminOfferPendingApprovalData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (AdminOfferPendingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
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

    private void getAdminOfferImageData() {
        commonViewModel.getMutableAdminOfferImagePendingData().observe(getViewLifecycleOwner(), adminOfferImageModel -> {
            if (AdminOfferPendingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
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

    private void getAdminOfferImagePendingApproval() {
        commonViewModel.getMutableAdminOfferImagePendingApprovalData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (AdminOfferPendingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
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
        requestData.put("offer_id", shopID);
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
            commonViewModel.getMutableAdminOfferPendingData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferPendingApprovalData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferImagePendingData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferImagePendingApprovalData().removeObservers(getViewLifecycleOwner());
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
            commonViewModel.getMutableAdminOfferPendingData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferPendingApprovalData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferImagePendingData().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableAdminOfferImagePendingApprovalData().removeObservers(getViewLifecycleOwner());
        }
    }

    AdminOfferModel.Data approvalModel;
    AdminOfferImageModel.Data approvalImageModel;
    @Override
    public void onApproval(String type, AdminOfferModel.Data model, AdminOfferImageModel.Data imageModel) {
        showProgress();
        if (model != null) {
            approvalModel = model;
            commonViewModel.getAdminOfferPendingApproval(makeRequest(model.id, type), myProgressDialog);
        } else {
            approvalImageModel = imageModel;
            commonViewModel.getAdminOfferImagePendingApproval(makeRequestImage(""+imageModel.id, type), myProgressDialog);
        }
    }

    @Override
    public void onReject(String type, AdminOfferModel.Data model, AdminOfferImageModel.Data imageModel) {
        showProgress();
        if (model != null) {
            approvalModel = model;
            commonViewModel.getAdminOfferPendingApproval(makeRequest(model.id, type), myProgressDialog);
        } else {
            approvalImageModel = imageModel;
            commonViewModel.getAdminOfferImagePendingApproval(makeRequestImage(""+imageModel.id, type), myProgressDialog);
        }
    }
}