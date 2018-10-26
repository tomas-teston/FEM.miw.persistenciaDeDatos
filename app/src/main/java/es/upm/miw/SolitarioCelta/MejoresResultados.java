package es.upm.miw.SolitarioCelta;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.upm.miw.SolitarioCelta.Integration.GestorBBDD;

public class MejoresResultados extends AppCompatActivity implements View.OnClickListener {

	private ListView lvListado;
	private Button botonBorrarTodos;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mejores_resultados);

		this.lvListado = (ListView) findViewById(R.id.lvListadoElementos);
		this.botonBorrarTodos = (Button) findViewById(R.id.borrarTodos);

		ArrayList<Jugador> jugadores = this.getIntent().getParcelableArrayListExtra(MainActivity.JUGADORES_ID);


		JugadorAdapter adaptador = new JugadorAdapter(
				this,
				R.layout.item_lista,
				jugadores
		);

		this.lvListado.setAdapter(adaptador);
		this.botonBorrarTodos.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.borrarTodos) {
			DialogFragment dialogFragment = new DeleteDialogFragment();
			dialogFragment.show(getSupportFragmentManager(), "delete");
		}
	}
}
