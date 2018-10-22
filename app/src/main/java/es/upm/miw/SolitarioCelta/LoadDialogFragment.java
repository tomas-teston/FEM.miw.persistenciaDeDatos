package es.upm.miw.SolitarioCelta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class LoadDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.txtDialogoLoadTitulo))
                .setMessage(getString(R.string.txtDialogoLoadPregunta))
                .setPositiveButton(
                        getString(R.string.txtDialogoLoadAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                main.cargarJuego(main.getPartidaRecibida());
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoLoadNegativo),
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
