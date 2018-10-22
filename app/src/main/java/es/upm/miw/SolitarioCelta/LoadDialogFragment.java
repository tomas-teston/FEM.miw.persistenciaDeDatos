package es.upm.miw.SolitarioCelta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class LoadDialogFragment extends DialogFragment{

    private DialogListener listener;

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (DialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.txtDialogoLoadTitulo))
                .setMessage(getString(R.string.txtDialogoLoadPregunta))
                .setPositiveButton(
                        getString(R.string.txtDialogoLoadAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDialogPositiveClick(LoadDialogFragment.this);
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoLoadNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDialogNegativeClick(LoadDialogFragment.this);
                            }
                        }
                );

        return builder.create();
    }

}
