package com.chart.all.anychart.core.stock.indicators;

import com.chart.all.anychart.APIlib;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.JsObject;
import com.chart.all.anychart.core.stock.indicators.Base;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * Awesome Oscillator (AO) indicator class
{docs:Stock_Charts/Technical_Indicators/Adaptive_Moving_Average_(AMA)}Learn more about the AMA indicator.{docs}
 */
public class AO extends Base {

    protected AO() {

    }

    public static AO instantiate() {
        return new AO("new anychart.core.stock.indicators.aO()");
    }

    

    public AO(String jsChart) {
        jsBase = "aO" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the fast period.
     */
    public void fastPeriod() {
        APIlib.getInstance().addJSLine(jsBase + ".fastPeriod();");
    }
    /**
     * Setter for the fast period.
     */
    public com.chart.all.anychart.core.stock.indicators.AO fastPeriod(Number period) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fastPeriod(%s);", period));

        return this;
    }
    /**
     * Getter for the indicator smoothing type.
     */
    public void maType() {
        APIlib.getInstance().addJSLine(jsBase + ".maType();");
    }
    /**
     * Setter for the indicator smoothing type.
     */
    public com.chart.all.anychart.core.stock.indicators.AO maType(com.chart.all.anychart.enums.MovingAverageType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maType(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator smoothing type.
     */
    public com.chart.all.anychart.core.stock.indicators.AO maType(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maType(%s);", wrapQuotes(type)));

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
    public com.chart.all.anychart.core.stock.indicators.AO series(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator series type.
     */
    public com.chart.all.anychart.core.stock.indicators.AO series(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", wrapQuotes(type)));

        return this;
    }
    /**
     * Getter for the slow period.
     */
    public void slowPeriod() {
        APIlib.getInstance().addJSLine(jsBase + ".slowPeriod();");
    }
    /**
     * Setter for the slow period.
     */
    public com.chart.all.anychart.core.stock.indicators.AO slowPeriod(Number period) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".slowPeriod(%s);", period));

        return this;
    }

}