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

    private void visitorDetailsBar() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.visitorProgressBar);
        Cartesian cartesian = AnyChart.column();

        if (shopDashboardModel.visitchart.size() > 7) {
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
        for (int i=0; i < shopDashboardModel.visitchart.size(); i++) {
            data.add(new ValueDataEntry(shopDashboardModel.visitchart.get(i).day, shopDashboardModel.visitchart.get(i).count));
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
        for (int i=0; i < shopDashboardModel.agechart.size(); i++) {
            data.add(new ValueDataEntry(shopDashboardModel.agechart.get(i).age, shopDashboardModel.agechart.get(i).value));
        }

        Set set = Set.instantiate();
        set.data(data);
        Mapping barData = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping jumpLineData = set.mapAs("{ x: 'x', value: 'jumpLine' }");

        Bar bar = vertical.bar(barData);
        bar.color("#387ADF");
//        bar.labels().format("${%Value} mln");

        /*JumpLine jumpLine = vertical.jumpLine(jumpLineData);
        jumpLine.stroke("2 #60727B");
        jumpLine.labels().enabled(false);*/

        vertical.yScale().minimum(0d);

        vertical.labels(false);

        /*vertical.tooltip()
                .displayMode(TooltipDisplayMode.UNION)
                .positionMode(TooltipPositionMode.POINT)
                .unionFormat(
                        "function() {\n" +
                                "      return 'Plain: $' + this.points[1].value + ' mln' +\n" +
                                "        '\\n' + 'Fact: $' + this.points[0].value + ' mln';\n" +
                                "    }");*/

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

    private void orderDetailsBar() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.orderProgressBar);
        Cartesian cartesian = AnyChart.column();

        if (shopDashboardModel.orderdetails.size() > 7) {
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
        for (int i=0; i < shopDashboardModel.orderdetails.size(); i++) {
            data.add(new ValueDataEntry(shopDashboardModel.orderdetails.get(i).day, shopDashboardModel.orderdetails.get(i).count));
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
                        if (!shopDashboardModel.visitchart.isEmpty()) {
                            binding.barChartll.setVisibility(View.VISIBLE);
                            visitorDetailsBar();
                        } else {
                            binding.barChartll.setVisibility(View.GONE);
                        }
                        if (!shopDashboardModel.agechart.isEmpty()) {
                            binding.ageChartView.setVisibility(View.VISIBLE);
                            ageBarChart();
                        } else {
                            binding.ageChartView.setVisibility(View.GONE);
                        }
                        if (!shopDashboardModel.genderpiechart.isEmpty()) {
                            binding.genderPieChart.setVisibility(View.VISIBLE);
                            genderPieChart();
                        } else {
                            binding.genderPieChart.setVisibility(View.GONE);
                        }
                        if (!shopDashboardModel.orderdetails.isEmpty()) {
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