package com.example.practica2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class GuardarBBDD extends SQLiteOpenHelper {
public static  final int VERSION=2;

    public GuardarBBDD(Context contexto) {
        super(contexto, Constantes.BASE_DATOS, null, VERSION); }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table " + Constantes.TABLA_FORMULARIO + " (" + Constantes._ID + " integer Primary key autoincrement," + Constantes.NOMBRE + " text, "
                + Constantes.DNI + " text, " + Constantes.CORREO + " text ," + Constantes.NACIONALIDAD + " text," + Constantes.BOLETIN_NOTICIAS + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table " + Constantes.TABLA_FORMULARIO);
        onCreate(db);

    }

    public ArrayList<DatosRegistro> obtenerDatos(){

        SQLiteDatabase db = getReadableDatabase();
        String[] arrayBBDD= { Constantes._ID, Constantes.NOMBRE, Constantes.DNI, Constantes.CORREO, Constantes.NACIONALIDAD, Constantes.BOLETIN_NOTICIAS };
        Cursor pedri = db.query(Constantes.TABLA_FORMULARIO, arrayBBDD, null, null, null, null, Constantes._ID );
        ArrayList<DatosRegistro> datos = new ArrayList<DatosRegistro>();


        while (pedri.moveToNext()){ //mientras haya datos en el cursor que se vaya al siguiente

            DatosRegistro metetabla = new DatosRegistro(pedri.getInt(0), pedri.getString(1), pedri.getString(2),
                    pedri.getString(3), pedri.getString(4), pedri.getString(5));
            datos.add(metetabla);   //metemos los datos en el array
        }

        pedri.close();
        db.close();

        return datos;
    }

    public long registrarDatos(DatosRegistro datos){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues datostabla = new ContentValues();
        //datostabla.put(Constantes._ID, datos.getId());
        datostabla.put(Constantes.NOMBRE, datos.getNombre());       //registramos los datos
        datostabla.put(Constantes.DNI, datos.getDni());
        datostabla.put(Constantes.CORREO, datos.getCorreo());
        datostabla.put(Constantes.NACIONALIDAD, datos.getNacionalidad());
        datostabla.put(Constantes.BOLETIN_NOTICIAS, datos.getBoletin());

        long devolver = db.insert(Constantes.TABLA_FORMULARIO , null , datostabla);     //insertamos en tabla

        db.close();

        return devolver;
    }

    public void eliminarDatos(String id){

        SQLiteDatabase db = getReadableDatabase();
        db.delete(Constantes.TABLA_FORMULARIO, Constantes._ID + " = ? " , new String[] {id});

    }


}
