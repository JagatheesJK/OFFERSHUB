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
 * Keltner Channels indicator class.
 */
public class KeltnerChannels extends JsObject {

    protected KeltnerChannels() {

    }

    public static KeltnerChannels instantiate() {
        return new KeltnerChannels("new anychart.core.stock.indicators.keltnerChannels()");
    }

    

    public KeltnerChannels(String jsChart) {
        jsBase = "keltnerChannels" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the Average True Range period.
     */
    public void atrPeriod() {
        APIlib.getInstance().addJSLine(jsBase + ".atrPeriod();");
    }
    /**
     * Setter for the Average True Range period.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels atrPeriod(Number period) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".atrPeriod(%s);", period));

        return this;
    }
    /**
     * Getter for the Moving Average period.
     */
    public void maPeriod() {
        APIlib.getInstance().addJSLine(jsBase + ".maPeriod();");
    }
    /**
     * Setter for the Moving Average period.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels maPeriod(Number period) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maPeriod(%s);", period));

        return this;
    }
    /**
     * Getter for the Moving Average series instance.
     */
    public com.chart.all.anychart.core.stock.series.Base maSeries() {
        return new com.chart.all.anychart.core.stock.series.Base(jsBase + ".maSeries()");
    }
    /**
     * Setter for the Moving Average series type.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels maSeries(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maSeries(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the Moving Average series type.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels maSeries(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maSeries(%s);", wrapQuotes(type)));

        return this;
    }
    /**
     * Getter for the multiplier.
     */
    public void multiplier() {
        APIlib.getInstance().addJSLine(jsBase + ".multiplier();");
    }
    /**
     * Setter for the multiplier.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels multiplier(Number multiplier) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".multiplier(%s);", multiplier));

        return this;
    }
    /**
     * Getter for the indicator Range Series instance.
     */
    public com.chart.all.anychart.core.stock.series.Base rangeSeries() {
        return new com.chart.all.anychart.core.stock.series.Base(jsBase + ".rangeSeries()");
    }
    /**
     * Setter for the indicator Range Series type.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels rangeSeries(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeSeries(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator Range Series type.
     */
    public com.chart.all.anychart.core.stock.indicators.KeltnerChannels rangeSeries(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeSeries(%s);", wrapQuotes(type)));

        return this;
    }

}