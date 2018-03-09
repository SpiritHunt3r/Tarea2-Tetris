package ec.tec.ac.cr.juan_jop96.tetris;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
    public static int tablero[][] = new int[22][16];
    public static Pieza actual = new Pieza();
    public static int score = 0;

    public static void reiniciarTablero(){
        for (int f=0; f < 22; f++){
            for (int c=0; c < 15; c++){

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
        switch (2){
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
        if (CheckCreation(pos)){
            actual = temp;
            actual.setColor(color);
            actual.setRotation(0);
            actual.setId(0);
            agregarTablero();
            drawPiece();
        }

    }

    public void generarL(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        pos[0] = 105;
        pos[1] = 106;
        pos[2] = 107;
        pos[3] = 207;
        temp.setPosiciones(pos);
        if (CheckCreation(pos)){
            actual = temp;
            actual.setColor(color);
            actual.setRotation(0);
            actual.setId(1);
            agregarTablero();
            drawPiece();
        }
    }

    public void generarZ(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        pos[0] = 104;
        pos[1] = 105;
        pos[2] = 205;
        pos[3] = 206;
        temp.setPosiciones(pos);
        if (CheckCreation(pos)){
            actual = temp;
            actual.setColor(color);
            actual.setRotation(0);
            actual.setId(2);
            agregarTablero();
            drawPiece();
        }
    }

    public void generarI(int color){
        Pieza temp = new Pieza();
        int pos[] = new int[4];
        pos[0] = 104;
        pos[1] = 105;
        pos[2] = 106;
        pos[3] = 107;
        temp.setPosiciones(pos);
        if (CheckCreation(pos)){
            actual = temp;
            actual.setColor(color);
            actual.setRotation(0);
            actual.setId(3);
            agregarTablero();
            drawPiece();
        }
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


    public boolean CheckRotation (int[] pos){
        boolean resp = true;
        int act[] = actual.getPosiciones();
        int tempF,tempC;
        for (int i=0; i<4; i++){
            tempC = (pos[i]%100);
            tempF  = (pos[i]/100);
            if (!isOnit(pos[i],act)) {
                if (tablero[tempF][tempC]==1){
                    resp = false;
                }
            }
        }
        return resp;
    }

    public boolean CheckCreation (int[] pos){
        boolean resp = true;
        int tempF,tempC;
        for (int i=0; i<4; i++){
            tempC = (pos[i]%100);
            tempF  = (pos[i]/100);
            if (tablero[tempF][tempC]==1){
                resp = false;
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

    public void rotar() {
        switch (actual.getId()) {
            case 0:
                actual.setRotation(0);
                break;
            case 1: {
                if (actual.getRotation() == 0) {
                    actual.setRotation(1);
                    int init = actual.getPosiciones()[0];
                    int Fpos[] = new int[4];
                    Fpos[0] = init;
                    Fpos[1] = init + 100;
                    Fpos[2] = init + 200;
                    Fpos[3] = init + 200 - 1;
                    if (CheckRotation(Fpos)) {
                        clearPiece();
                        limpiarTablero();
                        actual.setPosiciones(Fpos);
                        agregarTablero();
                        drawPiece();
                    } else {
                        Toast.makeText(MainActivity.this, "Rotacion Invalida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    actual.setRotation(0);
                    int init = actual.getPosiciones()[0];
                    int Fpos[] = new int[4];
                    Fpos[0] = init;
                    Fpos[1] = init + 1;
                    Fpos[2] = init + 2;
                    Fpos[3] = init + 102;
                    if (CheckRotation(Fpos)) {
                        clearPiece();
                        limpiarTablero();
                        actual.setPosiciones(Fpos);
                        agregarTablero();
                        drawPiece();
                    } else {
                        Toast.makeText(MainActivity.this, "Rotacion Invalida", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            }

            case 2: {
                if (actual.getRotation() == 0) {
                    actual.setRotation(1);
                    int init = actual.getPosiciones()[0];
                    int Fpos[] = new int[4];
                    Fpos[0] = init;
                    Fpos[1] = init + 100;
                    Fpos[2] = init + 100 - 1;
                    Fpos[3] = init + 200 - 1;
                    if (CheckRotation(Fpos)) {
                        clearPiece();
                        limpiarTablero();
                        actual.setPosiciones(Fpos);
                        agregarTablero();
                        drawPiece();
                    } else {
                        Toast.makeText(MainActivity.this, "Rotacion Invalida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    actual.setRotation(0);
                    int init = actual.getPosiciones()[0];
                    int Fpos[] = new int[4];
                    Fpos[0] = init;
                    Fpos[1] = init + 1;
                    Fpos[2] = init + 100 + 1;
                    Fpos[3] = init + 100 + 2;
                    if (CheckRotation(Fpos)) {
                        clearPiece();
                        limpiarTablero();
                        actual.setPosiciones(Fpos);
                        agregarTablero();
                        drawPiece();
                    } else {
                        Toast.makeText(MainActivity.this, "Rotacion Invalida", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            }
            case 3: {
                if (actual.getRotation() == 0) {
                    actual.setRotation(1);
                    int init = actual.getPosiciones()[0];
                    int Fpos[] = new int[4];
                    Fpos[0] = init;
                    Fpos[1] = init + 100;
                    Fpos[2] = init + 200;
                    Fpos[3] = init + 300;
                    if (CheckRotation(Fpos)) {
                        clearPiece();
                        limpiarTablero();
                        actual.setPosiciones(Fpos);
                        agregarTablero();
                        drawPiece();
                    } else {
                        Toast.makeText(MainActivity.this, "Movimiento Invalido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    actual.setRotation(0);
                    int init = actual.getPosiciones()[0];
                    int Fpos[] = new int[4];
                    Fpos[0] = init;
                    Fpos[1] = init + 1;
                    Fpos[2] = init + 2;
                    Fpos[3] = init + 3;
                    if (CheckRotation(Fpos)) {
                        clearPiece();
                        limpiarTablero();
                        actual.setPosiciones(Fpos);
                        agregarTablero();
                        drawPiece();
                    } else {
                        Toast.makeText(MainActivity.this, "Movimiento Invalido", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            }

        }
    }

    public void vereficarLineas(){
        boolean linea = false;
        for (int f=1; f < 21; f++){
            linea = false;
            if (tablero[f][1]==1 && tablero[f][2] == 1 && tablero[f][3]==1 && tablero[f][4] == 1 && tablero[f][5]==1 && tablero[f][6] == 1 && tablero[f][7]==1 && tablero[f][8] == 1 && tablero[f][9]==1 && tablero[f][10] == 1){
                linea = true;
            }
            if (linea){
                score += 100;
                TextView Scoreview = findViewById(R.id.TxtScoreat);
                Scoreview.setText(String.valueOf(score));
                clearLine(f);
                downlines(f);
            }
        }
    }

    public void clearLine(int f){
        for (int c =1; c<11; c++) {
            tablero[f][c] = 0;
            clearPos(f,c);
        }
    }

    public void clearPos(int f,int c){
        int id;
        if (f>10){
            if(c >= 10){
                id = this.getResources().getIdentifier("T"+String.valueOf(f)+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            else{
                id = this.getResources().getIdentifier("T"+String.valueOf(f)+"0"+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

        }
        else{
            if(c >= 10){
                id = this.getResources().getIdentifier("T0"+String.valueOf(f)+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            else{
                id = this.getResources().getIdentifier("T0"+String.valueOf(f)+"0"+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }
    }

    public int getColorpos(int f, int c){
        int id;
        if (f>10){
            if(c >= 10){
                id = this.getResources().getIdentifier("T"+String.valueOf(f)+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setDrawingCacheEnabled(true);
                tmp.buildDrawingCache(true);
                Bitmap a = tmp.getDrawingCache();
                int pixel = a.getPixel(0,0);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int color = Color.argb(255,r,g,b);
                return color;
            }
            else{
                id = this.getResources().getIdentifier("T"+String.valueOf(f)+"0"+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setDrawingCacheEnabled(true);
                tmp.buildDrawingCache(true);
                Bitmap a = tmp.getDrawingCache();
                int pixel = a.getPixel(0,0);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int color = Color.argb(255,r,g,b);
                return color;
            }

        }
        else{
            if(c >= 10){
                id = this.getResources().getIdentifier("T0"+String.valueOf(f)+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setDrawingCacheEnabled(true);
                tmp.buildDrawingCache(true);
                Bitmap a = tmp.getDrawingCache();
                int pixel = a.getPixel(0,0);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int color = Color.argb(255,r,g,b);
                return color;
            }
            else{
                id = this.getResources().getIdentifier("T0"+String.valueOf(f)+"0"+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setDrawingCacheEnabled(true);
                tmp.buildDrawingCache(true);
                Bitmap a = tmp.getDrawingCache();
                int pixel = a.getPixel(0,0);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int color = Color.argb(255,r,g,b);
                return color;
            }
        }
    }

    public void setColorpos(int f, int c, int color){
        int id;
        if (f>10){
            if(c >= 10){
                id = this.getResources().getIdentifier("T"+String.valueOf(f)+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(color);
            }
            else{
                id = this.getResources().getIdentifier("T"+String.valueOf(f)+"0"+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(color);
            }

        }
        else{
            if(c >= 10){
                id = this.getResources().getIdentifier("T0"+String.valueOf(f)+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(color);
            }
            else{
                id = this.getResources().getIdentifier("T0"+String.valueOf(f)+"0"+String.valueOf(c), "id", this.getPackageName());
                ImageView tmp = findViewById(id);
                tmp.setBackgroundColor(color);
            }
        }
    }

    public void downlines(int f){
        f -= 1;
        for (int k = f;k>0;k--){
            for(int c=1; c<11; c++){
                if (tablero[k][c]==1){
                    tablero[k][c] = 0;
                    tablero[k+1][c] = 1;
                    int color = getColorpos(k,c);
                    clearPos(k,c);
                    setColorpos(k+1,c,color);
                }
            }
        }
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
        ImageButton rotarBtn = findViewById(R.id.rotarBtn);
        ImageButton infoBtn = findViewById(R.id.infoBtn);

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
                while (CheckMove(1,0)){
                    clearPiece();
                    limpiarTablero();
                    movimiento(1,0);
                    agregarTablero();
                    drawPiece();
                }
                vereficarLineas();
                generarPieza();


            }
        });

        rotarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotar();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Developer: Juan Jose A. Castro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
