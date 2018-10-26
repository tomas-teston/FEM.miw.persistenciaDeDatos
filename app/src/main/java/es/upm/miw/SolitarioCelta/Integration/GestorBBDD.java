package es.upm.miw.SolitarioCelta.Integration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import es.upm.miw.SolitarioCelta.JuegoContract.tablaJuego;
import es.upm.miw.SolitarioCelta.Jugador;

public class GestorBBDD extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DB_NAME = tablaJuego.TABLE_NAME + ".db";

    // Número de version
    private static final int DB_VERSION = 1;

    private static GestorBBDD instance = null;
    /**
     * Constructor
     *
     * @param contexto Context
     */
    private GestorBBDD(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    public static GestorBBDD getInstance(Context context) {
        if (instance == null) {
            instance = new GestorBBDD(context);
        }
        return instance;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaJuego.TABLE_NAME + " ("
                + tablaJuego.COL_NAME_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaJuego.COL_NAME_NOMBRE         + " TEXT, "
                + tablaJuego.COL_NAME_PUNTUACION     + " INTEGER)";
        db.execSQL(consultaSQL);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaJuego.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    /**
     * Devuelve el n&uacute;mero de Puntuaciones en la tabla
     *
     * @return N&uacute;mero de Puntuaciones
     */
    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, tablaJuego.TABLE_NAME);
    }


    /**
     * A&ntilde;ade una nueva Puntuacio&ntilde;n a la tabla
     *
     * @param nombre      Nombre del jugador
     * @param puntuacion  Puntuación del Jugador
     * @return Identificador del Jugador insertado (-1 si no se ha insertado)
     */
    public long add(String nombre, int puntuacion) {

        // Obtiene la DB en modo escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // Mapa de valores: parejas nombreColumna:valor
        ContentValues valores = new ContentValues();
        valores.put(tablaJuego.COL_NAME_NOMBRE, nombre);
        valores.put(tablaJuego.COL_NAME_PUNTUACION, puntuacion);

        // Realiza la inserción
        return db.insert(tablaJuego.TABLE_NAME, null, valores);
    }

    /**
     * Elimina todos los registros de la tabla
     *
     */
    public void deleteAll() {

        // Obtiene la DB en modo escritura
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ tablaJuego.TABLE_NAME);
    }


    /**
     * Recupera todos las Jugadores de la tabla
     *
     * @return array de Jugadores
     */
    public ArrayList<Jugador> getAll() {
        String consultaSQL = "SELECT * FROM " + tablaJuego.TABLE_NAME;
        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Jugador jugador = new Jugador(
                        cursor.getInt(cursor.getColumnIndex(tablaJuego.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaJuego.COL_NAME_NOMBRE)),
                        cursor.getInt(cursor.getColumnIndex(tablaJuego.COL_NAME_PUNTUACION))
                );

                listaJugadores.add(jugador);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return listaJugadores;
    }
}
