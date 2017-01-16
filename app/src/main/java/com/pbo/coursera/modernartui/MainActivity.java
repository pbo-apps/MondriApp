package com.pbo.coursera.modernartui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_VISIT_MOMA_DIALOG = "com.pbo.coursera.modernartui.MOMA_Dialog";
    private static final String TAG_MONDRIAN_FRAGMENT = "mondrian_fragment";
    private static final String TAG = "modernartui.info";
    private int m_seekerProgress = 50;
    private MondrianFragment m_mondrianFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createSeekBarListener();

        // Reset instance state on reconfiguration 
        if (null != savedInstanceState) {
            restoreState(savedInstanceState);
        } else {
            setupFragments();
        }
    }

    // One time setup of UI and retained Fragment
    private void setupFragments() {
        m_mondrianFragment = new MondrianFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.mondrian_fragment_container, m_mondrianFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        if (null != m_mondrianFragment) {
            savedInstanceState.putString(TAG_MONDRIAN_FRAGMENT,
                    m_mondrianFragment.getTag());
        }

        super.onSaveInstanceState(savedInstanceState);
    }

    private void restoreState(Bundle savedInstanceState) {
        //Fragments tags were saved in onSaveInstanceState
        m_mondrianFragment = (MondrianFragment) getFragmentManager()
                .findFragmentByTag(savedInstanceState
                        .getString(TAG_MONDRIAN_FRAGMENT));
    }

    private void createSeekBarListener() {
        final SeekBar seekerView = (SeekBar) findViewById(R.id.seeker);

        if (seekerView != null) {
            seekerView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (Math.abs(progress - m_seekerProgress) > 5) {
                        LinearLayout mondrianLayout = (LinearLayout) findViewById(R.id.mondrian_root_layout);
                        if (mondrianLayout != null) {
                            int randomRange = 5 + progress / 10;
                            MondrianHelper.setRandomMondrianColourRecursively(seekBar.getContext(),
                                    (ViewGroup) mondrianLayout,
                                    randomRange);
                        }
                        m_seekerProgress = progress;
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_more_info) {
            DialogFragment visitMomaDialog =  new VisitMomaDialogFragment();
            visitMomaDialog.show(getFragmentManager(), TAG_VISIT_MOMA_DIALOG);

            return true;
        } else if (id == R.id.action_new_layout) {
            MondrianFragment mondrianFragment = new MondrianFragment();

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mondrian_fragment_container, mondrianFragment)
                    .addToBackStack(mondrianFragment.toString())
                    .commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO - Figure out how to put a layout in the bundle
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        if (m_mondrianLayout != null) {
//            ArrayList<LinearLayout> arrayList = new ArrayList<LinearLayout>();
//            arrayList.add(m_mondrianLayout);
//            outState.putParcelableArrayList("TAGAGAG", arrayList);
//        }
//    }
}
