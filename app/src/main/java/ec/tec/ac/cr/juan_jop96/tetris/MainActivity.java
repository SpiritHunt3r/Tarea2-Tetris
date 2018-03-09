package ec.tec.ac.cr.juan_jop96.tetris;


import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static int tablero[][] = new int[22][12];
    public static Pieza actual = new Pieza();

    public static void reiniciarTablero(){
        for (int f=0; f < 22; f++){
            for (int c=0; c < 12; c++){

                if (f==0 || f==21 || c==0 || c==11) {
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
        switch (opt){
            case 0: generarCuadrado(color); break;
            case 1: generarZ(color); break;
            case 2: generarI(color); break;
            case 3: generarL(color); break;
        }
    }

    public void generarCuadrado(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
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
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        pos[0] = 105;
        pos[1] = 205;
        pos[2] = 305;
        pos[3] = 306;
        temp.setPosiciones(pos);
        actual = temp;
        actual.setColor(color);
        actual.setRotation(0);
        agregarTablero();
        drawPiece();
    }

    public void generarZ(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        pos[0] = 104;
        pos[1] = 105;
        pos[2] = 205;
        pos[3] = 206;
        temp.setPosiciones(pos);
        actual = temp;
        actual.setColor(color);
        actual.setRotation(0);
        agregarTablero();
        drawPiece();
    }

    public void generarI(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        pos[0] = 104;
        pos[1] = 105;
        pos[2] = 106;
        pos[3] = 107;
        temp.setPosiciones(pos);
        actual = temp;
        actual.setColor(color);
        actual.setRotation(0);
        agregarTablero();
        drawPiece();
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

    public void clearPiece(){
        int id;
        int pos[] = actual.getPosiciones();
        for (int i=0; i<4; i++){
            if(pos[i]<1000){
                id = this.getResources().getIdentifier("T0"+String.valueOf(pos[i]), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            else{
                id = this.getResources().getIdentifier("T"+String.valueOf(pos[i]), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }
    }

    public void limpiarTablero(){
        int pos[] = actual.getPosiciones();
        for (int i=0; i<4; i++){
            int c = pos[i]%100;
            int f = pos[i]/100;
            tablero[f][c] = 0;
        }
    }


    public void agregarTablero(){
        int pos[] = actual.getPosiciones();
        for (int i=0; i<4; i++){
            int c = pos[i]%100;
            int f = pos[i]/100;
            tablero[f][c] = 1;

        }
    }

    public boolean CheckMove(int f,int c){
        boolean resp = true;
        int pos[] = actual.getPosiciones();
        int fmov[] = new int[4];
        int tempF,tempC,next;
        for (int i=0; i<4; i++){
            tempC = (pos[i]%100)+c;
            tempF  = (pos[i]/100) + f;
            next = (tempF * 100) + tempC;
            if (!isOnit(next,pos)) {
                if (tablero[tempF][tempC]==1){
                    resp = false;
                }
            }
        }
        return resp;
    }

    public boolean isOnit(int elem,int pos[]){
        for (int i=0; i<pos.length; i++){
            if (elem == pos[i]){
                return true;
            }
        }
        return false;
    }


    public void movimiento(int f,int c){
        int pos[] = actual.getPosiciones();
        for (int i=0; i<4; i++){
            pos[i] += (f*100);
            pos[i] += c;
        }
        actual.setPosiciones(pos);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reiniciarTablero();
        generarPieza();



        ImageButton DerchaBtn = findViewById(R.id.derechaBtn);
        ImageButton IzquierdaBtn = findViewById(R.id.izquierdaBtn);
        ImageButton AbajoBtn = findViewById(R.id.abajoBtn);
        DerchaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckMove(0,1)){
                    clearPiece();
                    limpiarTablero();
                    movimiento(0,1);
                    agregarTablero();
                    drawPiece();
                }
                else{
                    Toast.makeText(MainActivity.this, "Movimiento Invalido", Toast.LENGTH_SHORT).show();
                }

            }
        });

        IzquierdaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckMove(0,-1)){
                    clearPiece();
                    limpiarTablero();
                    movimiento(0,-1);
                    agregarTablero();
                    drawPiece();
                }
                else{
                    Toast.makeText(MainActivity.this, "Movimiento Invalido", Toast.LENGTH_SHORT).show();
                }

            }
        });

        AbajoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckMove(1,0)){
                    clearPiece();
                    limpiarTablero();
                    movimiento(1,0);
                    agregarTablero();
                    drawPiece();
                }
                else{
                    generarPieza();
                }

            }
        });
    }
}
