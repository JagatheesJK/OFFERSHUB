package com.hub.offershub.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import com.hub.offershub.R;
import com.hub.offershub.databinding.FragmentShopDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class ShopDashboardFragment extends Fragment {

    private FragmentShopDashboardBinding binding;

    public static ShopDashboardFragment newInstance() {
        ShopDashboardFragment fragment = new ShopDashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShopDashboardBinding.inflate(getLayoutInflater());
//        barChart();
        multipleBarChart();
        pieChart();
        lineChart();
        return binding.getRoot();
    }

    private void barChart() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 40));
        entries.add(new BarEntry(2, 30));
        entries.add(new BarEntry(3, 25));
        entries.add(new BarEntry(4, 50));

        BarDataSet barDataSet = new BarDataSet(entries, "Label");
        barDataSet.setColor(Color.BLUE, Color.RED);

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

    private void multipleBarChart() {
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(1, 40));
        entries1.add(new BarEntry(2, 30));
        entries1.add(new BarEntry(3, 25));

        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1, 10));
        entries2.add(new BarEntry(2, 20));
        entries2.add(new BarEntry(3, 15));

        BarDataSet dataSet1 = new BarDataSet(entries1, "Dataset 1");
        dataSet1.setColor(Color.BLUE);

        BarDataSet dataSet2 = new BarDataSet(entries2, "Dataset 2");
        dataSet2.setColor(Color.GREEN);

        BarData barData = new BarData(dataSet1, dataSet2);
        binding.barChart.setData(barData);

        // Customize X-axis
        XAxis xAxis = binding.barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf((int) value); // Format the X-axis labels as needed
            }
        });

        // Customize Y-axis
        YAxis yAxisRight = binding.barChart.getAxisRight();
        yAxisRight.setEnabled(false); // Disable the right Y-axis
    }
}