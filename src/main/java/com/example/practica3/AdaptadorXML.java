package com.example.practica3;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorXML extends BaseAdapter {

    private Context context; // Contexto de la aplicacion
    private List<StackOverflowXmlParser> listaEntradas; // Lista de objetos DatosRegistro
    private LayoutInflater inflater; // Inflador del contexto de la aplicacion

    // Constructor

   public AdaptadorXML(Activity context, List<StackOverflowXmlParser> listaEntradas){
        this.context = context;
        this.listaEntradas = listaEntradas;
        this.inflater = LayoutInflater.from(context);
   }

    public AdaptadorXML() {

    }

    // Se le llama en la construcción del ListView
    // Va elemento a elemento hasta rellenar el ListView con el ArrayList

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflamos la vista en el caso de que no exista. De esta forma optimizamos el rendimiento

        if(convertView == null){

           convertView = inflater.inflate(R.layout.fila_xml,parent,false);
        }

        // Obtenemos todas las vistas que conforman el layout

        TextView textoTitulo = (TextView) convertView.findViewById(R.id.titulo);
        TextView textoAutor = (TextView) convertView.findViewById(R.id.autor);

        // obtenemos el objeto Entrada (guardado en StackOverflowXmlParser) de la posicion actual

        StackOverflowXmlParser datos = listaEntradas.get(position);

        // Cambiamos los textos de la vista por los del array

        textoTitulo.setText(datos.getTitulo());
        textoAutor.setText("Autor: " + datos.getAutor());

        StackOverflowXmlParser so = new StackOverflowXmlParser();
        
        return convertView;

    }



    @Override
    public int getCount() {
        return listaEntradas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEntradas.get(position) ;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}