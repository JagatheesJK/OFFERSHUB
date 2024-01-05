package com.hub.offershub.fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hub.offershub.R;
import com.hub.offershub.adapter.RatingAdaper;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentRatingBinding;
import com.hub.offershub.model.RatingModel;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingFragment extends BaseFragment implements View.OnClickListener, RatingAdaper.CommentListener {

    private FragmentRatingBinding binding;
    private List<RatingModel.Data> list = new ArrayList<>();
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    private RatingAdaper adapter;
    private static String shopID;
    private int page_no = 0;

    // Keyboard
    protected InputMethodManager inputMethodManager;

    public static RatingFragment newInstance(String shop_id) {
        RatingFragment fragment = new RatingFragment();
        shopID = shop_id;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRatingBinding.inflate(getLayoutInflater());
        init();
        setListener();
        setUpRecycler();
        getRatingReviewData();
        getReplayRatingData();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            list.clear();
            page_no = 0;
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getRatingReview(makeRatingRequest(), myProgressDialog);
        });
        return binding.getRoot();
    }

    private void init() {
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        commonViewModel.getRatingReview(makeRatingRequest(), myProgressDialog);
    }

    private void setListener() {
        binding.liveSendBtn.setOnClickListener(this);
        binding.rootLayout.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.ratingRecycler.setLayoutManager(linearLayoutManager);
        adapter = new RatingAdaper(getActivity(), list, this);
        binding.ratingRecycler.setAdapter(adapter);
        setNotify();
    }

    private void setNotify() {
        binding.ratingRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    String replayComment;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.liveSendBtn:
                replayComment = binding.liveMessageEditText.getText().toString();
                hideKeybaord();
                if (replayComment.length() > 0) {
                    commonViewModel.shopRatingReply(makeReplayRatingRequest(binding.liveMessageEditText.getText().toString()), myProgressDialog);
                    binding.liveMessageEditText.setText("");
                } else {
                    Toast.makeText(getActivity(), "Say something...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rootLayout:
                Log.e("Check_Touch", "setListener RootClick 1");
                break;
            default:
                break;
        }
    }

    private Map<String, Object> makeRatingRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        return requestData;
    }

    private Map<String, Object> makeReplayRatingRequest(String replayComment) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("id", replayModel.id);
        requestData.put("replycomments", replayComment);
        return requestData;
    }

    private void getRatingReviewData() {
        commonViewModel.getMutableRatingData().observe(getViewLifecycleOwner(), ratingModel -> {
            if (RatingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (ratingModel != null) {
                    if(ratingModel.status.equals("success")) {
                        if (ratingModel.data != null) {
                            if (page_no == 0)
                                list.clear();
                            binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.ratingRecycler.setVisibility(View.VISIBLE);
                            adapter.addAll(ratingModel.data);
//                            list.addAll(ratingModel.data);
//                            setNotify();
                        } else {
                            binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                            binding.ratingRecycler.setVisibility(View.GONE);
                        }
                    } else {
                        binding.empty.emptyConstraint.setVisibility(View.VISIBLE);
                        binding.ratingRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void getReplayRatingData() {
        commonViewModel.getMutableRatingReplyData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (RatingFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    binding.commentEditLayout.setVisibility(View.GONE);
                    adapter.changeData(list.indexOf(replayModel), replayModel);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Rating & Review");
    }

    private RatingModel.Data replayModel;
    @Override
    public void onReplay(RatingModel.Data model) {
        replayModel = model;
        showKeyboard();
    }

    private void showKeyboard() {
        if (binding.commentEditLayout != null) {
            binding.commentEditLayout.setVisibility(View.VISIBLE);
            binding.liveMessageEditText.setFocusable(true);
            binding.liveMessageEditText.requestFocus();
            inputMethodManager.showSoftInput(binding.liveMessageEditText, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    private void hideKeybaord() {
        binding.commentEditLayout.setVisibility(View.GONE);
        inputMethodManager.hideSoftInputFromWindow(binding.liveMessageEditText.getWindowToken(), 0);
    }
}