package com.hub.offershub.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chart.all.anychart.AnyChart;
import com.chart.all.anychart.AnyChartView;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.chart.common.dataentry.ValueDataEntry;
import com.chart.all.anychart.chart.common.listener.Event;
import com.chart.all.anychart.chart.common.listener.ListenersInterface;
import com.chart.all.anychart.charts.Cartesian;
import com.chart.all.anychart.charts.Pie;
import com.chart.all.anychart.core.cartesian.series.Column;
import com.chart.all.anychart.core.cartesian.series.Line;
import com.chart.all.anychart.data.Mapping;
import com.chart.all.anychart.data.Set;
import com.chart.all.anychart.enums.Anchor;
import com.chart.all.anychart.enums.HoverMode;
import com.chart.all.anychart.enums.MarkerType;
import com.chart.all.anychart.enums.Position;
import com.chart.all.anychart.enums.TooltipPositionMode;
import com.chart.all.anychart.graphics.vector.Stroke;
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
import com.hub.offershub.databinding.FragmentOfferDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class OfferDashboardFragment extends Fragment {

    private FragmentOfferDashboardBinding binding;

    public static OfferDashboardFragment newInstance() {
        OfferDashboardFragment fragment = new OfferDashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOfferDashboardBinding.inflate(getLayoutInflater());
        newPieChart();
        newMultipleBarChart();
        newLineChart();
        return binding.getRoot();
    }

    private void newPieChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
        chart1.setProgressBar(binding.progressBar);

        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(getActivity(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Apples", 6371664));
        data.add(new ValueDataEntry("Pears", 789622));
        data.add(new ValueDataEntry("Bananas", 7216301));
        data.add(new ValueDataEntry("Grapes", 1486621));
        data.add(new ValueDataEntry("Oranges", 1200000));

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
        binding.anyChartView.addView(chart1);
    }

    private void newLineChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
//        AnyChartView anyChartView = binding.lineChart1;
        chart1.setProgressBar(binding.lineProgressBar);

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        // cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.");

        cartesian.yAxis(0).title("Number of Bottles Sold (thousands)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1986", 3.6, 2.3, 2.8));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1987", 7.1, 4.0, 4.1));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1988", 8.5, 6.2, 5.1));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1989", 9.2, 11.8, 6.5));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1990", 10.1, 13.0, 12.5));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1991", 11.6, 13.9, 18.0));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1992", 16.4, 18.0, 21.0));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1993", 18.0, 23.3, 20.3));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1994", 13.2, 24.7, 19.2));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1995", 12.0, 18.0, 14.4));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1996", 3.2, 15.1, 9.2));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1997", 4.1, 11.3, 5.9));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1998", 6.3, 14.2, 5.2));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("1999", 9.4, 13.7, 4.7));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2000", 11.5, 9.9, 4.2));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2001", 13.5, 12.1, 1.2));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2002", 14.8, 13.5, 5.4));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2003", 16.6, 15.1, 6.3));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2004", 18.1, 17.9, 8.9));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2005", 17.0, 18.9, 10.1));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2006", 16.6, 20.3, 11.5));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2007", 14.1, 20.7, 12.2));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2008", 15.7, 21.6, 10));
        seriesData.add(new OfferDashboardFragment.CustomDataEntry("2009", 12.0, 22.5, 8.9));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Brandy");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Whiskey");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Tequila");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        cartesian.draw(true);
//        anyChartView.setVisibility(View.VISIBLE);
        chart1.setChart(cartesian);
        binding.lineChart1.addView(chart1);
    }

    private void newMultipleBarChart() {
        AnyChartView chart1 = new AnyChartView(requireContext());
//        binding.anyChartView1.addView(chart1);
        chart1.setProgressBar(binding.progressBar);

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Rouge", 80540));
        data.add(new ValueDataEntry("Foundation", 94190));
        data.add(new ValueDataEntry("Mascara", 102610));
        data.add(new ValueDataEntry("Lip gloss", 110430));
        data.add(new ValueDataEntry("Lipstick", 128000));
        data.add(new ValueDataEntry("Nail polish", 143760));
        data.add(new ValueDataEntry("Eyebrow pencil", 170670));
        data.add(new ValueDataEntry("Eyeliner", 213210));
        data.add(new ValueDataEntry("Eyeshadows", 249980));

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

        cartesian.xAxis(0).title("Product");
        cartesian.yAxis(0).title("Revenue");

        cartesian.draw(true);
        binding.anyChartView1.setVisibility(View.VISIBLE);
        chart1.setChart(cartesian);
        binding.anyChartView1.addView(chart1);
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }
}