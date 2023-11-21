package com.chart.all.anychart.editor;

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
 * Step editor class.
 */
public class Step extends JsObject {

    protected Step() {

    }

    public static Step instantiate() {
        return new Step("new anychart.editor.step()");
    }

    

    public Step(String jsChart) {
        jsBase = "step" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for enabled state of the step.<br/>
<br>
<i> To work with the Chart Editor you need to reference the extension file from AnyChart CDN (example for the versioned file:
https://cdn.anychart.com/releases/8.4.0/js/anychart-editor.min.js)</i>
     */
    public void enabled() {
        APIlib.getInstance().addJSLine(jsBase + ".enabled();");
    }
    /**
     * Setter for enabled state of the step.<br/>
<br>
<i> To work with the Chart Editor you need to reference the extension file from AnyChart CDN (example for the versioned file:
https://cdn.anychart.com/releases/8.4.0/js/anychart-editor.min.js)</i>
     */
    public com.chart.all.anychart.editor.Step enabled(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".enabled(%s);", enabled));

        return this;
    }
    /**
     * Allows to enable/disable tab on the chart editor by name.<br/>
<br>
<i> To work with the Chart Editor you need to reference the extension file from AnyChart CDN (example for the versioned file:
https://cdn.anychart.com/releases/8.4.0/js/anychart-editor.min.js)</i>
     */
    public com.chart.all.anychart.editor.Step tab(com.chart.all.anychart.enums.EditorTabs tabName, Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tab(%s, %s);", (tabName != null) ? tabName.getJsBase() : null, value));

        return this;
    }
    /**
     * Allows to enable/disable tab on the chart editor by name.<br/>
<br>
<i> To work with the Chart Editor you need to reference the extension file from AnyChart CDN (example for the versioned file:
https://cdn.anychart.com/releases/8.4.0/js/anychart-editor.min.js)</i>
     */
    public com.chart.all.anychart.editor.Step tab(String tabName, Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tab(%s, %s);", wrapQuotes(tabName), value));

        return this;
    }

}