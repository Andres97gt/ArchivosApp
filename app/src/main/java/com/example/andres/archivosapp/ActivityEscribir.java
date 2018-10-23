package com.example.andres.archivosapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ActivityEscribir extends AppCompatActivity {

    RadioButton interna;
    RadioButton externa;
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir);
        interna = findViewById(R.id.rbnInterna);
        externa = findViewById(R.id.rbnExterna);
        texto = findViewById(R.id.editText);
    }

    public void Guardar(View v){
        if(interna.isChecked()){
            try {
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("Datos.txt", Context.MODE_PRIVATE));
                osw.write(texto.getText().toString());
                osw.close();
                texto.setText("");
                Toast.makeText(getApplicationContext(),"Datos guardados en la memoria interna",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.e("Ficheros","Error en la memoria interna");
            }

        }else{
            verificarPermisos();
            try {

                String estado = Environment.getExternalStorageState();
                if (estado.equals(Environment.MEDIA_MOUNTED)){
                    File rutaSD = Environment.getExternalStorageDirectory();
                    File f = new File(rutaSD.getAbsolutePath(),"DatosSD.txt");
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
                    osw.write(texto.getText().toString());
                    osw.close();
                    Toast.makeText(getApplicationContext(),"Datos guardados en la memoria externa SD",Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Log.d("exit" , e.getMessage() + " ---------");
            }
            texto.setText("");

        }
        texto.setText("");
    }

    private void verificarPermisos() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivityEscribir.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
    }
}
