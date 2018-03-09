package ec.tec.ac.cr.juan_jop96.tetris;

import android.widget.ImageView;

/**
 * Created by juan_ on 3/8/2018.
 */

public class Pieza {
    private int posiciones[] = new int[4];
    private int rotation;
    private int color;


    public Pieza(){}

    public Pieza(int[] posiciones, int rotation, int color) {
        this.posiciones = posiciones;
        this.rotation = rotation;
        this.color = color;
    }


    public int[] getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(int[] posiciones) {
        this.posiciones = posiciones;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
