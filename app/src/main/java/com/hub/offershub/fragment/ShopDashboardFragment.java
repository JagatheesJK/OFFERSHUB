package com.hub.offershub.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chart.all.anychart.AnyChart;
import com.chart.all.anychart.AnyChartView;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.chart.common.dataentry.ValueDataEntry;
import com.chart.all.anychart.chart.common.listener.Event;
import com.chart.all.anychart.chart.common.listener.ListenersInterface;
import com.chart.all.anychart.charts.Cartesian;
import com.chart.all.anychart.charts.Pie;
import com.chart.all.anychart.core.cartesian.series.Column;
import com.chart.all.anychart.enums.Anchor;
import com.chart.all.anychart.enums.HoverMode;
import com.chart.all.anychart.enums.Position;
import com.chart.all.anychart.enums.TooltipPositionMode;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentShopDashboardBinding;
import com.hub.offershub.model.BusinessModel;
import com.hub.offershub.model.ShopDashboardModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopDashboardFragment extends BaseFragment {

    private FragmentShopDashboardBinding binding;
    private ShopDashboardModel shopDashboardModel;
    private static BusinessModel.Data businessModel;

    public static ShopDashboardFragment newInstance(BusinessModel.Data model) {
        ShopDashboardFragment fragment = new ShopDashboardFragment();
        businessModel = model;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShopDashboardBinding.inflate(getLayoutInflater());
        commonViewModel.getShopsDashData(makeRequest(), myProgressDialog);
        getShopsDashData();
        return binding.getRoot();
    }

    private void initUI() {
        binding.totalOffersVisitTxt.setText(""+shopDashboardModel.data.offervisitcount);
        binding.totalShopVisitTxt.setText(""+shopDashboardModel.data.shopvisitcount);
        binding.contactVisitTxt.setText(""+shopDashboardModel.data.contactvisitcount);
        binding.mapVisitTxt.setText(""+shopDashboardModel.data.mapvisitcount);
        binding.totalOrderCountTxt.setText(""+shopDashboardModel.data.totalorders);
        binding.repeatUserVisitTxt.setText(""+shopDashboardModel.data.repeatedusers);
        binding.totalUserVisitTxt.setText(""+shopDashboardModel.data.totalusers);
    }

    private void ageBarChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        //chart1.setProgressBar(binding.progressBar);
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        for (int i=0; i < shopDashboardModel.agechart.size(); i++) {
            data.add(new ValueDataEntry(shopDashboardModel.agechart.get(i).age, shopDashboardModel.agechart.get(i).value));
        }

        Column column = cartesian.column(data);
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d);

        cartesian.animation(true);
        //cartesian.title("Top 10 Cosmetic Products by Revenue");
        cartesian.yScale().minimum(0d);
        //cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Age");
        cartesian.yAxis(0).title("Value");

        cartesian.draw(true);
        //binding.anyChartView.setVisibility(View.VISIBLE);
        chart1.setChart(cartesian);
        //binding.anyChartView.addView(chart1);
    }

    private void newGenderPieChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.genderProgressBar);
        Pie pie = AnyChart.pie();
        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
//                Toast.makeText(getActivity(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        for (int i = 0; i < shopDashboardModel.genderpiechart.size(); i++) {
            data.add(new ValueDataEntry(shopDashboardModel.genderpiechart.get(i).gender, shopDashboardModel.genderpiechart.get(i).value));
        }
        /*data.add(new ValueDataEntry("Apples", 6371664));
        data.add(new ValueDataEntry("Pears", 789622));
        data.add(new ValueDataEntry("Bananas", 7216301));
        data.add(new ValueDataEntry("Grapes", 1486621));
        data.add(new ValueDataEntry("Oranges", 1200000));*/

        pie.data(data);
        //pie.title("Fruits imported in 2015 (in kg)");
        pie.labels().position("inside");

        //pie.legend().title().enabled(true);
       /* pie.legend().title()
                .text("Retail channels")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);*/
        pie.draw(true);
