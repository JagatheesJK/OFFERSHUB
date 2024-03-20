package com.hub.offershub.fragment;

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
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
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

        binding.swipeRefresh.setOnRefreshListener(() -> {
            binding.swipeRefresh.setRefreshing(false);
            commonViewModel.getShopsDashData(makeRequest(), myProgressDialog);
            getShopsDashData();
        });
        return binding.getRoot();
    }

    private void initUI() {
        binding.totalFavotiteTxt.setText(""+shopDashboardModel.data.totalfavorite);
        binding.totalOffersVisitTxt.setText(""+shopDashboardModel.data.offervisitcount);
        binding.totalShopVisitTxt.setText(""+shopDashboardModel.data.shopvisitcount);
        binding.contactVisitTxt.setText(""+shopDashboardModel.data.contactvisitcount);
        binding.mapVisitTxt.setText(""+shopDashboardModel.data.mapvisitcount);
        binding.totalOrderCountTxt.setText(""+shopDashboardModel.data.totalorders);
        binding.repeatUserVisitTxt.setText(""+shopDashboardModel.data.repeatedusers);
        binding.totalUserVisitTxt.setText(""+shopDashboardModel.data.totalusers);

        ratingData();
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
        pie.legend(false);
        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
//                Toast.makeText(getActivity(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        if (shopDashboardModel.genderpiechart.size() > 0)
            binding.genderPercentageConstraint.setVisibility(View.VISIBLE);
        else
            binding.genderPercentageConstraint.setVisibility(View.GONE);

        List<DataEntry> data = new ArrayList<>();
        for (int i = 0; i < shopDashboardModel.genderpiechart.size(); i++) {
            if (i == 0) {
                binding.genderWomenValue.setText(getWomenPercentage(shopDashboardModel.genderpiechart.get(0).value, shopDashboardModel.genderpiechart.get(1).value)+"%");
            } else if (i == 1) {
                binding.genderMenValue.setText(getMenPercentage(shopDashboardModel.genderpiechart.get(0).value, shopDashboardModel.genderpiechart.get(1).value)+"%");
            }
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

    private void ratingData() {
        binding.ratingTotalTxt.setText(""+shopDashboardModel.data.avgrating);
        binding.totalRatingCountTxt.setText(""+shopDashboardModel.data.usersrated);
        binding.ratingTotalStar.setRating(Float.parseFloat(shopDashboardModel.data.avgrating));
        binding.rating5Txt.setText(""+shopDashboardModel.ratingdata.rating5);
        binding.rating4Txt.setText(""+shopDashboardModel.ratingdata.rating4);
        binding.rating3Txt.setText(""+shopDashboardModel.ratingdata.rating3);
        binding.rating2Txt.setText(""+shopDashboardModel.ratingdata.rating2);
        binding.rating1Txt.setText(""+shopDashboardModel.ratingdata.rating1);
    }

    private void ordersDetailsBar() {
        binding.orderBarChart.setVisibility(View.VISIBLE);
        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < shopDashboardModel.orderdetails.size() ; i++) {
            // Add data to entries list
            entries.add(new BarEntry(i, shopDashboardModel.orderdetails.get(i).count));
            labels.add(shopDashboardModel.orderdetails.get(i).day);
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

        binding.orderBarChart.invalidate();
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shop_id", businessModel.id);
//        requestData.put("shop_id", 16);
        return requestData;
    }

    private void getShopsDashData() {
        commonViewModel.getMutableShopDashData().observe(getViewLifecycleOwner(), shopDashboardModel -> {
            if (ShopDashboardFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (shopDashboardModel != null) {
                    if(shopDashboardModel.status.equals("success")) {
                        this.shopDashboardModel = shopDashboardModel;

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
                            dataSet.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value) {
                                    // Format value as integer
                                    return String.valueOf((int) value);
                                }
                            });
                            BarData barData = new BarData(dataSet);
                            binding.ageBarChart.setData(barData);
                            binding.ageBarChart.setVisibleXRange(0f, 6f);
                            binding.ageBarChart.getDescription().setEnabled(false);
                            binding.ageBarChart.getAxisRight().setEnabled(false);

                            // Customize X-axis
                            XAxis xAxis = binding.ageBarChart.getXAxis();
                            xAxis.setTextSize(10f); // Set your desired label text size here
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                            xAxis.setGranularity(1f);
                            xAxis.setDrawGridLines(false);
                            YAxis yAxis = binding.ageBarChart.getAxisLeft(); // Or getAxisRight() if needed
                            yAxis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value) {
                                    // Convert float value to integer
                                    return String.valueOf((int) value);
                                }
                            }); yAxis.setDrawGridLines(false);

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
                            dataSet.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value) {
                                    // Format value as integer
                                    return String.valueOf((int) value);
                                }
                            });

                            BarData barData = new BarData(dataSet);
                            binding.barChart.setData(barData);
                            binding.barChart.setVisibleXRange(0f, 7f);
                            binding.barChart.getDescription().setEnabled(false);
                            binding.barChart.getAxisRight().setEnabled(false);

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

                            binding.barChart.invalidate();
                            //newMultipleBarChart();
                        } else
                            binding.barChart.setVisibility(View.GONE);
                        if (!shopDashboardModel.orderdetails.isEmpty()) {
                            binding.ordersBarChartLinear.setVisibility(View.VISIBLE);
                            ordersDetailsBar();
                        } else
                            binding.ordersBarChartLinear.setVisibility(View.GONE);
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
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Shop Dashboard");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonViewModel != null) {
            commonViewModel.getMutableShopDashData().removeObservers(getViewLifecycleOwner());
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