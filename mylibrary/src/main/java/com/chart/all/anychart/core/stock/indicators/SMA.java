package com.chart.all.anychart.core.stock.indicators;

import com.chart.all.anychart.APIlib;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.JsObject;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * Simple Moving Average (SMA) indicator class.
{docs:Stock_Charts/Technical_Indicators/Simple_Moving_Average_(SMA)}Learn more about the SMA indicator.{docs}
 */
public class SMA extends JsObject {

    protected SMA() {

    }

    public static SMA instantiate() {
        return new SMA("new anychart.core.stock.indicators.sMA()");
    }

    

    public SMA(String jsChart) {
        jsBase = "sMA" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the period.
     */
    public void period() {
        APIlib.getInstance().addJSLine(jsBase + ".period();");
    }
    /**
     * Setter for the period.
     */
    public com.chart.all.anychart.core.stock.indicators.SMA period(Number period) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".period(%s);", period));

        return this;
    }
    /**
     * Getter for the indicator SMA series instance.
     */
    public com.chart.all.anychart.core.stock.series.Base series() {
        return new com.chart.all.anychart.core.stock.series.Base(jsBase + ".series()");
    }
    /**
     * Setter for the indicator SMA series type.
     */
    public com.chart.all.anychart.core.stock.indicators.SMA series(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator SMA series type.
     */
    public com.chart.all.anychart.core.stock.indicators.SMA series(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", wrapQuotes(type)));

        return this;
    }

}