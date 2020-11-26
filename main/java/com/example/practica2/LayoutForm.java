package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


        nomyape =(EditText) findViewById(R.id.textnom);             //en estas a partir de lo que se ponga en los texts de layoutform xml se guardan los datos
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

        String nya = nomyape.getText().toString().trim();       //coge el nombre y el trim hace que no cuente ni espacios delante ni detras
        int asci = 0;
        int i = 0;
        int cont = 0;

        if (nya.isEmpty()){             //si esta vacio el booleano seguira siendo false asi que saltara la excepcion

        }else {


            do {
                asci = nya.codePointAt(cont);           //va de un caracter en un caracter
                if (validanombre = (asci == 32)) {      //si hay un espacio se incrementa la i
                    i++;
                }
                cont++;
            } while (cont < nya.length());              //hasta que no termine el length no acaba

            if (i == 2) {                               //si ha conseguido ser 2 la i sera porque existen 2 espacios asi que se pondra true sino seguira siendo false
                validanombre = true;
            } else {
                validanombre = false;
            }
        }
    }

    public boolean compruebaDNI(){

        String dni2 = dni.getText().toString();     //adquiere el dni

        int i = 0;
        int asci = 0;
        char letra =' ';
        int eldni = 0;

        if (dni2.length() == 9 && Character.isLetter(dni2.charAt(8))){      //si hay 9 caracteres y uno es letra entra en el if

            do{
                asci = dni2.codePointAt(i);
                valido = (asci > 47 && asci < 58);          //comprueba si hay un numero si hay el booleano sera true
                i++;

            }while (i < dni2.length() - 1 && valido);       // mientras que la i sea menor que el length - 1 (quitando el numero) y es valido si antes hay un numero
        }

        if (valido){
            letra = Character.toUpperCase(dni2.charAt(8));      //letra en mayuscula
            eldni = Integer.parseInt(dni2.substring(0,8));      //parseamos numeros para devolver en valido

        }

        return valido;
    }

    public void compruebanacionalidad(){

        Spinner spinnerpaises = (Spinner) findViewById(R.id.paises);        //adquirimos la eleccion del spinner
        String eleccion =  spinnerpaises.getSelectedItem().toString();      //guardamos en la variable la eleccion del spinner
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

                if (asci == 64){            //comprueba si hay un arroba guardandolo en variable
                    posarroba = cont;
                }
                if (asci == 46){            //y si hay un punto guardandolo en variable
                    pospunto = cont;
                }

                if (validacorreo = (email.codePointAt(cont) == 64 || email.codePointAt(cont) == 46 )) {     //valida el correo y si hay un punto y un arroba se incrementa la i
                    i++;
                }

                cont++;
            } while (cont < email.length());        //length del email

            if (i == 2 && posarroba<pospunto) {     //si la i es 2 (hay punto y arroba) y la posicion del arroba es menor a la del punto (asi comprobamos que el punto esta detras del arroba
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


        if (nya.isEmpty()){     //salta un toast tela de chulo de que esta en blanco
            nomyape.setError("No puedes dejar esto en blanco");
            toast = false;

        }else if (validanombre == false){       //si el nombre es valido o no
            nombool = false;
        }else {
            nombool = true;
        }

        if (dni2.isEmpty()){
            dni.setError("No puedes dejar esto en blanco");
            toast = false;

        }else if (valido == false){     //si el dni es valido o no
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

        //al darle click algo esta mal salta el toast

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

        //ingresamos todos los datos en la bbdd
        String nya = nomyape.getText().toString();
        String dni2 = dni.getText().toString();
        String email = correo.getText().toString();
        Spinner spinnerpaises = (Spinner) findViewById(R.id.paises);
        boolean suscripcion = box.isChecked();
        String eleccion =  spinnerpaises.getSelectedItem().toString();

        Intent pasardatos = new Intent(LayoutForm.this, Muestrabbdd.class);
        String box = "";
        if (suscripcion==false){
            box = "No";
        }else {
            box = "Si";
        }


        GuardarBBDD registrar = new GuardarBBDD(this);
        registrar.registrarDatos(new DatosRegistro(nya,dni2,email,eleccion,box));




        if (nombool == true && dnibool == true && correobool == true){
            Toast toad = Toast.makeText(this, "Datos enviados correctamente a la base de datos", Toast.LENGTH_SHORT);
            toad.show();
            finish();
        }


    }

}