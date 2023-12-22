package com.hub.offershub.dialogfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentExitDialogBinding;
import com.hub.offershub.listener.ExitListener;

public class ExitDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = ExitDialogFragment.class.getSimpleName();
    private FragmentExitDialogBinding binding;
    private ExitListener listener;

    public static ExitDialogFragment newInstance() {
        ExitDialogFragment fragment = new ExitDialogFragment();
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
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExitDialogBinding.inflate(getLayoutInflater());
        init();
        setListener();
        return binding.getRoot();
    }

    private void init() {

    }

    private void setListener() {
        binding.yesBtn.setOnClickListener(this);
        binding.cancelBtn.setOnClickListener(this);
    }

    public void setExitListener(ExitListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn:
                dismiss();
                break;
            case R.id.yesBtn:
                listener.onExitYes();
                break;
            default:
                break;
        }
    }
}