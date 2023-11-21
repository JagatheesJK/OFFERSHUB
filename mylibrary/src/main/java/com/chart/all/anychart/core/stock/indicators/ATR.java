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
 * Average True Range (ATR) indicator class.
{docs:Stock_Charts/Technical_Indicators/Average_True_Range_(ATR)}Learn more about the Average True Range indicator.{docs}
 */
public class ATR extends JsObject {

    protected ATR() {

    }

    public static ATR instantiate() {
        return new ATR("new anychart.core.stock.indicators.aTR()");
    }

    

    public ATR(String jsChart) {
        jsBase = "aTR" + ++variableIndex;
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
    public com.chart.all.anychart.core.stock.indicators.ATR period(Number period) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".period(%s);", period));

        return this;
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
    public com.chart.all.anychart.core.stock.indicators.ATR series(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator series type.
     */
    public com.chart.all.anychart.core.stock.indicators.ATR series(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", wrapQuotes(type)));

        return this;
    }

}