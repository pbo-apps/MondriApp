package com.pbo.coursera.modernartui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

/**
 * A builder class to deal with the creation of new shapes in a Mondrian layout
 */
public class MondrianShapeBuilder {
    private final Context m_context;
    private final MondrianHelper.Orientation m_parentOrientation;

    public MondrianShapeBuilder(Context context, MondrianHelper.Orientation parentOrientation) {
        m_context = context;
        m_parentOrientation = parentOrientation;

    }

    public TextView build() {
        @DrawableRes int colourID = MondrianHelper.getMondrianColour(10);
        Random rand = new Random();
        int weight = 1 + rand.nextInt(10);

        return build(colourID, (float) weight);
    }

    public TextView build(@DrawableRes final int backgroundColourID, float weight) {
        MondrianShape mondrianShape = new MondrianShape(m_context);
        mondrianShape.setTag(m_context.getString(R.string.mondrian_rect));
        mondrianShape.setLayoutParams(getMarginLayoutParams(m_parentOrientation, weight));
        mondrianShape.setBackgroundResource(backgroundColourID);

        mondrianShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable background = v.getBackground();
                if (background instanceof ColorDrawable) {
                    @ColorInt int nextColour = MondrianHelper.getNextMondrianColour(m_context,
                            ((ColorDrawable) background).getColor());
                    v.setBackgroundColor(nextColour);
                }
            }
        });

        return mondrianShape;
    }

    @NonNull
    private TableRow.MarginLayoutParams getMarginLayoutParams(MondrianHelper.Orientation parentOrientation, float weight) {
        TableRow.MarginLayoutParams layoutParams;
        if (parentOrientation == MondrianHelper.Orientation.Vertical)
            layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 0, weight);
        else
            layoutParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, weight);

        int margin = m_context.getResources().getDimensionPixelSize(R.dimen.mondrian_line_width);
        layoutParams.setMargins(margin, margin, margin, margin);

        return layoutParams;
    }
}
