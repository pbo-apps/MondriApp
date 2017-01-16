package com.pbo.coursera.modernartui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import java.util.Random;

/**
 * A builder class to deal with the creation of new child layouts in a Mondrian layout
 */
public class MondrianLayoutBuilder {
    private final Context m_context;
    private final MondrianHelper.Orientation m_parentOrientation;

    public MondrianLayoutBuilder(Context context, MondrianHelper.Orientation parentOrientation) {
        m_context = context;
        m_parentOrientation = parentOrientation;

    }

    public LinearLayout build() {
        Random rand = new Random();
        int weight = 1 + rand.nextInt(10);

        return build((float) weight);
    }

    public LinearLayout build(float weight) {
        MondrianLayout mondrianLayout = new MondrianLayout(m_context);
        mondrianLayout.setTag(m_context.getString(R.string.mondrian_layout));
        mondrianLayout.setLayoutParams(getLayoutParams(m_parentOrientation, weight));
        mondrianLayout.setBackgroundResource(R.color.mondrian_black);

        // Set the direction of this layout to be the opposite of its parent layout
        if (m_parentOrientation == MondrianHelper.Orientation.Vertical) {
            mondrianLayout.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            mondrianLayout.setOrientation(LinearLayout.VERTICAL);
        }

//        mondrianLayout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//            if (m_context instanceof Activity) {
//                MondrianFragment mondrianFragment = new MondrianFragment();
//
//                ((Activity) m_context).getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.mondrian_fragment_container, mondrianFragment)
//                        .addToBackStack(mondrianFragment.toString())
//                        .commit();
//
//                return true;
//            }
//
//            return false;
//            }
//        });

        return mondrianLayout;
    }

    @NonNull
    private LinearLayout.LayoutParams getLayoutParams(MondrianHelper.Orientation parentOrientation, float weight) {
        LinearLayout.LayoutParams layoutParams;
        if (parentOrientation == MondrianHelper.Orientation.Vertical) {
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, weight);
        } else {
            layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight);
        }

        return layoutParams;
    }
}
