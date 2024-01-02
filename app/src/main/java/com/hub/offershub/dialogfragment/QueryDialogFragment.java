package com.hub.offershub.dialogfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentQueryDialogBinding;

public class QueryDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private FragmentQueryDialogBinding binding;

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
        return binding.getRoot();
    }

    private void init() {

    }

    private void setListener() {
        binding.closeBtn.setOnClickListener(this);
        binding.submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.closeBtn:
                break;
            case R.id.submitBtn:
                break;
            default:
                break;
        }
    }
}