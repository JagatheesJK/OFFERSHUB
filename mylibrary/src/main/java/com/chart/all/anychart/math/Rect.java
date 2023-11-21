package com.chart.all.anychart.math;

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
 * Define rectangle.
This class is an alias of the {@link anychart.graphics.math.Rect} class.
 */
public class Rect extends JsObject {

    protected Rect() {

    }

    public static Rect instantiate() {
        return new Rect("new anychart.math.rect()");
    }

    

    public Rect(String jsChart) {
        jsBase = "rect" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    

}