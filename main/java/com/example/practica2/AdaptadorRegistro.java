package com.example.practica2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class AdaptadorRegistro extends BaseAdapter {

    private Context context; // Contexto de la aplicacion
    private ArrayList<DatosRegistro> listaRegistros; // Lista de objetos DatosRegistro
    private LayoutInflater inflater; // Inflador del contexto de la aplicacion

    // Constructor

    public AdaptadorRegistro(Activity context, ArrayList<DatosRegistro> listaRegistros){
        this.context = context;
        this.listaRegistros = listaRegistros;
        this.inflater = LayoutInflater.from(context);
    }

    // Se le llama cada en la construcción del ListView
    // Va elemento a elemento hasta rellenar el ListView con el ArrayList
    // Buscar método en la API correspondiente: parámetros de entrada y de salida

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = inflater.inflate(R.layout.datosbbdd , null);      //si esta vacia que se rellene
        }

        TextView txt1 = convertView.findViewById(R.id.idbbdd);          //busca la id
        TextView txt2 = convertView.findViewById(R.id.nombrebbdd);
        TextView txt3 = convertView.findViewById(R.id.dnibbdd);
        TextView txt4 = convertView.findViewById(R.id.correobbdd);
        TextView txt5 = convertView.findViewById(R.id.paisbbdd);
        TextView txt6 = convertView.findViewById(R.id.boletinbbdd);

        DatosRegistro datosRegistro = listaRegistros.get(position);     //se posiciona en ese registro de la tabla

        txt1.setText(String.valueOf(datosRegistro.getId()));                            //coge el registro del id
        txt2.setText(datosRegistro.getNombre());
        txt3.setText(datosRegistro.getDni());
        txt4.setText(datosRegistro.getCorreo());
        txt5.setText(datosRegistro.getNacionalidad());
        txt6.setText(datosRegistro.getBoletin());


        return convertView; //retorno el view convertido (los datos en el list)
    }



    @Override
    public int getCount() {
        return listaRegistros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRegistros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
