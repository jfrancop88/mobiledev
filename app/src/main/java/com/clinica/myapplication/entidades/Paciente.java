package com.clinica.myapplication.entidades;

public class Paciente {
    private int id;
    private String documento;
    private int numdocumenot;
    private String nombre;
    private String appaterno;
    private String apmaterno;
    private String sexo;
    private String correo;
    private String contrasena;
    private String direccion;

    public Paciente(String documento, int numdocumenot, String nombre, String appaterno, String apmaterno, String sexo, String correo, String contrasena, String direccion) {
        this.documento = documento;
        this.numdocumenot = numdocumenot;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.sexo = sexo;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
    }

    public Paciente(int id, String documento, int numdocumenot, String nombre, String appaterno, String apmaterno, String sexo, String correo, String contrasena, String direccion) {
        this.id = id;
        this.documento = documento;
        this.numdocumenot = numdocumenot;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.sexo = sexo;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getNumdocumenot() {
        return numdocumenot;
    }

    public void setNumdocumenot(int numdocumenot) {
        this.numdocumenot = numdocumenot;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
