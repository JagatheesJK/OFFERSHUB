package com.chart.all.anychart.ui;

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
 * The RangePicker class contains methods for configuring range picker.
 */
public class RangePicker extends JsObject {

    protected RangePicker() {

    }

    public static RangePicker instantiate() {
        return new RangePicker("new anychart.ui.rangePicker()");
    }

    

    public RangePicker(String jsChart) {
        jsBase = "rangePicker" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Disposes range picker.<br/>
Removes it from parent layer, nulls links, removes from DOM.
     */
    public void dispose() {
        APIlib.getInstance().addJSLine(jsBase + ".dispose();");
    }
    /**
     * Getter for the input and output date time format.
     */
    public void format() {
        APIlib.getInstance().addJSLine(jsBase + ".format();");
    }
    /**
     * Setter for the input and output date time format.
     */
    public void format(String format) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".format(%s);", wrapQuotes(format)));
    }
    /**
     * Getter for the text for 'from'-label.
     */
    public void fromLabelText() {
        APIlib.getInstance().addJSLine(jsBase + ".fromLabelText();");
    }
    /**
     * Setter for the text for 'from'-label.
     */
    public com.chart.all.anychart.ui.RangePicker fromLabelText(String text) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fromLabelText(%s);", wrapQuotes(text)));

        return this;
    }
    /**
     * Returns the editable DOM element or null if this field.
     */
    public void getElement() {
        APIlib.getInstance().addJSLine(jsBase + ".getElement();");
    }
    /**
     * Renders the range picker.
     */
    public void render(com.chart.all.anychart.charts.Stock parentElement) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".render(%s);", (parentElement != null) ? parentElement.getJsBase() : null));
    }
    /**
     * Sets stock chart for range picker.
     */
    public void target(com.chart.all.anychart.charts.Stock chart) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".target(%s);", (chart != null) ? chart.getJsBase() : null));
    }
    /**
     * Getter for the text for 'to'-label.
     */
    public void toLabelText() {
        APIlib.getInstance().addJSLine(jsBase + ".toLabelText();");
    }
    /**
     * Setter for the text for 'to'-label.
     */
    public com.chart.all.anychart.ui.RangePicker toLabelText(String text) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toLabelText(%s);", wrapQuotes(text)));

        return this;
    }

}