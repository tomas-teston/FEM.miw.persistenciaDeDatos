package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class JugadorAdapter extends ArrayAdapter {

    private Context contexto;
    private List<Jugador> jugadores;
    private int idRecursoLayout;

    public JugadorAdapter(Context context, int resource, List<Jugador> jugadores) {
        super(context, resource, jugadores);
        this.contexto = context;
        this.idRecursoLayout = resource;
        this.jugadores = jugadores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout vista;

        if (null != convertView) {
            vista = (LinearLayout) convertView;
        } else {
            LayoutInflater inflador = (LayoutInflater) contexto
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = (LinearLayout) inflador.inflate(idRecursoLayout, parent, false);
        }

        // Asignamos datos a los elementos de la vista
        Jugador jugador = this.jugadores.get(position);
        TextView itemId = (TextView) vista.findViewById(R.id.itemId);
        itemId.setText(String.valueOf(jugador.getId()));

        TextView itemNombre = (TextView) vista.findViewById(R.id.itemNombre);
        itemNombre.setText(jugador.getName());

        TextView itemPuntuacion = (TextView) vista.findViewById(R.id.itemPuntuacion);
        itemPuntuacion.setText(String.valueOf(jugador.getPuntuacion()));

        return vista;
    }
}
