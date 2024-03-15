package com.hub.offershub.dialogfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentQueryDialogBinding;
import com.hub.offershub.listener.FeedbackListener;
import com.hub.offershub.listener.ReplayListener;
import com.hub.offershub.model.RatingModel;
import com.hub.offershub.utils.loading.MyProgressDialog;
import com.hub.offershub.viewmodel.CommonViewModel;

import java.util.HashMap;
import java.util.Map;

public class QueryDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = QueryDialogFragment.class.getSimpleName();
    private FragmentQueryDialogBinding binding;
    private CommonViewModel commonViewModel;
    private String shopID, title;
    private RatingModel.Data replayModel;
    private MyProgressDialog myProgressDialog;
    private FeedbackListener listener;
    private ReplayListener replayListener;

    public static QueryDialogFragment newInstance() {
        QueryDialogFragment fragment = new QueryDialogFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myProgressDialog = new MyProgressDialog(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,  R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQueryDialogBinding.inflate(getLayoutInflater());
        init();
        setListener();
        getAddFeedbackData();
        return binding.getRoot();
    }

    private void init() {
        commonViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory()).get(CommonViewModel.class);
        binding.titleTxt.setText(""+title);

        if (title.equals("Feedback")) {
            binding.feedbackEd.setHint("Your Feedback...");
        } else {
            binding.feedbackEd.setHint("Your Replay...");
        }
    }

    private void setListener() {
        binding.closeBtn.setOnClickListener(this);
        binding.submitBtn.setOnClickListener(this);
    }

    public void setData(String title, String shop_id, FeedbackListener listener) {
        this.title = title;
        shopID = shop_id;
        this.listener = listener;
    }

    public void setData(String title, RatingModel.Data replayModel, ReplayListener listener) {
        this.title = title;
        this.replayModel = replayModel;
        this.replayListener = listener;
    }

    String feedbackMsg;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.closeBtn:
                dismiss();
                break;
            case R.id.submitBtn:
                feedbackMsg = binding.feedbackEd.getText().toString();
                if (feedbackMsg.length() > 0) {
                    showDialog();
                    if (listener != null) {
                        binding.feedbackEd.setText("");
                        commonViewModel.shopFeedback(makeAddFeedbackRequest(feedbackMsg), myProgressDialog);
                    } else {
                        replayListener.onReplaySuccess(feedbackMsg);
                        binding.feedbackEd.setText("");
                        closeDialog();
                        dismiss();
                    }
                } else
                    Toast.makeText(getActivity(), "Say something...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private Map<String, Object> makeAddFeedbackRequest(String feedbackMsg) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", shopID);
        requestData.put("feedback", feedbackMsg);
        return requestData;
    }

    private void getAddFeedbackData() {
        commonViewModel.getMutableAddFeedbackData().observe(getViewLifecycleOwner(), jsonObject -> {
            if (QueryDialogFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if ("success".equals(jsonObject.getString("status"))) {
                            dismiss();
                        }
                        Toast.makeText(getActivity(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                closeDialog();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null)
            commonViewModel.getMutableAddFeedbackData().removeObservers(getViewLifecycleOwner());
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null)
            listener.onFeedbackSuccess();
    }

    public void showDialog() {
        try {
            if (myProgressDialog != null && !myProgressDialog.isShowing())
                myProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Check_JKUpdate", "showDialog Error : "+e.getMessage());
        }
    }

    public void closeDialog() {
        try {
            if (myProgressDialog != null && myProgressDialog.isShowing())
                myProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}