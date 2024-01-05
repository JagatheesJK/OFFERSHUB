package com.hub.offershub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hub.offershub.R;
import com.hub.offershub.activity.EditDetailsActivity;
import com.hub.offershub.adapter.OfferAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentActiveOfferBinding;
import com.hub.offershub.listener.OfferListener;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveOfferFragment extends BaseFragment implements View.OnClickListener, OfferListener {

    private FragmentActiveOfferBinding binding;
    private List<OfferModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private OfferAdapter adapter;
    private int page_no = 0;
    private static String shopID = "";

    public static ActiveOfferFragment newInstance(String shop_id) {
        ActiveOfferFragment fragment = new ActiveOfferFragment();
        shopID = shop_id;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActiveOfferBinding.inflate(getLayoutInflater());

//        init();
        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getActiveOffers(makeRequest(), myProgressDialog);
        });

        return binding.getRoot();
    }

    private void init() {
        if (commonViewModel != null) {
            commonViewModel.getActiveOffers(makeRequest(), myProgressDialog);
            getActiveOffersData();
            getDeleteData();
            getOfferPriorityData();
        }
    }

    private void setListener() {
        binding.empty.reloadBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.offerRecycler.setLayoutManager(linearLayoutManager);
        adapter = new OfferAdapter(getActivity(), list, this, true);
        binding.offerRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.offerRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private void getActiveOffersData() {
        commonViewModel.getMutableActiveOffers().observe(getViewLifecycleOwner(), offerModel -> {
            if (ActiveOfferFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (offerModel != null) {
                    if(offerModel.status.equals("success")) {
                        if (offerModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.offerRecycler.setVisibility(View.VISIBLE);
                            list.addAll(offerModel.data);
                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.offerRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.offerRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void getDeleteData() {
        commonViewModel.getMutableDeleteOffer().observe(getViewLifecycleOwner(), jsonObject -> {
            if (ActiveOfferFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        Log.e("Check_Offer", "ActiveOffer getDeleteData Status : "+jsonObject.getString("status"));
                        if(jsonObject.getString("status").equals("success")) {
                            adapter.removeData(deleteModel, deletePosition);
                            Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void getOfferPriorityData() {
        commonViewModel.getMutableOfferPriorityData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (ActiveOfferFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        Log.e("Check_Offer", "ActiveOffer getDeleteData Status : "+jsonObject.getString("status"));
                        if(jsonObject.getString("status").equals("success")) {

                        } else {

                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
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

    private Map<String, Object> makeDeleteRequest(String shopID) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("offer_id", shopID);
        return requestData;
    }

    private Map<String, Object> makePriorityRequest(String offer_id, String priority) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("offer_id", offer_id);
        requestData.put("priority", priority);
        return requestData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableActiveOffers().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableDeleteOffer().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableOfferPriorityData().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onOfferSelect(OfferModel.Data model, String priority) {
        commonViewModel.offerPriority(makePriorityRequest(model.offer_id, priority), myProgressDialog);
//        loadFragment(new ShopDetailsFragment());
    }

    @Override
    public void onOfferEdit(Object obj) {
        OfferModel.Data model = (OfferModel.Data) obj;
        BusinessModel.Data businessModel = null;
        Intent i = new Intent(getActivity(), EditDetailsActivity.class);
        i.putExtra("offer_model", model);
        i.putExtra("shop_model", businessModel);
        i.putExtra("isShop", false);
        startActivity(i);
    }

    OfferModel.Data deleteModel;
    int deletePosition;
    @Override
    public void onOfferRemove(Object obj, int position) {
        deleteModel = (OfferModel.Data) obj;
        deletePosition = position;
        commonViewModel.getDeleteOffer(makeDeleteRequest(deleteModel.offer_id), myProgressDialog);
    }

    @Override
    public void onOfferInSight(Object obj) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, new OfferDashboardFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reloadBtn:
                commonViewModel.getActiveOffers(makeRequest(), myProgressDialog);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Check_Offer", "ActiveOffer onPause");
        if (commonViewModel != null) {
            commonViewModel.getMutableActiveOffers().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableDeleteOffer().removeObservers(getViewLifecycleOwner());
            commonViewModel.getMutableOfferPriorityData().removeObservers(getViewLifecycleOwner());
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
}