package com.example.practica2;

public class DatosRegistro {
    // Variables que conforman la clase DatosRegistro
    private int id;
    private String nombre;
    private String dni;
    private String correo;
    private String nacionalidad;
    private String boletin;

    // Constructor
    public DatosRegistro(int id, String nombre, String dni, String correo, String nacionalidad, String boletin){
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.boletin = boletin;
    }

    public DatosRegistro(String nombre, String dni, String correo, String nacionalidad, String boletin){
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.boletin = boletin;
    }

    // Getters y Setters asociados a las variables de la clase
    public String getNombre(){
        return this.nombre;
    }
    public int getId(){
        return this.id;
    }
    public String getDni(){
        return this.dni;
    }
    public String getCorreo(){
        return this.correo;
    }
    public String getNacionalidad(){
        return this.nacionalidad;
    }
    public String getBoletin(){
        return this.boletin;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setDni(String dni){
        this.dni = dni;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;
    }

    public void setBoletin(String boletin){
        this.boletin = boletin;
    }

    public void setId(int id){
        this.id = id;
    }

}
