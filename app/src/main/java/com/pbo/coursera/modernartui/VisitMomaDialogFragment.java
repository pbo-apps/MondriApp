package com.pbo.coursera.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Simple dialog fragment accessed from the More Information item on the Options menu
 * which provides the option to visit the Moma website or cancel the dialog
 */
public class VisitMomaDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.title_visit_moma)
                .setPositiveButton(R.string.action_visit_moma, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent visitMOMAWebsite = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(getString(R.string.web_address_moma)));

                        if (visitMOMAWebsite.resolveActivity(getActivity().getPackageManager()) != null)
                            startActivity(visitMOMAWebsite);
                    }
                })
                .setNegativeButton(R.string.action_not_now, null);
        return builder.create();
    }
}
