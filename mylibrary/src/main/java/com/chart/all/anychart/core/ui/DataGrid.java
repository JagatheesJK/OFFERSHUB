package com.chart.all.anychart.core.ui;

import com.chart.all.anychart.APIlib;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.JsObject;
import com.chart.all.anychart.core.VisualBaseWithBounds;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * Data grid element.<br/>
The Data grid is a part of Gantt chart but it also can be used independently.
 */
public class DataGrid extends VisualBaseWithBounds {

    protected DataGrid() {

    }

    public static DataGrid instantiate() {
        return new DataGrid("new anychart.core.ui.dataGrid()");
    }

    

    public DataGrid(String jsChart) {
        jsBase = "dataGrid" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the background fill.
     */
    public void backgroundFill() {
        APIlib.getInstance().addJSLine(jsBase + ".backgroundFill();");
    }
    /**
     * Setter for background fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for background fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for background fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Background fill color with opacity.
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient background fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid backgroundFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for element bottom bound settings.
     */
    public void bottom() {
        APIlib.getInstance().addJSLine(jsBase + ".bottom();");
    }
    /**
     * Setter for element bottom bound settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bottom(Number bottom) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bottom(%s);", bottom));

        return this;
    }
    /**
     * Setter for element bottom bound settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bottom(String bottom) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bottom(%s);", wrapQuotes(bottom)));

        return this;
    }
    /**
     * Getter for element bounds settings.
     */
    public com.chart.all.anychart.core.utils.Bounds bounds() {
        return new com.chart.all.anychart.core.utils.Bounds(jsBase + ".bounds()");
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(com.chart.all.anychart.utils.RectObj bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(com.chart.all.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(com.chart.all.anychart.core.utils.Bounds bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(Number x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid bounds(String x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for button settings.
     */
    public com.chart.all.anychart.core.gantt.DataGridButton buttons() {
        return new com.chart.all.anychart.core.gantt.DataGridButton(jsBase + ".buttons()");
    }
    /**
     * Setter for button settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid buttons(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".buttons(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for column by index.<br/>
Gets column by index or creates a new one if column doesn't exist yet.
     */
    public com.chart.all.anychart.core.ui.datagrid.Column column(Number index) {
        return new com.chart.all.anychart.core.ui.datagrid.Column(String.format(Locale.US, jsBase + ".column(%s)", index));
    }
    /**
     * Setter for the first column.
     */
    public com.chart.all.anychart.core.ui.DataGrid column(com.chart.all.anychart.core.ui.datagrid.Column settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".column(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the first column.
     */
    public com.chart.all.anychart.core.ui.DataGrid column(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".column(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the column by index.
     */
    public com.chart.all.anychart.core.ui.DataGrid column(Number index, com.chart.all.anychart.core.ui.datagrid.Column settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".column(%s, %s);", index, (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the column by index.
     */
    public com.chart.all.anychart.core.ui.DataGrid column(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".column(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the column stroke.
     */
    public void columnStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".columnStroke();");
    }
    /**
     * Setter for the column stroke.
     */
    public com.chart.all.anychart.core.ui.DataGrid columnStroke(com.chart.all.anychart.graphics.vector.Stroke color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".columnStroke(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the column stroke.
     */
    public com.chart.all.anychart.core.ui.DataGrid columnStroke(String color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".columnStroke(%s);", wrapQuotes(color)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.data.Tree data(List<DataEntry> data) {
        return new com.chart.all.anychart.data.Tree(String.format(Locale.US, jsBase + ".data(%s)", arrayToString(data)));
    }
    /**
     * Draws data grid.
     */
    public com.chart.all.anychart.core.ui.DataGrid draw() {
        APIlib.getInstance().addJSLine(jsBase + ".draw();");

        return this;
    }
    /**
     * Getter for live edit settings.
     */
    public com.chart.all.anychart.core.gantt.edit.StructureEdit edit() {
        return new com.chart.all.anychart.core.gantt.edit.StructureEdit(jsBase + ".edit()");
    }
    /**
     * Setter for live edit settings.
{docs:Gantt_Chart/Live_Edit_UI_and_API}Learn more about Live editing.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid edit(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".edit(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for live edit settings.
{docs:Gantt_Chart/Live_Edit_UI_and_API}Learn more about Live editing.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid edit(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".edit(%s);", settings));

        return this;
    }
    /**
     * 
     */
    public void editStructurePreviewDashStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".editStructurePreviewDashStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(String color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(String color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewDashStroke(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * 
     */
    public void editStructurePreviewFill() {
        APIlib.getInstance().addJSLine(jsBase + ".editStructurePreviewFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void editStructurePreviewStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".editStructurePreviewStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.DataGrid editStructurePreviewStroke(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public void editing() {
        APIlib.getInstance().addJSLine(jsBase + ".editing();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.DataGrid editing(Boolean value) {
        return new com.chart.all.anychart.standalones.DataGrid(String.format(Locale.US, jsBase + ".editing(%s)", value));
    }
    /**
     * Getter for the element state (enabled or disabled).
     */
    public void enabled() {
        APIlib.getInstance().addJSLine(jsBase + ".enabled();");
    }
    /**
     * Setter for the element enabled state.
     */
    public com.chart.all.anychart.core.ui.DataGrid enabled(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".enabled(%s);", enabled));

        return this;
    }
    /**
     * Getter for the end index.
     */
    public void endIndex() {
        APIlib.getInstance().addJSLine(jsBase + ".endIndex();");
    }
    /**
     * Setter for the end index.
     */
    public com.chart.all.anychart.core.ui.DataGrid endIndex(Number index) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".endIndex(%s);", index));

        return this;
    }
    /**
     * Returns pixel bounds of the element due to parent bounds and self bounds settings.
     */
    public com.chart.all.anychart.math.Rect getPixelBounds() {
        return new com.chart.all.anychart.math.Rect(jsBase + ".getPixelBounds()");
    }
    /**
     * 
     */
    public void getVisibleItems() {
        APIlib.getInstance().addJSLine(jsBase + ".getVisibleItems();");
    }
    /**
     * Getter for element height settings.
     */
    public void height() {
        APIlib.getInstance().addJSLine(jsBase + ".height();");
    }
    /**
     * Setter for element height setting.
     */
    public com.chart.all.anychart.core.ui.DataGrid height(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", height));

        return this;
    }
    /**
     * Setter for element height setting.
     */
    public com.chart.all.anychart.core.ui.DataGrid height(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for the horizontal offset.
     */
    public void horizontalOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".horizontalOffset();");
    }
    /**
     * Setter for the horizontal offset.
     */
    public com.chart.all.anychart.core.ui.DataGrid horizontalOffset(Number offset) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".horizontalOffset(%s);", offset));

        return this;
    }
    /**
     * Getter for the horizontal scroll bar.
     */
    public com.chart.all.anychart.core.ui.ScrollBar horizontalScrollBar() {
        return new com.chart.all.anychart.core.ui.ScrollBar(jsBase + ".horizontalScrollBar()");
    }
    /**
     * Setter for the horizontal scroll bar.
     */
    public com.chart.all.anychart.core.ui.DataGrid horizontalScrollBar(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".horizontalScrollBar(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for element left bound settings.
     */
    public void left() {
        APIlib.getInstance().addJSLine(jsBase + ".left();");
    }
    /**
     * Setter for element left bound settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid left(Number left) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".left(%s);", left));

        return this;
    }
    /**
     * Setter for element left bound settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid left(String left) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".left(%s);", wrapQuotes(left)));

        return this;
    }
    /**
     * Getter for the maximum height.
     */
    public void maxHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".maxHeight();");
    }
    /**
     * Setter for the maximum height.
     */
    public com.chart.all.anychart.core.ui.DataGrid maxHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", height));

        return this;
    }
    /**
     * Setter for the maximum height.
     */
    public com.chart.all.anychart.core.ui.DataGrid maxHeight(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for the maximum width.
     */
    public void maxWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".maxWidth();");
    }
    /**
     * Setter for the maximum width.
     */
    public com.chart.all.anychart.core.ui.DataGrid maxWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxWidth(%s);", width));

        return this;
    }
    /**
     * Setter for the maximum width.
     */
    public com.chart.all.anychart.core.ui.DataGrid maxWidth(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxWidth(%s);", wrapQuotes(width)));

        return this;
    }
    /**
     * Getter for the minimum height.
     */
    public void minHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".minHeight();");
    }
    /**
     * Setter for the minimum height.
     */
    public com.chart.all.anychart.core.ui.DataGrid minHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", height));

        return this;
    }
    /**
     * Setter for the minimum height.
     */
    public com.chart.all.anychart.core.ui.DataGrid minHeight(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for the minimum width.
     */
    public void minWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".minWidth();");
    }
    /**
     * Setter for the minimum width.
     */
    public com.chart.all.anychart.core.ui.DataGrid minWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", width));

        return this;
    }
    /**
     * Setter for the minimum width.
     */
    public com.chart.all.anychart.core.ui.DataGrid minWidth(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", wrapQuotes(width)));

        return this;
    }
    /**
     * Getter for the onEditEnd function.
     */
    public void onEditEnd() {
        APIlib.getInstance().addJSLine(jsBase + ".onEditEnd();");
    }
    /**
     * Getter for the onEditStart function.
     */
    public void onEditStart() {
        APIlib.getInstance().addJSLine(jsBase + ".onEditStart();");
    }
    /**
     * Prints all elements on related stage.
     */
    public void print(com.chart.all.anychart.graphics.vector.PaperSize paperSizeOrOptions, Boolean landscape) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".print(%s, %s);", (paperSizeOrOptions != null) ? paperSizeOrOptions.getJsBase() : null, landscape));
    }
    /**
     * Prints all elements on related stage.
     */
    public void print(String paperSizeOrOptions, Boolean landscape) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".print(%s, %s);", wrapQuotes(paperSizeOrOptions), landscape));
    }
    /**
     * Removes all listeners from an object. You can also optionally remove listeners of some particular type.
     */
    public void removeAllListeners(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeAllListeners(%s);", wrapQuotes(type)));
    }
    /**
     * Getter for element right bound settings.
     */
    public void right() {
        APIlib.getInstance().addJSLine(jsBase + ".right();");
    }
    /**
     * Setter for element right bound setting.
     */
    public com.chart.all.anychart.core.ui.DataGrid right(Number right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", right));

        return this;
    }
    /**
     * Setter for element right bound setting.
     */
    public com.chart.all.anychart.core.ui.DataGrid right(String right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", wrapQuotes(right)));

        return this;
    }
    /**
     * Getter for row even fill.
     */
    public void rowEvenFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowEvenFill();");
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity.
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowEvenFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for row fill.
     */
    public void rowFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowFill();");
    }
    /**
     * Setter for fill settings using an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity.
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for row hover fill.
     */
    public void rowHoverFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowHoverFill();");
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity.
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowHoverFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for row odd fill.
     */
    public void rowOddFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowOddFill();");
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity.
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowOddFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for row fill in selected mode.
     */
    public void rowSelectedFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowSelectedFill();");
    }
    /**
     * Setter for row fill settings in selected mode using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row fill settings in selected mode using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row fill settings in selected mode using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color in selected mode with opacity. Fill as a string or an object.
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode)));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(String[] keys, Number angle, Boolean mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(String[] keys, Number angle, String mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode)));

        return this;
    }
    /**
     * Radial gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.core.ui.DataGrid rowSelectedFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for the start index.
     */
    public void startIndex() {
        APIlib.getInstance().addJSLine(jsBase + ".startIndex();");
    }
    /**
     * Setter for the start index.
     */
    public com.chart.all.anychart.core.ui.DataGrid startIndex(Number index) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".startIndex(%s);", index));

        return this;
    }
    /**
     * Getter for tooltip settings.
     */
    public com.chart.all.anychart.core.ui.Tooltip tooltip() {
        return new com.chart.all.anychart.core.ui.Tooltip(jsBase + ".tooltip()");
    }
    /**
     * Getter for tooltip settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid tooltip(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for tooltip settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid tooltip(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", settings));

        return this;
    }
    /**
     * Getter for element top bound settings.
     */
    public void top() {
        APIlib.getInstance().addJSLine(jsBase + ".top();");
    }
    /**
     * Setter for element top bound settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid top(Number top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", top));

        return this;
    }
    /**
     * Setter for element top bound settings.
     */
    public com.chart.all.anychart.core.ui.DataGrid top(String top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", wrapQuotes(top)));

        return this;
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
    /**
     * Getter for the vertical offset.
     */
    public void verticalOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".verticalOffset();");
    }
    /**
     * Setter for the vertical offset.
     */
    public com.chart.all.anychart.core.ui.DataGrid verticalOffset(Number offset) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".verticalOffset(%s);", offset));

        return this;
    }
    /**
     * Getter for element width settings.
     */
    public void width() {
        APIlib.getInstance().addJSLine(jsBase + ".width();");
    }
    /**
     * Setter for element width setting.
     */
    public com.chart.all.anychart.core.ui.DataGrid width(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", width));

        return this;
    }
    /**
     * Setter for element width setting.
     */
    public com.chart.all.anychart.core.ui.DataGrid width(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", wrapQuotes(width)));

        return this;
    }
    /**
     * Getter for the Z-index of the element.
     */
    public void zIndex() {
        APIlib.getInstance().addJSLine(jsBase + ".zIndex();");
    }
    /**
     * Setter for the Z-index of the element.
     */
    public com.chart.all.anychart.core.ui.DataGrid zIndex(Number zIndex) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".zIndex(%s);", zIndex));

        return this;
    }
    /**
     * Getter for the container.
     */
    public com.chart.all.anychart.graphics.vector.Layer container() {
        return new com.chart.all.anychart.graphics.vector.Layer(jsBase + ".container()");
    }
    /**
     * Setter for the container.
     */
    public com.chart.all.anychart.core.ui.DataGrid container(com.chart.all.anychart.graphics.vector.Layer element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the container.
     */
    public com.chart.all.anychart.core.ui.DataGrid container(com.chart.all.anychart.graphics.vector.Stage element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the container.
     */
    public com.chart.all.anychart.core.ui.DataGrid container(String element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", wrapQuotes(element)));

        return this;
    }
    /**
     * Getter for the parent bounds.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.math.Rect parentBounds() {
        return new com.chart.all.anychart.math.Rect(jsBase + ".parentBounds()");
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.ui.DataGrid parentBounds(com.chart.all.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.ui.DataGrid parentBounds(String bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", wrapQuotes(bounds)));

        return this;
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.ui.DataGrid parentBounds(Number bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", bounds));

        return this;
    }
    /**
     * Setter for the parent bounds using several values.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.ui.DataGrid parentBounds(Number left, Number top, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s, %s, %s, %s);", left, top, width, height));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.data.Tree data(com.chart.all.anychart.data.View data) {
        return new com.chart.all.anychart.data.Tree(String.format(Locale.US, jsBase + ".data(%s)", (data != null) ? data.getJsBase() : null));
    }
    /**
     * 
     */
    public com.chart.all.anychart.data.Tree data(List<DataEntry> data, com.chart.all.anychart.enums.TreeFillingMethod fillMethod) {
        return new com.chart.all.anychart.data.Tree(String.format(Locale.US, jsBase + ".data(%s, %s)", arrayToString(data), (fillMethod != null) ? fillMethod.getJsBase() : null));
    }
    /**
     * 
     */
    public com.chart.all.anychart.data.Tree data(List<DataEntry> data, String fillMethod) {
        return new com.chart.all.anychart.data.Tree(String.format(Locale.US, jsBase + ".data(%s, %s)", arrayToString(data), wrapQuotes(fillMethod)));
    }

}