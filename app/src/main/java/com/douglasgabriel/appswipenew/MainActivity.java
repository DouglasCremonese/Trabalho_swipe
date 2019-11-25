package com.douglasgabriel.appswipenew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout tela;
    private TextView tvSwipe;
    private String[] perguntas = {"Você gosta de sorvete?","Você gosta de ler?","Você de gosta de frutas?","Você tem certeza de sua existência?","Você é você?"};
    List<String> lista = new ArrayList<String>(Arrays.asList(perguntas));
    private TextView tvSim;
    private TextView tvNao;
    private int cont = 0;
    private int sim = 0;
    private int nao = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tela = (ConstraintLayout) findViewById(R.id.tela);
        tvSwipe = (TextView) findViewById(R.id.tvSwipe);
        tvSim = (TextView) findViewById(R.id.tvSim);
        tvNao = (TextView) findViewById(R.id.tvNao);
        tvSwipe.setText(perguntas[0]);
        tela.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                nao = nao + 1;
                resposta();
                //tvSwipe.setText("Não");
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();

                sim = sim + 1;

                resposta();
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                muda();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                cont = cont - 1;
                if (cont == -1) {
                    cont = lista.size() - 1;
                }
                tvSwipe.setText(lista.toArray()[cont].toString());
            }
        });
    }
    public void muda(){
        cont = cont + 1;
        if(cont == lista.size()){
            cont = 0;
        }
        tvSwipe.setText(lista.toArray()[cont].toString());
    }

    public void resposta(){
        if(lista.size() > 1){
            muda();
            lista.remove(cont);
        }
        else{
            tvSim.setText("");
            tvNao.setText("");
            tvSwipe.setText("Sim: "+sim+"\n Não: "+nao);
        }
    }
}
