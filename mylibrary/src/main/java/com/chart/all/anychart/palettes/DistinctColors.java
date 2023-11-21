package com.chart.all.anychart.palettes;

import com.chart.all.anychart.APIlib;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.JsObject;
import com.chart.all.anychart.core.Base;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * Color palette class.
 */
public class DistinctColors extends Base {

    protected DistinctColors() {

    }

    public static DistinctColors instantiate() {
        return new DistinctColors("new anychart.palettes.distinctColors()");
    }

    

    public DistinctColors(String jsChart) {
        jsBase = "distinctColors" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for color palette colors from list by index.
     */
    public void itemAt(Number index) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".itemAt(%s);", index));
    }
    /**
     * Setter for color palette colors from list by index.
     */
    public com.chart.all.anychart.palettes.DistinctColors itemAt(Number index, com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".itemAt(%s, %s);", index, (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Getter for color palette colors list.
     */
    public void items() {
        APIlib.getInstance().addJSLine(jsBase + ".items();");
    }
    /**
     * Setter for color palette colors list.
     */
    public com.chart.all.anychart.palettes.DistinctColors items(com.chart.all.anychart.graphics.vector.Fill[] color, com.chart.all.anychart.graphics.vector.Fill var_args) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".items(%s, %s);", arrayToString(color), (var_args != null) ? var_args.getJsBase() : null));

        return this;
    }
    /**
     * Setter for color palette colors list.
     */
    public com.chart.all.anychart.palettes.DistinctColors items(com.chart.all.anychart.graphics.vector.Fill color, com.chart.all.anychart.graphics.vector.Fill var_args) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".items(%s, %s);", (color != null) ? color.getJsBase() : null, (var_args != null) ? var_args.getJsBase() : null));

        return this;
    }
    /**
     * Removes all listeners from an object. You can also optionally remove listeners of some particular type.
     */
    public void removeAllListeners(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeAllListeners(%s);", wrapQuotes(type)));
    }
    public void setOnClickListener(com.chart.all.anychart.chart.common.listener.ListenersInterface.OnClickListener listener) {
        StringBuilder js = new StringBuilder();

        js.append(jsBase).append(".listen('pointClick', function(e) {");

        if (listener.getFields() != null) {
            js.append("var result = ");
            for (String field : listener.getFields()) {
                js.append(String.format(Locale.US, "'%1$s' + ':' + e.point.get('%1$s') + ',' +", field));
            }
            js.setLength(js.length() - 8);
            js.append(";");

            js.append("android.onClick(result);");
        } else {
            js.append("android.onClick(null);");
        }
        js.append("});");

        com.chart.all.anychart.chart.common.listener.ListenersInterface.getInstance().setOnClickListener(listener);

        APIlib.getInstance().addJSLine(js.toString());
    }

    public void setOnClickListener(com.chart.all.anychart.chart.common.listener.ListenersInterface.OnClickListener listener, String type, String ePath) {
        StringBuilder js = new StringBuilder();

        js.append(jsBase).append(String.format(Locale.US, ".listen('%1$s', function(e) {", type));

        if (listener.getFields() != null) {
            ePath = (ePath != null) ? ePath + "." : "";
            js.append("var result = ");
            for (String field : listener.getFields()) {
                js.append(String.format(Locale.US, "'%1$s' + ':' + e.%2$s%1$s + ',' +", field, ePath));
            }
            js.setLength(js.length() - 8);
            js.append(";");

            js.append("android.onClick(result);");
        } else {
            js.append("android.onClick(null);");
        }
        js.append("});");

        com.chart.all.anychart.chart.common.listener.ListenersInterface.getInstance().setOnClickListener(listener);

        APIlib.getInstance().addJSLine(js.toString());
    }
    /**
     * Removes an event listener which was added with listen() by the key returned by listen() or listenOnce().
     */
    public void unlistenByKey(String key) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".unlistenByKey(%s);", wrapQuotes(key)));
    }

}