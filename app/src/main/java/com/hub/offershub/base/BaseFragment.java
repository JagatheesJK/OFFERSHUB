package com.hub.offershub.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hub.offershub.R;
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

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            if (!fragmentManager.isDestroyed())
                transaction.replace(R.id.flFragment, fragment).commit();
        }
    }
}
