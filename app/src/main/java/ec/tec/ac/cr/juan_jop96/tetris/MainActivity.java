package ec.tec.ac.cr.juan_jop96.tetris;


import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static int tablero[][] = new int[21][12];
    public static Pieza actual = new Pieza();

    public static void reiniciarTablero(){
        for (int f=0; f < 21; f++){
            for (int c=0; c < 12; c++){

                if (f==0 || f==20 || c==0 || c==11) {
                    tablero[f][c] = 1;
                }
                else{
                    tablero[f][c] = 0;
                }
            }
        }
    }




    public void generarPieza(){
        //cuadro,L,Z,I
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        Random a = new Random();
        int opt = a.nextInt(4);
        switch (0){
            case 0: generarCuadrado(color);
            case 1: generarZ(color);
            case 2: generarI(color);
            case 3: generarL(color);
        }
    }

    public void generarCuadrado(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        ImageView viw[] = new ImageView[4];
        pos[0] = 105;
        pos[1] = 106;
        pos[2] = 205;
        pos[3] = 206;
        temp.setPosiciones(pos);
        actual = temp;
        actual.setColor(color);
        actual.setRotation(0);
        agregarTablero();
        drawPiece();
    }

    public void generarL(int color){

    }

    public void generarZ(int color){

    }

    public void generarI(int color){

    }

    public void drawPiece (){
        int id;
        int pos[] = actual.getPosiciones();
        for (int i=0; i<4; i++){
            if(pos[i]<1000){
                id = this.getResources().getIdentifier("T0"+String.valueOf(pos[i]), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(actual.getColor());
            }
            else{
                id = this.getResources().getIdentifier("T"+String.valueOf(pos[i]), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(actual.getColor());
            }
        }
    }

    public void agregarTablero(){
        int pos[] = actual.getPosiciones();
        for (int i=0; i<4; i++){
            int f = pos[i]%10;
            int c = pos[i]/100;
            tablero[f][c] = 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reiniciarTablero();
        generarPieza();
    }
}
