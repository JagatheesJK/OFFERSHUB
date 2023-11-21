package com.chart.all.anychart.standalones;

import com.chart.all.anychart.APIlib;
import com.chart.all.anychart.chart.common.dataentry.DataEntry;
import com.chart.all.anychart.JsObject;
import com.chart.all.anychart.core.ui.Timeline;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * The ResourceTimeline class contains methods for configuring standalones resource timeline.
 */
public class ResourceTimeline extends Timeline {

    protected ResourceTimeline() {

    }

    public static ResourceTimeline instantiate() {
        return new ResourceTimeline("new anychart.standalones.resourceTimeline()");
    }

    

    public ResourceTimeline(String jsChart) {
        jsBase = "resourceTimeline" + ++variableIndex;
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
     * Setter for fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline backgroundFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".backgroundFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void baseBarAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".baseBarAnchor();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarAnchor(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarAnchor(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void baseBarHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".baseBarHeight();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarHeight(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarHeight(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarHeight(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarHeight(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void baseBarOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".baseBarOffset();");
    }
    /**
     * Setter for the base bar offset.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarOffset(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarOffset(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the base bar offset.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void baseBarPosition() {
        APIlib.getInstance().addJSLine(jsBase + ".baseBarPosition();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarPosition(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarPosition(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseBarPosition(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseBarPosition(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void baseFill() {
        APIlib.getInstance().addJSLine(jsBase + ".baseFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.LabelsFactory baseLabels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".baseLabels()");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseLabels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseLabels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseLabels(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseLabels(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void baseStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".baseStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baseStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void baselineAbove() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineAbove();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineAbove(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineAbove(%s);", value));

        return this;
    }
    /**
     * Getter for the baseline bar anchor.
     */
    public void baselineBarAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineBarAnchor();");
    }
    /**
     * Setter for the baseline bar anchor.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the baseline bar anchor.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarAnchor(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarAnchor(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the baseline bar height.
     */
    public void baselineBarHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineBarHeight();");
    }
    /**
     * Setter for the baseline bar height.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarHeight(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarHeight(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the baseline bar height.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarHeight(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarHeight(%s);", value));

        return this;
    }
    /**
     * Getter for the baseline bar offset.
     */
    public void baselineBarOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineBarOffset();");
    }
    /**
     * Setter for the baseline bar offset.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarOffset(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarOffset(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the baseline bar offset.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarOffset(%s);", value));

        return this;
    }
    /**
     * Getter for the baseline bar position.
     */
    public void baselineBarPosition() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineBarPosition();");
    }
    /**
     * Setter for the baseline bar position.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarPosition(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarPosition(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the baseline bar position.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineBarPosition(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineBarPosition(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void baselineFill() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.LabelsFactory baselineLabels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".baselineLabels()");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineLabels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineLabels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineLabels(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineLabels(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void baselineStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".baselineStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselineStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselineStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

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
    public com.chart.all.anychart.standalones.ResourceTimeline columnStroke(com.chart.all.anychart.graphics.vector.Stroke value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".columnStroke(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the column stroke.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline columnStroke(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".columnStroke(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public void connectorFill() {
        APIlib.getInstance().addJSLine(jsBase + ".connectorFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorFill(com.chart.all.anychart.graphics.vector.Fill value, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect opacityOrMode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorFill(%s, %s, %s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, cx, cy, (opacityOrMode != null) ? opacityOrMode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorFill(com.chart.all.anychart.graphics.vector.GradientKey value, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect opacityOrMode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorFill(%s, %s, %s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, cx, cy, (opacityOrMode != null) ? opacityOrMode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorFill(String[] value, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect opacityOrMode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(value), cx, cy, (opacityOrMode != null) ? opacityOrMode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void connectorPreviewStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".connectorPreviewStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(String color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(String color, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorPreviewStroke(String color, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void connectorStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".connectorStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectorStroke(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for connectors.
     */
    public com.chart.all.anychart.core.gantt.elements.ConnectorElement connectors() {
        return new com.chart.all.anychart.core.gantt.elements.ConnectorElement(jsBase + ".connectors()");
    }
    /**
     * Setter for connectors.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline connectors(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectors(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the default row height.
     */
    public void defaultRowHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".defaultRowHeight();");
    }
    /**
     * Setter for the default row height.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline defaultRowHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".defaultRowHeight(%s);", height));

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
    public com.chart.all.anychart.standalones.ResourceTimeline edit(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".edit(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for live edit settings.
{docs:Gantt_Chart/Live_Edit_UI_and_API}Learn more about Live editing.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline edit(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".edit(%s);", settings));

        return this;
    }
    /**
     * 
     */
    public void editConnectorThumbFill() {
        APIlib.getInstance().addJSLine(jsBase + ".editConnectorThumbFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void editConnectorThumbStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".editConnectorThumbStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editConnectorThumbStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editConnectorThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void editIntervalThumbFill() {
        APIlib.getInstance().addJSLine(jsBase + ".editIntervalThumbFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void editIntervalThumbStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".editIntervalThumbStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalThumbStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalThumbStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void editPreviewFill() {
        APIlib.getInstance().addJSLine(jsBase + ".editPreviewFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void editPreviewStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".editPreviewStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editPreviewStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editPreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void editProgressFill() {
        APIlib.getInstance().addJSLine(jsBase + ".editProgressFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void editProgressStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".editProgressStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editProgressStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editProgressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

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
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewDashStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewDashStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

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
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
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
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStructurePreviewStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStructurePreviewStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

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
    public com.chart.all.anychart.standalones.ResourceTimeline editing(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editing(%s);", value));

        return this;
    }
    /**
     * Getter for elements.
     */
    public com.chart.all.anychart.core.gantt.elements.TimelineElement elements() {
        return new com.chart.all.anychart.core.gantt.elements.TimelineElement(jsBase + ".elements()");
    }
    /**
     * Setter for elements.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline elements(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".elements(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the labels factory.
     */
    public com.chart.all.anychart.core.ui.LabelsFactory labels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".labels()");
    }
    /**
     * Setter for the labels factory.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline labels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".labels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Getter for the line marker.
     */
    public com.chart.all.anychart.core.axismarkers.GanttLine lineMarker(Number index) {
        return new com.chart.all.anychart.core.axismarkers.GanttLine(String.format(Locale.US, jsBase + ".lineMarker(%s)", index));
    }
    /**
     * Setter for the line marker.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline lineMarker(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the line marker.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline lineMarker(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s);", value));

        return this;
    }
    /**
     * Setter for the line marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline lineMarker(Number index, String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s, %s);", index, wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the line marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline lineMarker(Number index, Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s, %s);", index, value));

        return this;
    }
    /**
     * Setter for the line marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline lineMarker(Number index, com.chart.all.anychart.enums.GanttDateTimeMarkers value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s, %s);", index, (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the markers factory.
     */
    public com.chart.all.anychart.core.ui.MarkersFactory markers() {
        return new com.chart.all.anychart.core.ui.MarkersFactory(jsBase + ".markers()");
    }
    /**
     * Setter for the markers factory.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline markers(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markers(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public void maximumGap() {
        APIlib.getInstance().addJSLine(jsBase + ".maximumGap();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline maximumGap(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maximumGap(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void milestoneAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".milestoneAnchor();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneAnchor(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneAnchor(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void milestoneFill() {
        APIlib.getInstance().addJSLine(jsBase + ".milestoneFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void milestoneHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".milestoneHeight();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneHeight(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneHeight(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneHeight(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneHeight(%s);", value));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.LabelsFactory milestoneLabels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".milestoneLabels()");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneLabels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneLabels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneLabels(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneLabels(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void milestoneOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".milestoneOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneOffset(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneOffset(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void milestonePosition() {
        APIlib.getInstance().addJSLine(jsBase + ".milestonePosition();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestonePosition(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestonePosition(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestonePosition(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestonePosition(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void milestoneStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".milestoneStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestoneStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestoneStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void minimumGap() {
        APIlib.getInstance().addJSLine(jsBase + ".minimumGap();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline minimumGap(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minimumGap(%s);", value));

        return this;
    }
    /**
     * Getter for the palette.
     */
    public com.chart.all.anychart.palettes.RangeColors palette() {
        return new com.chart.all.anychart.palettes.RangeColors(jsBase + ".palette()");
    }
    /**
     * Setter the for palette.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline palette(com.chart.all.anychart.palettes.RangeColors paletteSettings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", (paletteSettings != null) ? paletteSettings.getJsBase() : null));

        return this;
    }
    /**
     * Setter the for palette.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline palette(com.chart.all.anychart.palettes.DistinctColors paletteSettings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", (paletteSettings != null) ? paletteSettings.getJsBase() : null));

        return this;
    }
    /**
     * Setter the for palette.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline palette(String paletteSettings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", wrapQuotes(paletteSettings)));

        return this;
    }
    /**
     * Setter the for palette.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline palette(String[] paletteSettings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", arrayToStringWrapQuotes(paletteSettings)));

        return this;
    }
    /**
     * 
     */
    public void parentBarAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".parentBarAnchor();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarAnchor(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarAnchor(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void parentBarHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".parentBarHeight();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarHeight(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarHeight(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarHeight(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarHeight(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void parentBarOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".parentBarOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarOffset(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarOffset(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void parentBarPosition() {
        APIlib.getInstance().addJSLine(jsBase + ".parentBarPosition();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarPosition(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarPosition(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentBarPosition(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBarPosition(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void parentFill() {
        APIlib.getInstance().addJSLine(jsBase + ".parentFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.LabelsFactory parentLabels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".parentLabels()");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentLabels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentLabels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentLabels(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentLabels(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void parentStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".parentStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline parentStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Getter for periods.
     */
    public com.chart.all.anychart.core.gantt.elements.PeriodsElement periods() {
        return new com.chart.all.anychart.core.gantt.elements.PeriodsElement(jsBase + ".periods()");
    }
    /**
     * Setter for periods.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline periods(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".periods(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * 
     */
    public void progressBarAnchor() {
        APIlib.getInstance().addJSLine(jsBase + ".progressBarAnchor();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarAnchor(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarAnchor(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarAnchor(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarAnchor(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void progressBarHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".progressBarHeight();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarHeight(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarHeight(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarHeight(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarHeight(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void progressBarOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".progressBarOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarOffset(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarOffset(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void progressBarPosition() {
        APIlib.getInstance().addJSLine(jsBase + ".progressBarPosition();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarPosition(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarPosition(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressBarPosition(com.chart.all.anychart.enums.Anchor value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressBarPosition(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public void progressFill() {
        APIlib.getInstance().addJSLine(jsBase + ".progressFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.core.ui.LabelsFactory progressLabels() {
        return new com.chart.all.anychart.core.ui.LabelsFactory(jsBase + ".progressLabels()");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressLabels(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressLabels(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressLabels(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressLabels(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void progressStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".progressStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline progressStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".progressStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the range marker.
     */
    public com.chart.all.anychart.core.axismarkers.GanttRange rangeMarker(Number index) {
        return new com.chart.all.anychart.core.axismarkers.GanttRange(String.format(Locale.US, jsBase + ".rangeMarker(%s)", index));
    }
    /**
     * Setter for the range marker.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rangeMarker(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the range marker.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rangeMarker(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s);", value));

        return this;
    }
    /**
     * Setter for the range marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rangeMarker(Number index, String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s, %s);", index, wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the range marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rangeMarker(Number index, Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s, %s);", index, value));

        return this;
    }
    /**
     * Setter for the range marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rangeMarker(Number index, com.chart.all.anychart.enums.GanttDateTimeMarkers value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s, %s);", index, (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the row even fill.
     */
    public void rowEvenFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowEvenFill();");
    }
    /**
     * Setter for row even fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row even fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row even fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity. Fill as a string or an object.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowEvenFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowEvenFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for the row fill.
     */
    public void rowFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowFill();");
    }
    /**
     * Setter for row fill settings using an array, an object or a string. Resets odd fill and even fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row fill settings using an array, an object or a string. Resets odd fill and even fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(com.chart.all.anychart.graphics.vector.GradientKey value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row fill settings using an array, an object or a string. Resets odd fill and even fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(String[] value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s);", arrayToStringWrapQuotes(value)));

        return this;
    }
    /**
     * Fill color with opacity. Fill as a string or an object.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for the row hover fill.
     */
    public void rowHoverFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowHoverFill();");
    }
    /**
     * Setter for row hover fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row hover fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row hover fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(String[] value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s);", arrayToStringWrapQuotes(value)));

        return this;
    }
    /**
     * Fill color with opacity. Fill as a string or an object.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowHoverFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowHoverFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Getter for the row odd fill.
     */
    public void rowOddFill() {
        APIlib.getInstance().addJSLine(jsBase + ".rowOddFill();");
    }
    /**
     * Setter for row odd fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row odd fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row odd fill settings using an object, an array or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(String[] value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s);", arrayToStringWrapQuotes(value)));

        return this;
    }
    /**
     * Fill color with opacity. Fill as a string or an object.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowOddFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowOddFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
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
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(com.chart.all.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row fill settings in selected mode using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for row fill settings in selected mode using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color in selected mode with opacity. Fill as a string or an object.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode)));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(String[] keys, Number angle, Boolean mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null));

        return this;
    }
    /**
     * Linear gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(String[] keys, Number angle, String mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode)));

        return this;
    }
    /**
     * Radial gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill in selected mode.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public com.chart.all.anychart.standalones.ResourceTimeline rowSelectedFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rowSelectedFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void selectedElementFill() {
        APIlib.getInstance().addJSLine(jsBase + ".selectedElementFill();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(com.chart.all.anychart.graphics.vector.Fill value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(String[] keys, Number angle, com.chart.all.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(com.chart.all.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementFill(String[] keys, Number cx, Number cy, com.chart.all.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * 
     */
    public void selectedElementStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".selectedElementStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedElementStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedElementStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the text marker.
     */
    public com.chart.all.anychart.core.axismarkers.GanttText textMarker(Number index) {
        return new com.chart.all.anychart.core.axismarkers.GanttText(String.format(Locale.US, jsBase + ".textMarker(%s)", index));
    }
    /**
     * Setter for the text marker.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline textMarker(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the text marker.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline textMarker(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s);", value));

        return this;
    }
    /**
     * Setter for text marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline textMarker(Number index, String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s, %s);", index, wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for text marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline textMarker(Number index, Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s, %s);", index, value));

        return this;
    }
    /**
     * Setter for text marker by index.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline textMarker(Number index, com.chart.all.anychart.enums.GanttDateTimeMarkers value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s, %s);", index, (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * Getter for tooltip settings.
     */
    public com.chart.all.anychart.core.ui.Tooltip tooltip() {
        return new com.chart.all.anychart.core.ui.Tooltip(jsBase + ".tooltip()");
    }
    /**
     * Setter for the tooltip.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline tooltip(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the tooltip.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline tooltip(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", value));

        return this;
    }
    /**
     * Getter for baselines.
     */
    public com.chart.all.anychart.core.gantt.elements.BaselinesElement baselines() {
        return new com.chart.all.anychart.core.gantt.elements.BaselinesElement(jsBase + ".baselines()");
    }
    /**
     * Setter for baselines.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline baselines(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baselines(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * 
     */
    public void editFinishConnectorMarkerHorizontalOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".editFinishConnectorMarkerHorizontalOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editFinishConnectorMarkerHorizontalOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editFinishConnectorMarkerHorizontalOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void editFinishConnectorMarkerSize() {
        APIlib.getInstance().addJSLine(jsBase + ".editFinishConnectorMarkerSize();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editFinishConnectorMarkerSize(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editFinishConnectorMarkerSize(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void editFinishConnectorMarkerType() {
        APIlib.getInstance().addJSLine(jsBase + ".editFinishConnectorMarkerType();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editFinishConnectorMarkerType(com.chart.all.anychart.enums.MarkerType value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editFinishConnectorMarkerType(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editFinishConnectorMarkerType(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editFinishConnectorMarkerType(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public void editFinishConnectorMarkerVerticalOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".editFinishConnectorMarkerVerticalOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editFinishConnectorMarkerVerticalOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editFinishConnectorMarkerVerticalOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void editIntervalWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".editIntervalWidth();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editIntervalWidth(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editIntervalWidth(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void editStartConnectorMarkerHorizontalOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".editStartConnectorMarkerHorizontalOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStartConnectorMarkerHorizontalOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStartConnectorMarkerHorizontalOffset(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void editStartConnectorMarkerSize() {
        APIlib.getInstance().addJSLine(jsBase + ".editStartConnectorMarkerSize();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStartConnectorMarkerSize(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStartConnectorMarkerSize(%s);", value));

        return this;
    }
    /**
     * 
     */
    public void editStartConnectorMarkerType() {
        APIlib.getInstance().addJSLine(jsBase + ".editStartConnectorMarkerType();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStartConnectorMarkerType(com.chart.all.anychart.enums.MarkerType value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStartConnectorMarkerType(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStartConnectorMarkerType(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStartConnectorMarkerType(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public void editStartConnectorMarkerVerticalOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".editStartConnectorMarkerVerticalOffset();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline editStartConnectorMarkerVerticalOffset(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".editStartConnectorMarkerVerticalOffset(%s);", value));

        return this;
    }
    /**
     * Getter for grouping tasks.
     */
    public com.chart.all.anychart.core.gantt.elements.GroupingTasksElement groupingTasks() {
        return new com.chart.all.anychart.core.gantt.elements.GroupingTasksElement(jsBase + ".groupingTasks()");
    }
    /**
     * Setter for grouping tasks.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline groupingTasks(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".groupingTasks(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the timeline header.
     */
    public com.chart.all.anychart.core.gantt.TimeLineHeader header() {
        return new com.chart.all.anychart.core.gantt.TimeLineHeader(jsBase + ".header()");
    }
    /**
     * Setter for the timeline header.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline header(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".header(%s);", wrapQuotes(value)));

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
    public com.chart.all.anychart.standalones.ResourceTimeline horizontalScrollBar(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".horizontalScrollBar(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Getter for milestones.
     */
    public com.chart.all.anychart.core.gantt.elements.MilestonesElement milestones() {
        return new com.chart.all.anychart.core.gantt.elements.MilestonesElement(jsBase + ".milestones()");
    }
    /**
     * Setter for milestones.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline milestones(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".milestones(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the timeline scale.
     */
    public com.chart.all.anychart.scales.GanttDateTime scale() {
        return new com.chart.all.anychart.scales.GanttDateTime(jsBase + ".scale()");
    }
    /**
     * Setter for the timeline scale.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline scale(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".scale(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * 
     */
    public void selectedConnectorStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".selectedConnectorStroke();");
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(com.chart.all.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(String value, Number thickness, String dashpattern, String lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * 
     */
    public com.chart.all.anychart.standalones.ResourceTimeline selectedConnectorStroke(String value, Number thickness, String dashpattern, com.chart.all.anychart.graphics.vector.StrokeLineJoin lineJoin, com.chart.all.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectedConnectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Getter for tasks.
     */
    public com.chart.all.anychart.core.gantt.elements.TasksElement tasks() {
        return new com.chart.all.anychart.core.gantt.elements.TasksElement(jsBase + ".tasks()");
    }
    /**
     * Setter for tasks.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline tasks(String settingss) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tasks(%s);", wrapQuotes(settingss)));

        return this;
    }
    /**
     * Getter for the vertical scroll bar.
     */
    public com.chart.all.anychart.core.ui.ScrollBar verticalScrollBar() {
        return new com.chart.all.anychart.core.ui.ScrollBar(jsBase + ".verticalScrollBar()");
    }
    /**
     * Setter for the vertical scroll bar.
     */
    public com.chart.all.anychart.standalones.ResourceTimeline verticalScrollBar(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".verticalScrollBar(%s);", wrapQuotes(value)));

        return this;
    }

}