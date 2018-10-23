package com.example.andres.archivosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button leer;
    Button escribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leer = findViewById(R.id.btnLeer);
        escribir = findViewById(R.id.btnEscribir);
    }

    public void onClickLeer(View v){
        Intent i = new Intent(v.getContext(),ActivityLeer.class);
        startActivity(i);
    }

    public void onClickEscribir(View v){
        Intent i = new Intent(v.getContext(),ActivityEscribir.class);
        startActivity(i);
    }
}
