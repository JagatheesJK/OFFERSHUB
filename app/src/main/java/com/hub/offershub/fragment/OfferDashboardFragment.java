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
import com.chart.all.anychart.charts.Pie;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentOfferDashboardBinding;
import com.hub.offershub.model.OfferDashboardModel;
import com.hub.offershub.model.OfferModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferDashboardFragment extends BaseFragment {

    private FragmentOfferDashboardBinding binding;
    private OfferDashboardModel offerDashboardModel;
    private static OfferModel.Data offerModel;

    public static OfferDashboardFragment newInstance(OfferModel.Data model) {
        OfferDashboardFragment fragment = new OfferDashboardFragment();
        offerModel = model;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOfferDashboardBinding.inflate(getLayoutInflater());
        commonViewModel.getOfferDashData(makeRequest(), myProgressDialog);
        getOfferDashData();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getOfferDashData(makeRequest(), myProgressDialog);
            getOfferDashData();
        });
        return binding.getRoot();
    }

    private void initUI() {
        binding.totalOffersVisitTxt.setText(""+offerDashboardModel.data.offervisitcount);
        binding.totalFavotiteTxt.setText(""+offerDashboardModel.data.totalfavorite);
        binding.totalOrdersTxt.setText(""+offerDashboardModel.data.totalorders);
        binding.repeatUserVisitTxt.setText(""+offerDashboardModel.data.repeatedusers);
        binding.totalUsersTxt.setText(""+offerDashboardModel.data.totalusers);
//        binding.totalOrderCountTxt.setText(""+offerDashboardModel.data.totalorders);
    }

    private void visitorDetailsBar() {
        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < offerDashboardModel.visitchart.size() ; i++) {
            // Add data to entries list
            entries.add(new BarEntry(i, offerDashboardModel.visitchart.get(i).count));
            labels.add(offerDashboardModel.visitchart.get(i).day);
        }
        BarDataSet dataSet = new BarDataSet(entries, "Visits");

        // Customize dataset as needed
        dataSet.setBarBorderColor(ContextCompat.getColor(getContext(), R.color.colorPromo));
        dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorFeatured));
        dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Format value as integer
                return String.valueOf((int) value);
            }
        });

        BarData barData = new BarData(dataSet);
        binding.barChart.setData(barData);
        // Set visible range to show the last few entries
        int numEntries = entries.size();
        binding.barChart.setVisibleXRangeMaximum(7); // Maximum number of visible entries
        binding.barChart.moveViewToX(numEntries - 1); // Move view to the last entry
        binding.barChart.setVisibleXRange(0f, 7f);
        binding.barChart.getDescription().setEnabled(false);
        binding.barChart.getAxisRight().setEnabled(false);
//        binding.barChart.setDragEnabled(true);
        binding.barChart.setPinchZoom(false);

        // Customize X-axis
        XAxis xAxis = binding.barChart.getXAxis();
        xAxis.setTextSize(10f); // Set your desired label text size here
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = binding.barChart.getAxisLeft(); // Or getAxisRight() if needed
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Convert float value to integer
                return String.valueOf((int) value);
            }
        });
        yAxis.setDrawGridLines(false);

        // Customize legend
        Legend legend = binding.barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Set legend position
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Set legend position
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Set legend orientation
        legend.setDrawInside(false);
        legend.setEnabled(false); // Disable the legend

        binding.barChart.invalidate();
    }

    private void ageBarChart() {
        HorizontalBarChart horizontalBarChart = binding.ageBarChart;

        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < offerDashboardModel.agechart.size() ; i++) {
            // Add data to entries list
            entries.add(new BarEntry(i, offerDashboardModel.agechart.get(i).value));
            labels.add(offerDashboardModel.agechart.get(i).age);
        }

        BarDataSet barDataSet = new BarDataSet(entries, "ghvhg");

        // Customize dataset as needed
        barDataSet.setColor(getActivity().getColor(R.color.colorFeatured));
        barDataSet.setValueTextColor(Color.RED);

        BarData barData = new BarData(barDataSet);
        horizontalBarChart.setData(barData);
        horizontalBarChart.setExtraLeftOffset(16f); // Adjust left padding
        horizontalBarChart.setExtraRightOffset(16f); // Adjust right padding

        XAxis xl = horizontalBarChart.getXAxis();
//        xl.setLabelRotationAngle(45f);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setValueFormatter(new IndexAxisValueFormatter(labels));
//        xl.setTypeface(tfLight);
        xl.setTextSize(10f);
//        xl.setTextColor(Color.BLACK);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(1f);
        xl.setEnabled(true);

        YAxis yl = horizontalBarChart.getAxisLeft();
//        yl.setTypeface(tfLight);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);
        yl.setEnabled(false);

        YAxis yr = horizontalBarChart.getAxisRight();
//        yr.setTypeface(tfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);
        yr.setEnabled(false);

        // Set description label text
        horizontalBarChart.getDescription().setEnabled(false);
        // Hide right Y-axis
        horizontalBarChart.getAxisRight().setEnabled(false);
        // Customize legend
        horizontalBarChart.getLegend().setEnabled(false);

        // Refresh the chart
        horizontalBarChart.invalidate();
    }

    private void genderPieChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.genderProgressBar);
        Pie pie = AnyChart.pie();
        pie.legend(false);
        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
