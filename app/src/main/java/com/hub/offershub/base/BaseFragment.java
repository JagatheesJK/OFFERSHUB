package com.hub.offershub.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hub.offershub.utils.CommonMethods;

public class BaseFragment extends Fragment {

    public CommonMethods commonMethods;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        commonMethods = new CommonMethods();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
