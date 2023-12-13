package com.hub.offershub.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hub.offershub.AppApplication;
import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentShopDetailsBinding;
import com.hub.offershub.utils.Utils;

public class ShopDetailsFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentShopDetailsBinding binding;
    private String[] labels = new String[]{"Shop Dashboard", "Offer Dashboard"};
    private DrawerLayout drawerLayout;

    public static ShopDetailsFragment newInstance() {
        ShopDetailsFragment fragment = new ShopDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShopDetailsBinding.inflate(getLayoutInflater());
        init();

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        binding.pager.setCurrentItem(0, false);

        return binding.getRoot();
    }

    private void init() {
        binding.pager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));

        /*drawerLayout = binding.drawerLayout;

        binding.navView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, binding.toolbar, R.string.nav_open,
                R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, new OfferListFragment()).commit();
        binding.navView.setCheckedItem(R.id.nav_home);*/
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ShopDashboardFragment();
                case 1:
                    return new OfferDashboardFragment();
            }
            return new ActiveBusinessFragment();
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Dashboard");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_offer) {
            fragment = new OfferListFragment();
        } else if (id == R.id.nav_shop_dash) {
            fragment = new ShopDashboardFragment();
        } else if (id == R.id.nav_offer_dash) {
            fragment = new OfferDashboardFragment();
        } else if (id == R.id.nav_booking_details) {
            fragment = new BookingListFragment();
        } else  {
            Utils.logout(getActivity(), AppApplication.getInstance().prefsHelper);

        }

        if (fragment != null) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        binding.drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}