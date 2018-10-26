package es.upm.miw.SolitarioCelta;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import es.upm.miw.SolitarioCelta.Integration.GestorBBDD;

public class MejoresResultados extends AppCompatActivity implements View.OnClickListener {

	private ListView lvListado;
	private Button botonBorrarTodos;
	private Button botonFiltro;
	private EditText filtroNombre;
	private Spinner filtroPuntuacion;

	private static final String TODAS_LAS_PUNTUACIONES = "TODOS";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mejores_resultados);

		this.lvListado = (ListView) findViewById(R.id.lvListadoElementos);
		this.botonBorrarTodos = (Button) findViewById(R.id.borrarTodos);
		this.botonFiltro = (Button) findViewById(R.id.botonFiltrar);
		this.filtroNombre = (EditText) findViewById(R.id.editFiltroNombre);

		// Listado de jugadores

		ArrayList<Jugador> jugadores = this.getIntent().getParcelableArrayListExtra(MainActivity.JUGADORES_ID);

		this.setDataList(jugadores);

		// Filtros

		this.filtroPuntuacion = (Spinner) findViewById(R.id.editFiltroPuntuacion);
		ArrayList<String> numbers = new ArrayList<>();
		numbers.add(TODAS_LAS_PUNTUACIONES);
		for (int i = 0; i < JuegoCelta.NUM_FICHAS; i++) {
			numbers.add(String.valueOf(i + 1));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, numbers);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.filtroPuntuacion.setAdapter(adapter);

		// Set Listeners.

		this.botonBorrarTodos.setOnClickListener(this);
		this.botonFiltro.setOnClickListener(this);
	}

	public void setDataList(ArrayList<Jugador> jugadores) {
		JugadorAdapter adaptador = new JugadorAdapter(
				this,
				R.layout.item_lista,
				jugadores
		);

		this.lvListado.setAdapter(adaptador);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.borrarTodos) {
			DialogFragment dialogFragment = new DeleteDialogFragment();
			dialogFragment.show(getSupportFragmentManager(), "delete");
		} else if (v.getId() == R.id.botonFiltrar) {
			String puntuacion = this.filtroPuntuacion.getSelectedItem().toString();
			if (puntuacion.equalsIgnoreCase(TODAS_LAS_PUNTUACIONES)) {
				puntuacion = "";
			}
			ArrayList<Jugador> jugadores =
					GestorBBDD.getInstance(getApplicationContext())
							.filtrar(this.filtroNombre.getText().toString(), puntuacion);
			if (jugadores.isEmpty()) {
				Toast.makeText(this, "No existen jugadores con estos filtros", Toast.LENGTH_LONG).show();
			} else {
				this.setDataList(jugadores);
			}
		}
	}
}
