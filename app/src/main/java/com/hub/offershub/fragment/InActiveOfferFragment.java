package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hub.offershub.R;
import com.hub.offershub.adapter.OfferAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentInActiveOfferBinding;
import com.hub.offershub.listener.OfferListener;
import com.hub.offershub.model.OfferModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InActiveOfferFragment extends BaseFragment implements View.OnClickListener, OfferListener {

    FragmentInActiveOfferBinding binding;
    private List<OfferModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private OfferAdapter adapter;
    private int page_no = 0;
    private static String shopID;

    public static InActiveOfferFragment newInstance(String shop_id) {
        InActiveOfferFragment fragment = new InActiveOfferFragment();
        shopID = shop_id;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInActiveOfferBinding.inflate(getLayoutInflater());

        init();
        setListener();
        setUpRecycler();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getInActiveOffers(makeRequest());
        });

        return binding.getRoot();
    }

    private void init() {
        commonViewModel.getInActiveOffers(makeRequest());
        getInActiveOffersData();
        getDeleteData();
    }

    private void setListener() {
        binding.empty.reloadBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.offerRecycler.setLayoutManager(linearLayoutManager);
        adapter = new OfferAdapter(getActivity(), list, this);
        binding.offerRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.offerRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private void getInActiveOffersData() {
        commonViewModel.getMutableInActiveOffers().observe(getViewLifecycleOwner(), offerModel -> {
            if (offerModel != null) {
                if(offerModel.status.equals("success")) {
                    if (offerModel.data != null) {
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
        });
    }

    private void getDeleteData() {
        commonViewModel.getMutableDeleteOffer().observe(getViewLifecycleOwner(), jsonObject -> {
            if (jsonObject != null) {
                try {
                    if(jsonObject.getString("status").equals("success")) {
                        adapter.removeData(deleteModel);
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableInActiveOffers().removeObservers(this);
            commonViewModel.getMutableDeleteOffer().removeObservers(this);
        }
    }

    @Override
    public void onOfferSelect() {
//        loadFragment(new ShopDetailsFragment());
    }

    @Override
    public void onOfferEdit(Object obj) {
        Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
    }

    OfferModel.Data deleteModel;
    @Override
    public void onOfferRemove(Object obj) {
        deleteModel = (OfferModel.Data) obj;
        commonViewModel.getDeleteOffer(makeDeleteRequest(deleteModel.offer_id));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reloadBtn:
                commonViewModel.getInActiveOffers(makeRequest());
                break;
            default:
                break;
        }
    }
}