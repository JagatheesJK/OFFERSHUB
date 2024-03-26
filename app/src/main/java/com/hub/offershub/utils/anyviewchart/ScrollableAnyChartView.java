package com.hub.offershub.utils.anyviewchart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class ScrollableAnyChartView extends FrameLayout {

    public ScrollableAnyChartView(Context context) {
        super(context);
    }

    public ScrollableAnyChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableAnyChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true); // Disable scrolling on parent NestedScrollView
        return super.onTouchEvent(event);
    }
}
