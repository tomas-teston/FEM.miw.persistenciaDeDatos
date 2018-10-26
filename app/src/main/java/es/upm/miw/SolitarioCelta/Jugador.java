package es.upm.miw.SolitarioCelta;

import android.os.Parcel;
import android.os.Parcelable;

public class Jugador implements Parcelable {

    private int id;
    private String name;
    private int puntuacion;

    public Jugador() {}

    public Jugador(int id, String name, int puntuacion) {
        this.id = id;
        this.name = name;
        this.puntuacion = puntuacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.puntuacion);
    }

    protected Jugador(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.puntuacion = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel source) {
            return new Jugador(source);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };
}
