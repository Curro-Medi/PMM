package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Muestrabbdd extends AppCompatActivity {

    private ListView list;
    private AdaptadorRegistro adaptador;
    private ArrayList<DatosRegistro> listadatos;
    private GuardarBBDD bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestrabbdd);
        bbdd = new GuardarBBDD(this);

        listadatos = new ArrayList<DatosRegistro>();
        ListView list = findViewById(R.id.datos);

        listadatos = bbdd.obtenerDatos() ;
        adaptador = new AdaptadorRegistro(this, listadatos);

        list.setAdapter(adaptador);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tex = findViewById(R.id.idbbdd);       //busca por la id en el list view
                bbdd.eliminarDatos(tex.getText().toString());   //borra de la base de datos
                listadatos.remove(position);                    //borrar del listview
                
                Toast toad = Toast.makeText(Muestrabbdd.this, "Eliminado elemento con id: " + tex.getText().toString() , Toast.LENGTH_SHORT);
                toad.show();
                adaptador.notifyDataSetChanged();
                return false;
            }

        });

    }

}