package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LayoutForm extends AppCompatActivity implements View.OnClickListener{
    EditText nomyape, dni, correo;
    CheckBox box;
    boolean nombool = false;
    boolean dnibool = false;
    boolean correobool = false;
    boolean valido = false;
    boolean validanombre = false;
    boolean validacorreo = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_form);

        //Button btn2 = (Button) findViewById(R.id.btnsalir);       habria que crear unn boton que sea salir y la id ponerla donde esta btnsalir
        //btnsalir.setOnClickListener(this);


        Button btnenv = (Button) findViewById(R.id.envform);
        btnenv.setOnClickListener(this);


        nomyape =(EditText) findViewById(R.id.textnom);
        dni =(EditText) findViewById(R.id.textdni);
        correo =(EditText) findViewById(R.id.textcorreo);
        box = (CheckBox) findViewById(R.id.box);
        Spinner spinnerpaises = (Spinner) findViewById(R.id.paises);
        ArrayAdapter<CharSequence> adaptpaises = ArrayAdapter.createFromResource(this,R.array.pais, android.R.layout.simple_spinner_item);
        adaptpaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpaises.setAdapter(adaptpaises);
        String eleccion =  spinnerpaises.getSelectedItem().toString();
    }

    public void compruebaNombre(){

        String nya = nomyape.getText().toString().trim();
        int asci = 0;
        int i = 0;
        int cont = 0;

        if (nya.isEmpty()){

        }else {


            do {
                asci = nya.codePointAt(cont);
                if (validanombre = (asci == 32)) {
                    i++;
                }
                cont++;
            } while (cont < nya.length());

            if (i == 2) {
                validanombre = true;
            } else {
                validanombre = false;
            }
        }
    }

    public boolean compruebaDNI(){

        String dni2 = dni.getText().toString();

        int i = 0;
        int asci = 0;
        char letra =' ';
        int eldni = 0;

        if (dni2.length() == 9 && Character.isLetter(dni2.charAt(8))){

            do{
                asci = dni2.codePointAt(i);
                valido = (asci > 47 && asci < 58);
                i++;

            }while (i < dni2.length() - 1 && valido);
        }

        if (valido){
            letra = Character.toUpperCase(dni2.charAt(8));
            eldni = Integer.parseInt(dni2.substring(0,8));

        }

        return valido;
    }

    public void compruebanacionalidad(){

        Spinner spinnerpaises = (Spinner) findViewById(R.id.paises);
        String eleccion =  spinnerpaises.getSelectedItem().toString();
        /*Toast toad = Toast.makeText(this, "Nacionalidad: " + eleccion , Toast.LENGTH_SHORT);
        toad.show();*/

    }

    public void compruebacorreo(){

        String email = correo.getText().toString();
        int asci = 0;
        int i = 0;
        int cont = 0;
        int posarroba = 0;
        int pospunto = 0;

        if (email.isEmpty()){

        }else {
            do {

                asci = email.codePointAt(cont);

                if (asci == 64){
                    posarroba = cont;
                }
                if (asci == 46){
                    pospunto = cont;
                }

                if (validacorreo = (email.codePointAt(cont) == 64 || email.codePointAt(cont) == 46 )) {
                    i++;
                }

                cont++;
            } while (cont < email.length());

            if (i == 2 && posarroba<pospunto) {
                validacorreo = true;
            } else {
                validacorreo = false;
            }
        }
    }

    public boolean comprobar(){

        String nya = nomyape.getText().toString();
        String dni2 = dni.getText().toString();
        String email = correo.getText().toString();
        boolean toast = true;
        nombool = false;
        dnibool = false;
        correobool = false;


        if (nya.isEmpty()){
            nomyape.setError("No puedes dejar esto en blanco");
            toast = false;

        }else if (validanombre == false){
            nombool = false;
        }else{
            nombool = true;
        }
        //se suman 2 al contador si se entra al if

        if (dni2.isEmpty()){
            dni.setError("No puedes dejar esto en blanco");
            toast = false;

        }else if (valido == false){
            dnibool = false;
        }else{
            dnibool = true;
        }

        if (email.isEmpty()){
            correo.setError("No puedes dejar esto en blanco");
            toast = false;

        }else if(validacorreo == false){
            correobool = false;
        }else{
            correobool = true;
        }

        return toast;

    }

    @Override
    public void onClick(View v) {
        //finish();      simplemente con esto saldria de esta actividad y volveria a la primera, a partir de pulsar un boton

        compruebaNombre();
        compruebaDNI();
        compruebanacionalidad();
        compruebacorreo();
        comprobar();

        if (nombool == false && dnibool == false && correobool == false){
            Toast toad = Toast.makeText(this, "Parametros incorrectos", Toast.LENGTH_SHORT);
            toad.show();
        }else if(nombool == false && dnibool == false || dnibool == false && correobool == false || nombool == false && correobool == false){
            Toast toad = Toast.makeText(this, "Parametros incorrectos", Toast.LENGTH_SHORT);
            toad.show();
        }else if (nombool == false){
            Toast toad = Toast.makeText(this, "Parametro nombre incorrecto", Toast.LENGTH_SHORT);
            toad.show();
        }else if (dnibool == false){
            Toast toad = Toast.makeText(this, "Parametro DNI incorrecto", Toast.LENGTH_SHORT);
            toad.show();
        }else if (correobool == false){
            Toast toad = Toast.makeText(this, "Parametro correo incorrecto", Toast.LENGTH_SHORT);
            toad.show();
        }

        if (nombool == true && dnibool == true && correobool == true){
            Intent intent = new Intent(v.getContext(), Layoutenvio.class);
            startActivity(intent);
        }

        String nya = nomyape.getText().toString();
        String dni2 = dni.getText().toString();
        String email = correo.getText().toString();
        Spinner spinnerpaises = (Spinner) findViewById(R.id.paises);
        boolean suscripcion = box.isChecked();
        String eleccion =  spinnerpaises.getSelectedItem().toString();

        Intent pasardatos = new Intent(LayoutForm.this, Layoutenvio.class);
        pasardatos.putExtra("Pasanombre",nya);
        pasardatos.putExtra("Pasadni", dni2);
        pasardatos.putExtra("PasaCorreo", email);
        pasardatos.putExtra("PasaSpinner", eleccion);
        if (suscripcion==false){
            pasardatos.putExtra("pasasuscripcion","No");
        }else {
            pasardatos.putExtra("pasasuscripcion","Si");
        }
        startActivity(pasardatos);


    }

}