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
 * The GanttToolbar class contains methods for configuring toolbar.<br/>
Toolbar is a part of Gantt chart. Toolbar contains menu Print with options print A4 - A0, buttons ZoomIn, ZoomOut,
FitAll, ExpandAll/CollapseAll and menu with option Save As (SVG, PNG, JPG, PDF).
 */
public class GanttToolbar extends JsObject {

    protected GanttToolbar() {

    }

    public static GanttToolbar instantiate() {
        return new GanttToolbar("new anychart.ui.ganttToolbar()");
    }

    

    public GanttToolbar(String jsChart) {
        jsBase = "ganttToolbar" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the Gantt chart toolbar container.
     */
    public void container() {
        APIlib.getInstance().addJSLine(jsBase + ".container();");
    }
    /**
     * Setter for the Gantt chart toolbar container.
     */
    public com.chart.all.anychart.ui.GanttToolbar container(String element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", wrapQuotes(element)));

        return this;
    }
    /**
     * Draws Gantt chart toolbar.
     */
    public com.chart.all.anychart.ui.GanttToolbar draw() {
        APIlib.getInstance().addJSLine(jsBase + ".draw();");

        return this;
    }
    /**
     * Getter for the print paper sizes.
     */
    public void printPaperSizes() {
        APIlib.getInstance().addJSLine(jsBase + ".printPaperSizes();");
    }
    /**
     * Setter for the print paper sizes.
     */
    public com.chart.all.anychart.ui.GanttToolbar printPaperSizes(com.chart.all.anychart.graphics.vector.PaperSize[] paperSizeList) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".printPaperSizes(%s);", arrayToString(paperSizeList)));

        return this;
    }
    /**
     * Getter for the toolbar target.
     */
    public com.chart.all.anychart.core.Chart target() {
        return new com.chart.all.anychart.core.Chart(jsBase + ".target()");
    }
    /**
     * Setter for the toolbar target.
     */
    public com.chart.all.anychart.ui.GanttToolbar target(com.chart.all.anychart.core.Chart target) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".target(%s);", (target != null) ? target.getJsBase() : null));

        return this;
    }

}