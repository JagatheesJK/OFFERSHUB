package com.chart.all.anychart.core.annotations;

import com.chart.all.anychart.APIlib;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.JsObject;
import com.chart.all.anychart.core.annotations.FibonacciBase;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * Fibonacci Arc annotation.
 */
public class FibonacciArc extends FibonacciBase {

    protected FibonacciArc() {

    }

    public static FibonacciArc instantiate() {
        return new FibonacciArc("new anychart.core.annotations.fibonacciArc()");
    }

    

    public FibonacciArc(String jsChart) {
        jsBase = "fibonacciArc" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }

    
    /**
     * Getter for the annotation color.
     */
    public void color() {
        APIlib.getInstance().addJSLine(jsBase + ".color();");
    }
    /**
     * Setter for the annotation color.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc color(String color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".color(%s);", wrapQuotes(color)));

        return this;
    }
    /**
     * Returns the chart on which the annotation is drawn.
     */
    public com.chart.all.anychart.core.SeparateChart getChart() {
        return new com.chart.all.anychart.core.SeparateChart(jsBase + ".getChart()");
    }
    /**
     * Returns the plot on which the annotation is drawn.
     */
    public com.chart.all.anychart.core.stock.Plot getPlot() {
        return new com.chart.all.anychart.core.stock.Plot(jsBase + ".getPlot()");
    }
    /**
     * Returns annotation type.
     */
    public void getType() {
        APIlib.getInstance().addJSLine(jsBase + ".getType();");
    }
    /**
     * Getter for the hover gap.
     */
    public void hoverGap() {
        APIlib.getInstance().addJSLine(jsBase + ".hoverGap();");
    }
    /**
     * Setter for the hover gap.<br/>
The contour size around annotation.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc hoverGap(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hoverGap(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Getter for hovered state settings.
     */
    public com.chart.all.anychart.core.StateSettings hovered() {
        return new com.chart.all.anychart.core.StateSettings(jsBase + ".hovered()");
    }
    /**
     * Setter for hovered state settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc hovered(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hovered(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for labels settings.
     */
    public com.chart.all.anychart.core.ui.LabelsFactory labels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".labels()");
    }
    /**
     * Setter for labels settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc labels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".labels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for labels settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc labels(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".labels(%s);", value));

        return this;
    }
    /**
     * Getter for the fibonacci levels.
     */
    public void levels() {
        APIlib.getInstance().addJSLine(jsBase + ".levels();");
    }
    /**
     * Setter for the fibonacci levels.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc levels(String[] levelsList) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".levels(%s);", arrayToStringWrapQuotes(levelsList)));

        return this;
    }
    /**
     * Getter for data markers.
     */
    public com.chart.all.anychart.core.ui.MarkersFactory markers() {
        return new com.chart.all.anychart.core.ui.MarkersFactory(jsBase + ".markers()");
    }
    /**
     * Setter for data markers.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc markers(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markers(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for data markers.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc markers(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markers(%s);", settings));

        return this;
    }
    /**
     * Getter for normal state settings.
     */
    public com.chart.all.anychart.core.StateSettings normal() {
        return new com.chart.all.anychart.core.StateSettings(jsBase + ".normal()");
    }
    /**
     * Setter for normal state settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc normal(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".normal(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the second value anchor.
     */
    public void secondValueAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".secondValueAnchor();");
    }
    /**
     * Setter for the second value anchor.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc secondValueAnchor(String Second) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".secondValueAnchor(%s);", wrapQuotes(Second)));

        return this;
    }
    /**
     * Getter for the second X anchor.
     */
    public void secondXAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".secondXAnchor();");
    }
    /**
     * Setter for the second X anchor.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc secondXAnchor(String Second) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".secondXAnchor(%s);", wrapQuotes(Second)));

        return this;
    }
    /**
     * Selects current annotation.
     */
    public void select() {
        APIlib.getInstance().addJSLine(jsBase + ".select();");
    }
    /**
     * Getter for selected state settings.
     */
    public com.chart.all.anychart.core.StateSettings selected() {
        return new com.chart.all.anychart.core.StateSettings(jsBase + ".selected()");
    }
    /**
     * Setter for selected state settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc selected(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selected(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for annotation stroke settings.
     */
    public void stroke() {
        APIlib.getInstance().addJSLine(jsBase + ".stroke();");
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(String color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(String color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation stroke settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for stroke settings using an object.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc stroke(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for annotation trend settings.
     */
    public void trend() {
        APIlib.getInstance().addJSLine(jsBase + ".trend();");
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(String color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(String color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for annotation trend settings.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc trend(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".trend(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the value anchor.
     */
    public void valueAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".valueAnchor();");
    }
    /**
     * Setter for the value anchor.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc valueAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".valueAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Getter for the X anchor.
     */
    public void xAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".xAnchor();");
    }
    /**
     * Setter for the X anchor.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc xAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Getter for the X-scale.
     */
    public com.chart.all.anychart.scales.Ordinal xScale() {
        return new com.chart.all.anychart.scales.Ordinal(jsBase + ".xScale()");
    }
    /**
     * Setter for the X-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc xScale(com.chart.all.anychart.scales.Base settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the X-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc xScale(com.chart.all.anychart.scales.StockScatterDateTime settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the X-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc xScale(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the X-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc xScale(com.chart.all.anychart.enums.ScaleTypes settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the Y-scale.
     */
    public com.chart.all.anychart.scales.Base yScale() {
        return new com.chart.all.anychart.scales.Base(jsBase + ".yScale()");
    }
    /**
     * Setter for the Y-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc yScale(com.chart.all.anychart.scales.Base settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the Y-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc yScale(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yScale(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the Y-scale.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc yScale(com.chart.all.anychart.enums.ScaleTypes settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Getter for an interactivity in the annotation.
     */
    public void allowEdit() {
        APIlib.getInstance().addJSLine(jsBase + ".allowEdit();");
    }
    /**
     * Setter for an interactivity in the annotation.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc allowEdit(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".allowEdit(%s);", wrapQuotes(value)));

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
    public com.chart.all.anychart.core.annotations.FibonacciArc bottom(Number bottom) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bottom(%s);", bottom));

        return this;
    }
    /**
     * Setter for element bottom bound settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bottom(String bottom) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(com.chart.all.anychart.utils.RectObj bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(com.chart.all.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(com.chart.all.anychart.core.utils.Bounds bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(Number x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc bounds(String x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
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
    public com.chart.all.anychart.core.annotations.FibonacciArc enabled(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".enabled(%s);", enabled));

        return this;
    }
    /**
     * Returns pixel bounds of the element due to parent bounds and self bounds settings.
     */
    public com.chart.all.anychart.math.Rect getPixelBounds() {
        return new com.chart.all.anychart.math.Rect(jsBase + ".getPixelBounds()");
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
    public com.chart.all.anychart.core.annotations.FibonacciArc height(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", height));

        return this;
    }
    /**
     * Setter for element height setting.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc height(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", wrapQuotes(height)));

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
    public com.chart.all.anychart.core.annotations.FibonacciArc left(Number left) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".left(%s);", left));

        return this;
    }
    /**
     * Setter for element left bound settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc left(String left) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc maxHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", height));

        return this;
    }
    /**
     * Setter for the maximum height.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc maxHeight(String height) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc maxWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxWidth(%s);", width));

        return this;
    }
    /**
     * Setter for the maximum width.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc maxWidth(String width) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc minHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", height));

        return this;
    }
    /**
     * Setter for the minimum height.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc minHeight(String height) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc minWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", width));

        return this;
    }
    /**
     * Setter for the minimum width.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc minWidth(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", wrapQuotes(width)));

        return this;
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
    public com.chart.all.anychart.core.annotations.FibonacciArc right(Number right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", right));

        return this;
    }
    /**
     * Setter for element right bound setting.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc right(String right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", wrapQuotes(right)));

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
    public com.chart.all.anychart.core.annotations.FibonacciArc top(Number top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", top));

        return this;
    }
    /**
     * Setter for element top bound settings.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc top(String top) {
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
     * Getter for element width settings.
     */
    public void width() {
        APIlib.getInstance().addJSLine(jsBase + ".width();");
    }
    /**
     * Setter for element width setting.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc width(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", width));

        return this;
    }
    /**
     * Setter for element width setting.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc width(String width) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc zIndex(Number zIndex) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc container(com.chart.all.anychart.graphics.vector.Layer element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the container.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc container(com.chart.all.anychart.graphics.vector.Stage element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the container.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc container(String element) {
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
    public com.chart.all.anychart.core.annotations.FibonacciArc parentBounds(com.chart.all.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc parentBounds(String bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", wrapQuotes(bounds)));

        return this;
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc parentBounds(Number bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", bounds));

        return this;
    }
    /**
     * Setter for the parent bounds using several values.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public com.chart.all.anychart.core.annotations.FibonacciArc parentBounds(Number left, Number top, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s, %s, %s, %s);", left, top, width, height));

        return this;
    }
    /**
     * 
     */
    public Object xScale(Class scaleClass) {
        Object instance = null;
        try {
            instance = scaleClass.getDeclaredConstructor(String.class).newInstance(jsBase + ".xScale()");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (java.lang.reflect.InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }
    /**
     * 
     */
    public Object yScale(Class scaleClass) {
        Object instance = null;
        try {
            instance = scaleClass.getDeclaredConstructor(String.class).newInstance(jsBase + ".yScale()");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (java.lang.reflect.InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

}