//        anyChartView.setVisibility(View.VISIBLE);
        chart1.setChart(pie);
        binding.genderChartView.addView(chart1);
    }

    private void newMultipleBarChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        //chart1.setProgressBar(binding.progressBar);
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        for (int i=0; i < shopDashboardModel.visitchart.size(); i++) {
            data.add(new ValueDataEntry(shopDashboardModel.visitchart.get(i).day, shopDashboardModel.visitchart.get(i).count));
        }

        Column column = cartesian.column(data);
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        //cartesian.title("Top 10 Cosmetic Products by Revenue");
        cartesian.yScale().minimum(0d);
        //cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Day");
        cartesian.yAxis(0).title("Value");

        cartesian.draw(true);
        //binding.anyChartView1.setVisibility(View.VISIBLE);
        chart1.setChart(cartesian);
        //binding.anyChartView1.addView(chart1);
    }



    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", businessModel.id);
       // requestData.put("shop_id", 1);
        return requestData;
    }

    private void getShopsDashData() {
        commonViewModel.getMutableShopDashData().observe(getViewLifecycleOwner(), shopDashboardModel -> {
            if (ShopDashboardFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (shopDashboardModel != null) {
                    if(shopDashboardModel.status.equals("success")) {
                        this.shopDashboardModel = shopDashboardModel;
                        /*this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 01",5));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 02",3));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 03",2));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 04",10));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 05",1));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 06",5));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 07",6));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 08",100));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 12",120));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 14",150));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 15",26));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 17",50));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 22",83));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 24",57));
                        this.shopDashboardModel.visitchart.add(new ShopDashboardModel.BarChart("Feb 01",5));*/

                        initUI();
                        if (!shopDashboardModel.agechart.isEmpty()) {
                            binding.ageBarChart.setVisibility(View.VISIBLE);
                            List<BarEntry> entries = new ArrayList<>();
                            List<String> labels = new ArrayList<>();

                            for (int i = 0; i < shopDashboardModel.agechart.size() ; i++) {
                                // Add data to entries list
                                entries.add(new BarEntry(i, shopDashboardModel.agechart.get(i).value));
                                labels.add(shopDashboardModel.agechart.get(i).age);
                            }
                            BarDataSet dataSet = new BarDataSet(entries, "Age");

                            // Customize dataset as needed
                            dataSet.setBarBorderColor(ContextCompat.getColor(getContext(), R.color.colorPromo));
                            dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorFeatured));
                            dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

                            BarData barData = new BarData(dataSet);
                            binding.ageBarChart.setData(barData);
                            binding.ageBarChart.setVisibleXRange(0f, 6f);
                            binding.ageBarChart.getDescription().setEnabled(false);
                            // Customize chart appearance
                            // ...

                            // Customize X-axis
                            XAxis xAxis = binding.ageBarChart.getXAxis();
                            xAxis.setTextSize(10f); // Set your desired label text size here
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                            xAxis.setGranularity(1f);
                            xAxis.setDrawGridLines(false);


                            // Customize legend
                            Legend legend = binding.ageBarChart.getLegend();
                            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Set legend position
                            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Set legend position
                            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Set legend orientation
                            legend.setDrawInside(false);

                            binding.ageBarChart.invalidate();
                            //ageBarChart();
                        } else {
                            binding.ageBarChart.setVisibility(View.GONE);
                        }
                        if (!shopDashboardModel.genderpiechart.isEmpty()) {
                            binding.genderPieChart.setVisibility(View.VISIBLE);
                            newGenderPieChart();
                        } else {
                            binding.genderPieChart.setVisibility(View.GONE);
                        }
                        if (!shopDashboardModel.visitchart.isEmpty()) {
                            binding.barChart.setVisibility(View.VISIBLE);
                            List<BarEntry> entries = new ArrayList<>();
                            List<String> labels = new ArrayList<>();

                            for (int i = 0; i < shopDashboardModel.visitchart.size() ; i++) {
                                // Add data to entries list
                                entries.add(new BarEntry(i, shopDashboardModel.visitchart.get(i).count));
                                labels.add(shopDashboardModel.visitchart.get(i).day);
                            }
                            BarDataSet dataSet = new BarDataSet(entries, "Visits");

                            // Customize dataset as needed
                            dataSet.setBarBorderColor(ContextCompat.getColor(getContext(), R.color.colorPromo));
                            dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorFeatured));
                            dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

                            BarData barData = new BarData(dataSet);
                            binding.barChart.setData(barData);
                            binding.barChart.setVisibleXRange(0f, 7f);
                            binding.barChart.getDescription().setEnabled(false);

                            // Customize chart appearance
                            // ...

                            // Customize X-axis
                            XAxis xAxis = binding.barChart.getXAxis();
                            xAxis.setTextSize(10f); // Set your desired label text size here
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                            xAxis.setGranularity(1f);
                            xAxis.setDrawGridLines(false);


                            // Customize legend
                            Legend legend = binding.barChart.getLegend();
                            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Set legend position
                            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Set legend position
                            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Set legend orientation
                            legend.setDrawInside(false);

                            binding.barChart.invalidate();
                            //newMultipleBarChart();
                        } else
                            binding.barChart.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (commonViewModel != null) {
            commonViewModel.getMutableShopDashData().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableShopDashData().removeObservers(getViewLifecycleOwner());
        }
    }
}