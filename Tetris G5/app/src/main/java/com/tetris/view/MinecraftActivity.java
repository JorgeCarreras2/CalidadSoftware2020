package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.model.impl.CustomShape;
import com.tetris.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class MinecraftActivity extends AppCompatActivity {


    private int numColor = 2;
    private int maxCeldas = 6;
    private ImageButton bCelda1;
    private ImageButton bCelda2;
    private ImageButton bCelda3;
    private ImageButton bCelda4;
    private ImageButton bCelda5;
    private ImageButton bCelda6;
    private ImageButton bCelda7;
    private ImageButton bCelda8;
    private ImageButton bCelda9;

    private List<ImageButton> celdas = new ArrayList<>();
    private Vibrator vibe;

    private boolean[][] positionLocator = new boolean[3][3];
    private List<Block> blocksCustomShape = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecraft);
        vibe = (Vibrator) MinecraftActivity.this.getSystemService(MinecraftActivity.VIBRATOR_SERVICE);

        setUpButtonsCeldas();

        setUpButtonsColors();

        setUpButtonsFinal();
    }

    private void setUpButtonsFinal(){
        ImageButton bCancelar = findViewById(R.id.mine_cancel);
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(40);
                finish();
            }
        });
        ImageButton bCreate = findViewById(R.id.mine_newshape);
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maxCeldas>3){
                    vibe.vibrate(40);
                    Toast.makeText(getApplicationContext(), "Debe coger un mínimo de 3 bloques", Toast.LENGTH_LONG).show();
                }else{
                    createCustomShape();
                    openGameActivity();
                }

            }
        });
    }
    private void openGameActivity() {
        Board.getInstance().setGameMode(Board.GameMode.MODE_MINECRAFT);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void setUpButtonsCeldas() {

        bCelda1 = findViewById(R.id.celda1);
        bCelda1.setTag(0);
        bCelda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda1);
            }
        });
        celdas.add(bCelda1);

        bCelda2 = findViewById(R.id.celda2);
        bCelda2.setTag(0);
        bCelda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda2);
            }
        });
        celdas.add(bCelda2);

        bCelda3 = findViewById(R.id.celda3);
        bCelda3.setTag(0);
        bCelda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda3);
            }
        });
        celdas.add(bCelda3);

        bCelda4 = findViewById(R.id.celda4);
        bCelda4.setTag(0);
        bCelda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda4);
            }
        });
        celdas.add(bCelda4);

        bCelda5 = findViewById(R.id.celda5);
        bCelda5.setTag(0);
        bCelda5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda5);
            }
        });
        celdas.add(bCelda5);

        bCelda6 = findViewById(R.id.celda6);
        bCelda6.setTag(0);
        bCelda6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda6);
            }
        });
        celdas.add(bCelda6);

        bCelda7 = findViewById(R.id.celda7);
        bCelda7.setTag(0);
        bCelda7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda7);
            }
        });
        celdas.add(bCelda7);

        bCelda8 = findViewById(R.id.celda8);
        bCelda8.setTag(0);
        bCelda8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda8);
            }
        });
        celdas.add(bCelda8);

        bCelda9 = findViewById(R.id.celda9);
        bCelda9.setTag(0);
        bCelda9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda9);
            }
        });
        celdas.add(bCelda9);
    }

    private void setUpButtonsColors() {
        colores(R.id.color1, 2);

        colores(R.id.color2, 5);

        colores(R.id.color3, 6);

        colores(R.id.color4, 0);

        colores(R.id.color5, 3);

        colores(R.id.color6, 4);

        colores(R.id.color7, 1);

        colores(R.id.color8, 7);
    }

    private void colores(int p, final int i) {
        ImageButton bColor1 = findViewById(p);
        bColor1.setImageResource(Colors.colorSelectorID(i));
        bColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(i);
            }
        });
    }

    private void celdaEvent(ImageButton celda){
        int drawable = (Integer) celda.getTag();
        if ((maxCeldas == 0) && (drawable == 0)){ //drawable == 0 is cell no selected
            Toast.makeText(getApplicationContext(), "Debe coger un máximo de 6 bloques", Toast.LENGTH_LONG).show();
        } else {
            chooseColor(celda);
        }
        vibe.vibrate(40);
    }

    private void colorEvent(int num){
        numColor = num;
        updateColor();
        vibe.vibrate(40);
    }

    private void chooseColor(ImageButton celda){
        int drawable = (Integer) celda.getTag();
        if (drawable == 0){
            setMaxCeldas(getMaxCeldas()-1);
            celda.setTag(Colors.colorSelectorID(getNumColor()));
            celda.setImageResource(Colors.colorSelectorID(getNumColor()));
        }else{
            setMaxCeldas(getMaxCeldas()+1);
            celda.setTag(0);
            celda.setImageResource(android.R.color.transparent);
        }
    }

    private void updateColor(){
        for (ImageButton c :celdas) {
            int drawable = (Integer) c.getTag();
            if (drawable != 0){
                c.setTag(Colors.colorSelectorID(getNumColor()));
                c.setImageResource(Colors.colorSelectorID(getNumColor()));
            }
        }
    }

    private void isClicked(int drawable, int i){
        int x = 4;
        int y = -4;
        posiciones(drawable, i, x, y, 1, 0, 0);
        posiciones(drawable, i, x + 1, y, 2, 1, 0);
        posiciones(drawable, i, x + 2, y, 3, 2, 0);
        posiciones(drawable, i, x, y + 1, 4, 0, 1);
        posiciones(drawable, i, x + 1, y + 1, 5, 1, 1);
        posiciones(drawable, i, x + 2, y + 1, 6, 2, 1);
        posiciones(drawable, i, x, y + 2, 7, 0, 2);
        posiciones(drawable, i, x + 1, y + 2, 8, 1, 2);
        posiciones(drawable, i, x + 2, y + 2, 9, 2, 2);
    }

    private void posiciones(int drawable, int i, int x, int y, int posicionI, int posicionLocatorX, int posicionLocatorY) {
        if ((i == posicionI) && (drawable != 0)) {
            Block block = new Block();
            block.setX(x);
            block.setY(y);
            positionLocator[posicionLocatorX][posicionLocatorY] = true;
            blocksCustomShape.add(block);
        }
    }

    private void createCustomShape(){
        for (int i = 0; i < 9; i++) {
            int drawable = (Integer) celdas.get(i).getTag();
            isClicked(drawable,i + 1);
        }

        Board.getInstance().setMinecraftShape(new CustomShape(
                Board.getInstance().getSpawnY(),
                numColor,
                blocksCustomShape,
                positionLocator
        ));
    }


    public int getMaxCeldas() {
        return maxCeldas;
    }

    public void setMaxCeldas(int maxCeldas) {
        this.maxCeldas = maxCeldas;
    }

    public int getNumColor() {
        return numColor;
    }


}
