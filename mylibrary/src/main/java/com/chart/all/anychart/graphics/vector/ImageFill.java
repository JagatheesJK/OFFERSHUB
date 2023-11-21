package com.chart.all.anychart.graphics.vector;

import com.chart.all.anychart.JsObject;

import com.chart.all.anychart.graphics.vector.ImageFillMode;

import java.util.Locale;
import java.util.Arrays;

// typedef
/**
 * Image fill.
 */
public class ImageFill extends JsObject implements Fill {

    
    public ImageFill(ImageFillMode mode, Number opacity, String src) {
        js.append(String.format(Locale.US, "{mode:%s, opacity: %s, src: %s, } ", (mode != null) ? mode.getJsBase() : null, opacity, wrapQuotes(src)));
    }
    public ImageFill(String mode, Number opacity, String src) {
        js.append(String.format(Locale.US, "{mode:%s, opacity: %s, src: %s, } ", wrapQuotes(mode), opacity, wrapQuotes(src)));
    }

    @Override
    public String getJsBase() {
        return js.toString();
    }

}