package com.hub.offershub.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentShopDetailsBinding;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailsFragment extends Fragment {

    private FragmentShopDetailsBinding binding;
    private String[] labels = new String[]{"Shop Dashboard", "Offer Dashboard"};

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
//        barChart();
//        pieChart();
//        lineChart();

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        binding.pager.setCurrentItem(0, false);

        return binding.getRoot();
    }

    private void init() {
        binding.pager.setAdapter(new ViewPagerFragmentAdapter(getActivity()));
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
                    return new InActiveOfferFragment();
            }
            return new ActiveBusinessFragment();
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }
    }

    private void barChart() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 40));
        entries.add(new BarEntry(2, 30));
        entries.add(new BarEntry(3, 25));
        entries.add(new BarEntry(4, 50));

        BarDataSet barDataSet = new BarDataSet(entries, "Label");
        barDataSet.setColor(Color.BLUE);

        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        binding.barChart.invalidate();
    }

    private void pieChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "Category A"));
        entries.add(new PieEntry(20f, "Category B"));
        entries.add(new PieEntry(50f, "Category C"));

        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        dataSet.setColors(Color.BLUE, Color.GREEN, Color.RED);

        PieData data = new PieData(dataSet);

        binding.pieChart.setData(data);
        binding.pieChart.getDescription().setEnabled(false);
        binding.pieChart.setCenterText("Pie Chart");
        binding.pieChart.animateY(1000);
        binding.pieChart.invalidate();
    }

    private void lineChart() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 40));
        entries.add(new Entry(2, 30));
        entries.add(new Entry(3, 25));
        entries.add(new Entry(4, 50));

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setColor(getResources().getColor(R.color.colorPrimary));
        dataSet.setValueTextColor(getResources().getColor(R.color.colorPrimaryDark));

        LineData lineData = new LineData(dataSet);
        binding.lineChart.setData(lineData);

        // Customize X-axis
        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value);
            }
        });

        binding.lineChart.invalidate();
    }
}