package com.hub.offershub.fragment;

import android.os.Bundle;

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
import com.chart.all.anychart.core.cartesian.series.Bar;
import com.chart.all.anychart.core.cartesian.series.Column;
import com.chart.all.anychart.data.Mapping;
import com.chart.all.anychart.data.Set;
import com.chart.all.anychart.enums.Anchor;
import com.chart.all.anychart.enums.HoverMode;
import com.chart.all.anychart.enums.Position;
import com.chart.all.anychart.enums.TooltipPositionMode;
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
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.visitorProgressBar);
        Cartesian cartesian = AnyChart.column();

        if (offerDashboardModel.visitchart.size() > 7) {
            cartesian.xScroller(true);
            cartesian.xScroller().orientation("top");
            cartesian.xScroller().thumbs(true);
            cartesian.xScroller().autoHide(true);
        }

        // set the bar height
        cartesian.xScroller().minHeight(2);
        cartesian.xScroller().maxHeight(35);

        // prevent the range changing
        cartesian.xScroller().allowRangeChange(false);
        cartesian.pointWidth(40);
        cartesian.xZoom().setToPointsCount(7, true, null);

        List<DataEntry> data = new ArrayList<>();
        for (int i=0; i < offerDashboardModel.visitchart.size(); i++) {
            data.add(new ValueDataEntry(offerDashboardModel.visitchart.get(i).day, offerDashboardModel.visitchart.get(i).count));
        }

        Column column = cartesian.column(data);
        column.color("#387ADF");
//        column.labels(true); // For bar value showing
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(false);
        //cartesian.title("Top 10 Cosmetic Products by Revenue");
        cartesian.yScale().minimum(0d);
        //cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");
        cartesian.xAxis(0).labels().fontSize(11).adjustFontSize(true);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

//        cartesian.xAxis(0).title("Day");
//        cartesian.yAxis(0).title("Value");

        cartesian.draw(true);
        binding.visitorBarChart.setVisibility(View.VISIBLE);
        chart1.setChart(cartesian);
        binding.visitorBarChart.addView(chart1);
    }

    private void ageBarChart() {
        AnyChartView anyChartView = new AnyChartView(requireContext());
        anyChartView.setProgressBar(binding.ageProgressBar);

        Cartesian vertical = AnyChart.vertical();
//        vertical.xScroller(true);
//        vertical.xScroller().orientation("top");
//        vertical.xScroller().thumbs(false);

        // prevent the range changing
//        vertical.xScroller().allowRangeChange(false);
        vertical.pointWidth(25);
//        vertical.xZoom().setToPointsCount(3, true, null);

        /*vertical.animation(true)
                .title("Vertical Combination of Bar and Jump Line Chart");*/
        List<DataEntry> data = new ArrayList<>();
        for (int i=0; i < offerDashboardModel.agechart.size(); i++) {
            data.add(new ValueDataEntry(offerDashboardModel.agechart.get(i).age, offerDashboardModel.agechart.get(i).value));
        }

        Set set = Set.instantiate();
        set.data(data);
        Mapping barData = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping jumpLineData = set.mapAs("{ x: 'x', value: 'jumpLine' }");

        Bar bar = vertical.bar(barData);
        bar.color("#387ADF");
//        bar.labels().format("${%Value} mln");

        vertical.yScale().minimum(0d);

        vertical.labels(false);

        vertical.interactivity().hoverMode(HoverMode.BY_X);

        vertical.xAxis(true);
        vertical.yAxis(false);
        vertical.yAxis(0).labels().format("${%Value} mln");

        anyChartView.setChart(vertical);
        binding.ageChartView.addView(anyChartView);
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
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.orderProgressBar);
        Cartesian cartesian = AnyChart.column();

        if (offerDashboardModel.orderdetails.size() > 7) {
            cartesian.xScroller(true);
            cartesian.xScroller().orientation("top");
            cartesian.xScroller().thumbs(true);
            cartesian.xScroller().autoHide(true);
        }

        // prevent the range changing
        cartesian.xScroller().allowRangeChange(false);
        cartesian.pointWidth(40);
        cartesian.xZoom().setToPointsCount(7, true, null);

        List<DataEntry> data = new ArrayList<>();
        for (int i=0; i < offerDashboardModel.orderdetails.size(); i++) {
            data.add(new ValueDataEntry(offerDashboardModel.orderdetails.get(i).day, offerDashboardModel.orderdetails.get(i).count));
        }

        Column column = cartesian.column(data);
        column.color("#387ADF");
//        column.labels(true); // For bar value showing
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        //cartesian.title("Top 10 Cosmetic Products by Revenue");
        cartesian.yScale().minimum(0d);
        //cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");
        cartesian.xAxis(0).labels().fontSize(11).adjustFontSize(true);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

//        cartesian.xAxis(0).title("Day");
//        cartesian.yAxis(0).title("Value");

        cartesian.draw(true);
        binding.orderBarChart1.setVisibility(View.VISIBLE);
        chart1.setChart(cartesian);
        binding.orderBarChart1.addView(chart1);
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
                            binding.ageChartView.setVisibility(View.VISIBLE);
                            ageBarChart();
                        } else {
                            binding.ageChartView.setVisibility(View.GONE);
                        }
                        if (!offerDashboardModel.genderpiechart.isEmpty()) {
                            binding.genderPieChart.setVisibility(View.VISIBLE);
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