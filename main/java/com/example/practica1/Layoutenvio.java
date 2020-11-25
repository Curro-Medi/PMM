package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Layoutenvio extends AppCompatActivity {

    TextView verdatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutenvio);

        recibirdatos();

    }


    private void recibirdatos(){
        Bundle datos = getIntent().getExtras();

        String datonom = datos.getString("Pasanombre");
        String datodni = datos.getString("Pasadni");
        String datoemail = datos.getString("PasaCorreo");
        String datospin = datos.getString("PasaSpinner");
        String suscripcion = datos.getString("pasasuscripcion");

        verdatos = (TextView) findViewById(R.id.verdatos);
        verdatos.setText(datonom + "\n" + datodni + "\n" + datoemail + "\n" + datospin + "\n" + suscripcion);

    }




}