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
 * Modified Moving Average (MMA) indicator class.
{docs:Stock_Charts/Technical_Indicators/Modified_Moving_Average_(MMA)}Learn more about the MMA indicator.{docs}
 */
public class MMA extends JsObject {

    protected MMA() {

    }

    public static MMA instantiate() {
        return new MMA("new anychart.core.stock.indicators.mMA()");
    }

    

    public MMA(String jsChart) {
        jsBase = "mMA" + ++variableIndex;
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
    public com.chart.all.anychart.core.stock.indicators.MMA period(Number period) {
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
    public com.chart.all.anychart.core.stock.indicators.MMA series(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator series type.
     */
    public com.chart.all.anychart.core.stock.indicators.MMA series(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", wrapQuotes(type)));

        return this;
    }

}