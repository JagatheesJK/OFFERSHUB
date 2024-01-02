package com.hub.offershub.activity;

import android.os.Bundle;
import android.view.View;

import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    private ActivityFeedbackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setListener();
    }

    private void init() {
        binding.toolbarLayout.toolbarTitle.setText("Edit Business");
    }

    private void setListener() {
        binding.toolbarLayout.toolbarBack.setOnClickListener(this);
        binding.createBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbarBack:
                finish();
                break;
            case R.id.createBtn:

                break;
            default:
                break;
        }
    }
}