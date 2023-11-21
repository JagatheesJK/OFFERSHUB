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
 * Momentum indicator class.
 */
public class Momentum extends JsObject {

    protected Momentum() {

    }

    public static Momentum instantiate() {
        return new Momentum("new anychart.core.stock.indicators.momentum()");
    }

    

    public Momentum(String jsChart) {
        jsBase = "momentum" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the indicator period.
     */
    public void period() {
        APIlib.getInstance().addJSLine(jsBase + ".period();");
    }
    /**
     * Setter for the indicator period.
     */
    public com.chart.all.anychart.core.stock.indicators.MFI period(Number period) {
        return new com.chart.all.anychart.core.stock.indicators.MFI(String.format(Locale.US, jsBase + ".period(%s)", period));
    }
    /**
     * Getter for the indicator series instance.
     */
    public com.chart.all.anychart.core.stock.series.Base series() {
        return new com.chart.all.anychart.core.stock.series.Base(jsBase + ".series()");
    }
    /**
     * Setter for the indicator series type.
     */
    public com.chart.all.anychart.core.stock.indicators.MFI series(com.chart.all.anychart.enums.StockSeriesType type) {
        return new com.chart.all.anychart.core.stock.indicators.MFI(String.format(Locale.US, jsBase + ".series(%s)", (type != null) ? type.getJsBase() : null));
    }
    /**
     * Setter for the indicator series type.
     */
    public com.chart.all.anychart.core.stock.indicators.MFI series(String type) {
        return new com.chart.all.anychart.core.stock.indicators.MFI(String.format(Locale.US, jsBase + ".series(%s)", wrapQuotes(type)));
    }

}