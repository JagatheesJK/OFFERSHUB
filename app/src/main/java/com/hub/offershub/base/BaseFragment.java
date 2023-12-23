package com.hub.offershub.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.hub.offershub.R;
import com.hub.offershub.utils.CommonMethods;
import com.hub.offershub.utils.loading.MyProgressDialog;
import com.hub.offershub.viewmodel.CommonViewModel;

public class BaseFragment extends Fragment {

    public CommonMethods commonMethods;
    public MyProgressDialog myProgressDialog;
    public CommonViewModel commonViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        commonMethods = new CommonMethods();
        myProgressDialog = new MyProgressDialog(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory()).get(CommonViewModel.class);
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

    public void showDialog() {
        try {
            if (myProgressDialog != null && !myProgressDialog.isShowing())
                myProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
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
