package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import es.upm.miw.SolitarioCelta.Integration.GestorBBDD;

public class DeleteDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MejoresResultados mejoresResultados = (MejoresResultados) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.txtDialogoDeleteTitulo))
                .setMessage(getString(R.string.txtDialogoDeletePregunta))
                .setPositiveButton(
                        getString(R.string.txtDialogoDeleteAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.i(MainActivity.LOG_TAG, "Eliminando mejores jugadas...");
                                GestorBBDD.getInstance(getContext()).deleteAll();
                                Log.i(MainActivity.LOG_TAG, "Mejores jugadas eliminadas!");
                                Toast.makeText(getContext(), "Mejores jugadas eliminadas", Toast.LENGTH_SHORT).show();
                                mejoresResultados.finish();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoDeleteNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), android.R.string.no,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );

        return builder.create();
    }

}
