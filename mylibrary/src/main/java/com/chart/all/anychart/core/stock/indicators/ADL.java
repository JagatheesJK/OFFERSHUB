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
 * Accumulation Distribution Line (ADL) indicator class.
{docs:Stock_Charts/Technical_Indicators/Accumulation_Distribution_Line_(ADL)}Learn more about the ADL indicator.{docs}
 */
public class ADL extends JsObject {

    protected ADL() {

    }

    public static ADL instantiate() {
        return new ADL("new anychart.core.stock.indicators.aDL()");
    }

    

    public ADL(String jsChart) {
        jsBase = "aDL" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
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
    public com.chart.all.anychart.core.stock.indicators.ADL series(com.chart.all.anychart.enums.StockSeriesType type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", (type != null) ? type.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the indicator series type.
     */
    public com.chart.all.anychart.core.stock.indicators.ADL series(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".series(%s);", wrapQuotes(type)));

        return this;
    }

}