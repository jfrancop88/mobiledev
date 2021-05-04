package com.clinica.myapplication.entidades;

public class Especialidad {
    private  int coEspecialidad;
    private String noEspecialidad;

    public Especialidad(String noEspecialidad){
        this.noEspecialidad = noEspecialidad;
    }

    public Especialidad(int coEspecialidad, String noEspecialidad){
        this.coEspecialidad = coEspecialidad;
        this.noEspecialidad = noEspecialidad;
    }

    public int getCoEspecialidad() {
        return coEspecialidad;
    }

    public void setCoEspecialidad(int coEspecialidad) {
        this.coEspecialidad = coEspecialidad;
    }

    public String getNoEspecialidad() {
        return noEspecialidad;
    }

    public void setNoEspecialidad(String noEspecialidad) {
        this.noEspecialidad = noEspecialidad;
    }
}
