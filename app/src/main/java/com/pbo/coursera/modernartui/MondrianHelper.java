package com.pbo.coursera.modernartui;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

/**
 * A helper class to deal with the particulars of getting colours and the like
 */
public class MondrianHelper {

    public enum Orientation { Horizontal, Vertical;}

    /**
     * Method designed to get a random colour in the Mondrian palette.
     */
    static int getMondrianColour(int randomRange) {
        Random rand = new Random();
        int randomInt = rand.nextInt(randomRange);
        int colour = R.color.mondrian_white;
        switch (randomInt) {
            case 0:
                colour = R.color.mondrian_blue;
                break;
            case 1:
                colour = R.color.mondrian_red;
                break;
            case 2:
                colour = R.color.mondrian_yellow;
                break;
        }
        return colour;
    }
    public static @ColorInt int getNextMondrianColour(Context context, @ColorInt int colour) {
        final @ColorInt int mondrianWhite = ContextCompat.getColor(context, R.color.mondrian_white);
        final @ColorInt int mondrianRed = ContextCompat.getColor(context, R.color.mondrian_red);
        final @ColorInt int mondrianYellow = ContextCompat.getColor(context, R.color.mondrian_yellow);
        final @ColorInt int mondrianBlue = ContextCompat.getColor(context, R.color.mondrian_blue);

        @ColorInt int nextColour = mondrianWhite;

        if (colour == mondrianWhite) {
            nextColour = mondrianRed;
        } else if (colour == mondrianRed) {
            nextColour = mondrianYellow;
        } else if (colour == mondrianYellow) {
            nextColour = mondrianBlue;
        }
        // Blue returns white to complete the loop
        return nextColour;
    }

    /*
     * Function to create an entire Mondrian layout inside the passed Linear Layout, with
     * random colouring and numbers of rectangles
     */
    static void generateMondrianLayout(Context context, @NonNull LinearLayout layout, int maxChildren) {
        // Don't do anything if no children are needed
        if (maxChildren <= 0)
            return;

        // Clear out any existing views
        layout.removeAllViews();

        Orientation layoutOrientation = layout.getOrientation() == LinearLayout.HORIZONTAL ?
                Orientation.Horizontal : Orientation.Vertical;

        // Create our builders
        MondrianShapeBuilder mondrianShapeBuilder = new MondrianShapeBuilder(context, layoutOrientation);
        MondrianLayoutBuilder mondrianLayoutBuilder = new MondrianLayoutBuilder(context, layoutOrientation);

        // Make sure that number of child views is always at least 1 so that we don't get black patches
        Random rand = new Random();
        int numChildren = 1 + rand.nextInt(maxChildren);

        for (int i = 0; i < numChildren; i++) {
            if (numChildren == 1 || rand.nextBoolean()) {
                layout.addView(mondrianShapeBuilder.build());
            } else {
                LinearLayout childLayout = mondrianLayoutBuilder.build();
                // Reduce the number of children in the child layout to prevent an infinite loop
                int maxGrandChildren = (int) (maxChildren / 2);
                generateMondrianLayout(context, childLayout, maxGrandChildren);
                layout.addView(childLayout);
            }
        }
    }

    /*
     * Function to set each rectangle within a layout to a random colour in the Mondrian palette
     */
    static void setRandomMondrianColourRecursively(Context context, ViewGroup parent, int randomRange) {
        for (int i = 0; i <= parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setRandomMondrianColourRecursively(context, (ViewGroup) child, randomRange);
            } else if (child != null && child.getTag() == context.getString(R.string.mondrian_rect)) {
                child.setBackgroundResource(getMondrianColour(randomRange));
            }
        }
    }

//    private void setMondrianColourRecursively(ViewGroup parent, int progress) {

//                (int) blue);
//                (int) green,
//                (int) red,
//        return Color.argb(Color.alpha(color) * factor,
//
//        int blue = (color >> 0) & 0xFF;
//        int green = (color >> 8) & 0xFF;
//        int red = (color >> 16) & 0xFF;
//
//        int factor = (int) progress / 100;
//    private int shadeColour(int color, float progress) {
//
//    }
//        }
//            }
//                //child.setBackgroundResource(getMondrianColour());
//                child.setBackgroundColor(newColour);
//                int newColour = shadeColour(colorDrawable.getColor(), progress);
//                ColorDrawable colorDrawable = (ColorDrawable) child.getBackground();
//            } else if (child != null && child.getTag() == getString(R.string.mondrian_rect)) {
//                setMondrianColourRecursively((ViewGroup) child, progress);
//            if (child instanceof ViewGroup) {
//            View child = parent.getChildAt(i);
//        for (int i = 0; i <= parent.getChildCount(); i++) {
//    }

    static boolean isLongClick(MotionEvent event) {
        return event.getEventTime() - event.getDownTime() >= ViewConfiguration.getLongPressTimeout();
    }

    static boolean isClick(MotionEvent event) {
        return event.getEventTime() - event.getDownTime() < ViewConfiguration.getLongPressTimeout();
    }
}
