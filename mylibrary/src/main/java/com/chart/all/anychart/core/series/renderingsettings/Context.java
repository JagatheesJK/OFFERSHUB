package com.chart.all.anychart.core.series.renderingsettings;

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
 * Rendering functions context.
 */
public class Context extends JsObject {

    protected Context() {

    }

    public static Context instantiate() {
        return new Context("new anychart.core.series.RenderingSettings.context()");
    }

    

    public Context(String jsChart) {
        jsBase = "context" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Fetch statistics value by its key or a whole object if no key provided.
     */
    public void getStat(String key) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".getStat(%s);", wrapQuotes(key)));
    }

}