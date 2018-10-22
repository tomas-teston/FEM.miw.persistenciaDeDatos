package es.upm.miw.SolitarioCelta.Integration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;

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
            fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            fos.write(tableroSerializado.getBytes());
            fos.close();
            Log.i(MainActivity.LOG_TAG, "Click botón Añadir -> Añadido al fichero");
        } catch (Exception e) {
            Log.i(MainActivity.LOG_TAG, "FILE I/O error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
