package com.pbo.coursera.modernartui;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A class to extend a LinearLayout and give it some functionality specific to Mondrian Layouts
 */
public class MondrianShape extends TextView {

    public MondrianShape(Context context) {
        super(context);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (MondrianHelper.isLongClick(event) == false)
//                return performClick();
//        }
//        return false;
//    }
}
