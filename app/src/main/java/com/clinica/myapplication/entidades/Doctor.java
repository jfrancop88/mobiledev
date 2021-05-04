package com.clinica.myapplication.entidades;

public class Doctor {
    private  int coDoctor;
    private String noDoctor;
    private String apePatDoctor;
    private String apeMatDoctor;
    private String cellphone;

    public Doctor(String noDoctor, String apePatDoctor, String apeMatDoctor, String cellphone){
        this.noDoctor = noDoctor;
        this.apePatDoctor = apePatDoctor;
        this.apeMatDoctor = apeMatDoctor;
        this.cellphone = cellphone;
    }

    public Doctor(int coDoctor, String noDoctor, String apePatDoctor, String apeMatDoctor, String cellphone){
        this.coDoctor = coDoctor;
        this.noDoctor = noDoctor;
        this.apePatDoctor = apePatDoctor;
        this.apeMatDoctor = apeMatDoctor;
        this.cellphone = cellphone;
    }

    public int getCoDoctor() {
        return coDoctor;
    }

    public void setCoDoctor(int coDoctor) {
        this.coDoctor = coDoctor;
    }

    public String getNoDoctor() {
        return noDoctor;
    }

    public void setNoDoctor(String noDoctor) {
        this.noDoctor = noDoctor;
    }

    public String getApePatDoctor() {
        return apePatDoctor;
    }

    public void setApePatDoctor(String apePatDoctor) {
        this.apePatDoctor = apePatDoctor;
    }

    public String getApeMatDoctor() {
        return apeMatDoctor;
    }

    public void setApeMatDoctor(String apeMatDoctor) {
        this.apeMatDoctor = apeMatDoctor;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }



}
