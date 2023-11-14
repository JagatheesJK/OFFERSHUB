package com.hub.offershub.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hub.offershub.fragment.ActiveBusinessFragment;
import com.hub.offershub.fragment.InActiveBusinessFragment;

public class PagerAdapter extends FragmentStateAdapter {

    String[] list;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, String[] list) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new InActiveBusinessFragment();
            case 1:
                return new ActiveBusinessFragment();
        }
        return new ActiveBusinessFragment();
    }

    @Override
    public int getItemCount() {
        return list.length;
    }
}
