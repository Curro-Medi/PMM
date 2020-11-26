package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);             //muestro el xml del main
        Button btn = (Button) findViewById(R.id.btnform);   //instancio el boton con id del boton que hace que se muestre el Layoutform formulario
        btn.setOnClickListener(this);                       //metodo que escucha el boton

        Button btn2 = (Button) findViewById(R.id.btnemail); //instancio el boton con id del boton que hace que se muestre el email
        btn2.setOnClickListener(this);                      //metodo que escucha el boton

        Button btn3 = (Button) findViewById(R.id.btnbbdd);
        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {                                               //metodo onclick, cuando se hace click en algo

        if(v.getId()==R.id.btnform) {                                           //busco por id el boton que se pulsa para que el boton que queramos sea el que pulsamos
            Intent intent = new Intent(v.getContext(), LayoutForm.class);       //creo el intent y adquiero el contenido de la clase layoutform
            startActivity(intent);                                              //empiezo la actividad

        }else if (v.getId()==R.id.btnbbdd){

            Intent intent2 = new Intent(v.getContext(), Muestrabbdd.class);
            startActivity(intent2);

        }else{

            Intent envemail = new Intent(Intent.ACTION_SENDTO);                                                         //intent sendto que abre el email
            envemail.setData(Uri.parse("mailto:"));
            envemail.putExtra(Intent.EXTRA_EMAIL, new String[] {"francisco.mediavilla.garcia@alumnos.fesac.es"});       //correo que pone por defecto al que envias
            envemail.putExtra(Intent.EXTRA_SUBJECT, "Sugerencias");                                               //pone un sujeto al correo
            startActivity(envemail);                                                                                    //da comienzo a la actividad

        }

    }
}