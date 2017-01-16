package com.pbo.coursera.modernartui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A fragment to handle layout of shapes in a Mondrian stylee
 */
public class MondrianFragment extends android.app.Fragment {
    private ViewGroup m_mondrianLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (m_mondrianLayout == null) {
            m_mondrianLayout = (ViewGroup) inflater.inflate(R.layout.mondrian_layout_default, container, false);

            if (m_mondrianLayout instanceof LinearLayout) {
                MondrianHelper.generateMondrianLayout(m_mondrianLayout.getContext(), (LinearLayout) m_mondrianLayout, 10);
            }

//            m_mondrianLayout.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    MondrianFragment mondrianFragment = new MondrianFragment();
//
//                    getFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.mondrian_fragment_container, mondrianFragment)
//                            .addToBackStack(mondrianFragment.toString())
//                            .commit();
//
//                    return true;
//                }
//            });
        }

        return m_mondrianLayout;
    }
}
