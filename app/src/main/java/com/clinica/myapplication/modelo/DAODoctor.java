package com.clinica.myapplication.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.clinica.myapplication.entidades.Doctor;
import com.clinica.myapplication.util.Constantes;
import com.clinica.myapplication.util.DoctorDB;

import java.util.ArrayList;

public class DAODoctor {
    DoctorDB doctorDB;
    SQLiteDatabase db;
    private Context context;

    public DAODoctor(Context context){
        doctorDB = new DoctorDB(context);
        this.context = context;
    }

    public void abrirDB(){
        db = doctorDB.getWritableDatabase();
    }

    public void registrarDoctor(Doctor doctor){
        try {
            ContentValues valores = new ContentValues();
            valores.put("noDoctor", doctor.getNoDoctor());
            valores.put("apePatDoctor", doctor.getApePatDoctor());
            valores.put("apeMatDoctor", doctor.getApeMatDoctor());
            valores.put("Cellphone", doctor.getCellphone());
            long resultado = db.insert(Constantes.TABLA_DOCTOR,null, valores);
            if (resultado == -1){
                //Toast.makeText(context, "Error en registrar Especialidad", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Doctor> mostrarDoctor(){
        ArrayList<Doctor> listaDoctor = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM " + Constantes.TABLA_DOCTOR, null);
            while (c.moveToNext()){
                listaDoctor.add(new Doctor(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                ));
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return listaDoctor;
    }
}
