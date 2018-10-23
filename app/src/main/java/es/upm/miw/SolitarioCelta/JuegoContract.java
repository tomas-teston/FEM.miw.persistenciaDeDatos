package es.upm.miw.SolitarioCelta;

import android.provider.BaseColumns;

public class JuegoContract {

    private JuegoContract() {}

    public static abstract class tablaJuego implements BaseColumns {
        public static final String TABLE_NAME              = "partidas";

        public static final String COL_NAME_ID             = _ID;
        public static final String COL_NAME_NOMBRE         = "nombre";
        public static final String COL_NAME_PUNTUACION     = "puntuacion";
    }



}