//                Toast.makeText(getActivity(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        if (offerDashboardModel.genderpiechart.size() > 0)
            binding.genderPercentageConstraint.setVisibility(View.VISIBLE);
        else
            binding.genderPercentageConstraint.setVisibility(View.GONE);

        List<DataEntry> data = new ArrayList<>();
        for (int i = 0; i < offerDashboardModel.genderpiechart.size(); i++) {
            if (i == 0) {
                binding.genderWomenValue.setText(getWomenPercentage(offerDashboardModel.genderpiechart.get(0).value, offerDashboardModel.genderpiechart.get(1).value)+"%");
            } else if (i == 1) {
                binding.genderMenValue.setText(getMenPercentage(offerDashboardModel.genderpiechart.get(0).value, offerDashboardModel.genderpiechart.get(1).value)+"%");
            }
            data.add(new ValueDataEntry(offerDashboardModel.genderpiechart.get(i).gender, offerDashboardModel.genderpiechart.get(i).value));
        }

        pie.data(data);
        //pie.title("Fruits imported in 2015 (in kg)");
        pie.labels().position("inside");
        pie.radius("100%");

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

    private void orderDetailsBar() {
        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < offerDashboardModel.orderdetails.size() ; i++) {
            // Add data to entries list
            entries.add(new BarEntry(i, offerDashboardModel.orderdetails.get(i).count));
            labels.add(offerDashboardModel.orderdetails.get(i).day);
        }
        BarDataSet dataSet = new BarDataSet(entries, "Visits");

        // Customize dataset as needed
        dataSet.setBarBorderColor(ContextCompat.getColor(getContext(), R.color.colorPromo));
        dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorFeatured));
        dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Format value as integer
                return String.valueOf((int) value);
            }
        });

        BarData barData = new BarData(dataSet);
        binding.orderBarChart.setData(barData);
        // Set visible range to show the last few entries
        int numEntries = entries.size();
        binding.orderBarChart.setVisibleXRangeMaximum(7); // Maximum number of visible entries
        binding.orderBarChart.moveViewToX(numEntries - 1); // Move view to the last entry
        binding.orderBarChart.setVisibleXRange(0f, 7f);
        binding.orderBarChart.setVisibleXRange(0f, 7f);
        binding.orderBarChart.getDescription().setEnabled(false);
        binding.orderBarChart.getAxisRight().setEnabled(false);

        // Customize X-axis
        XAxis xAxis = binding.orderBarChart.getXAxis();
        xAxis.setTextSize(10f); // Set your desired label text size here
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = binding.orderBarChart.getAxisLeft(); // Or getAxisRight() if needed
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Convert float value to integer
                return String.valueOf((int) value);
            }
        });
        yAxis.setDrawGridLines(false);

        // Customize legend
        Legend legend = binding.orderBarChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Set legend position
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Set legend position
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Set legend orientation
        legend.setDrawInside(false);
        legend.setEnabled(false); // Disable the legend

        binding.orderBarChart.invalidate();
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("offer_id", offerModel.offer_id);
        return requestData;
    }

    private void getOfferDashData() {
        commonViewModel.getMutableOfferDashData().observe(getViewLifecycleOwner(), offerDashboardModel -> {
            if (OfferDashboardFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (offerDashboardModel != null) {
                    if(offerDashboardModel.status.equals("success")) {
                        this.offerDashboardModel = offerDashboardModel;

                        initUI();
                        if (!offerDashboardModel.visitchart.isEmpty()) {
                            binding.barChartll.setVisibility(View.VISIBLE);
                            visitorDetailsBar();
                        } else {
                            binding.barChartll.setVisibility(View.GONE);
                        }
                        if (!offerDashboardModel.agechart.isEmpty()) {
                            binding.ageBarChartll.setVisibility(View.VISIBLE);
                            ageBarChart();
                        } else {
                            binding.ageBarChartll.setVisibility(View.GONE);
                        }
                        if (!offerDashboardModel.genderpiechart.isEmpty()) {
                            if (offerDashboardModel.genderpiechart.get(0).value != 0)
                                binding.genderPieChart.setVisibility(View.VISIBLE);
                            else
                                binding.genderPieChart.setVisibility(View.GONE);
                            genderPieChart();
                        } else {
                            binding.genderPieChart.setVisibility(View.GONE);
                        }
                        if (!offerDashboardModel.orderdetails.isEmpty()) {
                            binding.ordersBarChartLinear.setVisibility(View.VISIBLE);
                            orderDetailsBar();
                        } else
                            binding.ordersBarChartLinear.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Offer Dashboard");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (commonViewModel != null) {
            commonViewModel.getMutableOfferDashData().removeObservers(getViewLifecycleOwner());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableOfferDashData().removeObservers(getViewLifecycleOwner());
        }
    }

    private int getMenPercentage(int men, int women) {
        int totalCount = men + women;
        return (int) ((double) men / totalCount * 100);
    }

    private int getWomenPercentage(int men, int women) {
        int totalCount = men + women;
        return (int) ((double) women / totalCount * 100);
    }
}