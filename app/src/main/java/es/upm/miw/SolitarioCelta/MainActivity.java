package es.upm.miw.SolitarioCelta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.SolitarioCelta.Integration.GestorBBDD;

public class MainActivity extends AppCompatActivity {

	JuegoCelta mJuego;
    private final String CLAVE_TABLERO = "TABLERO_SOLITARIO_CELTA";
    public static final String LOG_TAG = "MiW";
    public static final String JUGADORES_ID = "JUGADORES";
    private String partidaRecibida = "";
    private String nombreJugador = "";

    GestorBBDD gdb;

	private final int[][] ids = {
		{       0,        0, R.id.p02, R.id.p03, R.id.p04,        0,        0},
        {       0,        0, R.id.p12, R.id.p13, R.id.p14,        0,        0},
        {R.id.p20, R.id.p21, R.id.p22, R.id.p23, R.id.p24, R.id.p25, R.id.p26},
        {R.id.p30, R.id.p31, R.id.p32, R.id.p33, R.id.p34, R.id.p35, R.id.p36},
        {R.id.p40, R.id.p41, R.id.p42, R.id.p43, R.id.p44, R.id.p45, R.id.p46},
        {       0,        0, R.id.p52, R.id.p53, R.id.p54,        0,        0},
        {       0,        0, R.id.p62, R.id.p63, R.id.p64,        0,        0}
	};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gdb = GestorBBDD.getInstance(getApplicationContext());

        mJuego = new JuegoCelta();
        mostrarTablero();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        this.nombreJugador = sharedPref.getString("playerName", "jugador");
    }

    @Override
    protected void onDestroy() {
        gdb.close();
        super.onDestroy();
    }

    public String getPartidaRecibida() {
        return partidaRecibida;
    }

    public void setPartidaRecibida(String partidaRecibida) {
        this.partidaRecibida = partidaRecibida;
    }

    /**
     * Se ejecuta al pulsar una posición
     *
     * @param v Vista de la posición pulsada
     */
    public void posicionPulsada(View v) {
        String resourceName = getResources().getResourceEntryName(v.getId());
        int i = resourceName.charAt(1) - '0';
        int j = resourceName.charAt(2) - '0';

        mJuego.jugar(i, j);

        mostrarTablero();
        if (mJuego.juegoTerminado()) {
            this.guardarPuntuacion();
            new AlertDialogFragment().show(getFragmentManager(), "ALERT DIALOG");
        }
    }

    /**
     * Visualiza el tablero
     */
    public void mostrarTablero() {
        RadioButton button;

        for (int i = 0; i < JuegoCelta.TAMANIO; i++)
            for (int j = 0; j < JuegoCelta.TAMANIO; j++)
                if (ids[i][j] != 0) {
                    button = findViewById(ids[i][j]);
                    button.setChecked(mJuego.obtenerFicha(i, j) == 1);
                }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(CLAVE_TABLERO, mJuego.serializaTablero());
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String grid = savedInstanceState.getString(CLAVE_TABLERO);
        mJuego.deserializaTablero(grid);
        mostrarTablero();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        DialogFragment dialogFragment;
        switch (item.getItemId()) {
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, SCeltaPreferences.class));
                return true;
            case R.id.guardar:
                this.mJuego.guardarPartida(getApplicationContext());
                Toast.makeText(getApplicationContext(), R.string.partidaGuardada,
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reiniciar:
                dialogFragment = new ResetDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "reset");
                return true;
            case R.id.cargar:
                this.setPartidaRecibida(this.mJuego.cargarPartida(getApplicationContext()));
                if (this.getPartidaRecibida() != null) {
                    if (!this.getPartidaRecibida().equalsIgnoreCase(this.mJuego.serializaTablero())) {
                        dialogFragment = new LoadDialogFragment();
                        dialogFragment.show(getSupportFragmentManager(), "load");
                    } else {
                        this.cargarJuego(this.getPartidaRecibida());
                    }

                }
                return true;
            case R.id.mejoresResultados:
                Intent intent = new Intent(getApplicationContext(), MejoresResultados.class);
                ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
                jugadores = this.gdb.getAll();

                if (jugadores.isEmpty()) {
                    Toast.makeText(this, "Ningún jugador registrado.", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putParcelableArrayListExtra(JUGADORES_ID, jugadores);
                    startActivity(intent);
                }

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void cargarJuego(String partida) {
        this.mJuego.deserializaTablero(partida);
        this.mostrarTablero();
        Toast.makeText(getApplicationContext(), R.string.partidaCargada,
                Toast.LENGTH_SHORT).show();
    }

    public void guardarPuntuacion() {
        Long id = this.gdb.add(this.nombreJugador, this.mJuego.puntuacionTotal());
        Log.i(LOG_TAG, "Id Jugador: " + String.valueOf(id));
    }
}
