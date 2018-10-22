package es.upm.miw.SolitarioCelta.Integration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import es.upm.miw.SolitarioCelta.MainActivity;

public class GestorFicheros {

    private static final GestorFicheros instance = new GestorFicheros();

    private final String fileName = "gameFile";

    private GestorFicheros() {}


    public static GestorFicheros getInstance() {
        return instance;
    }

    public void guardarJuego(String tableroSerializado, Context context) {
        FileOutputStream fos;

        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(tableroSerializado.getBytes());
            fos.close();
            Log.i(MainActivity.LOG_TAG, "Guardando en fichero...");
        } catch (Exception e) {
            Log.i(MainActivity.LOG_TAG, "FILE I/O error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String cargarJuego(Context context) {
        BufferedReader buffer;
        String partidaRecibida = "";

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(context.openFileInput(fileName)));

            partidaRecibida = buffer.readLine();
            Log.i(MainActivity.LOG_TAG, "Partida leida: " + partidaRecibida);

            buffer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return partidaRecibida;
    }
}
