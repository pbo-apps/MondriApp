package com.pbo.coursera.modernartui;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * A class to extend a LinearLayout and give it some functionality specific to Mondrian Layouts
 */
public class MondrianLayout extends LinearLayout {

    public MondrianLayout(Context context) {
        super(context);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        // Intercept any long click events
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (MondrianHelper.isLongClick(event))
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (MondrianHelper.isLongClick(event)) {
//            return performLongClick();
//        }
//        return false;
//    }
}
