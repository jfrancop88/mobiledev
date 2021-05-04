package com.clinica.myapplication.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.clinica.myapplication.entidades.Especialidad;
import com.clinica.myapplication.util.Constantes;
import com.clinica.myapplication.util.EspecialidadDB;

import java.util.ArrayList;

public class DAOEspecialidad {
    EspecialidadDB especialidadDB;
    SQLiteDatabase db;
    private Context context;

    public DAOEspecialidad(Context context){
        especialidadDB = new EspecialidadDB(context);
        this.context = context;
    }

    public void abrirDB(){
        db = especialidadDB.getWritableDatabase();
    }

    public void registrarEspecialidad(Especialidad especialidad){
        try {
            ContentValues valores = new ContentValues();
            valores.put("noEspecialidad", especialidad.getNoEspecialidad());
            long resultado = db.insert(Constantes.TABLA_ESPECIALIDAD,null, valores);
            if (resultado == -1){
                //Toast.makeText(context, "Error en registrar Especialidad", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void modificarEspecialidad(Especialidad especialidad){
        try {
            ContentValues valores = new ContentValues();
            valores.put("noEspecialidad", especialidad.getNoEspecialidad());
            long resultado = db.update(Constantes.TABLA_ESPECIALIDAD, valores, "id="+especialidad.getCoEspecialidad(),null);
            if (resultado == -1){
                Toast.makeText(context, "Error en Actualizar Especialidad", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Actualización Exitosa", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarEspecialidad(int id){
        try {
            long resultado = db.delete(Constantes.TABLA_ESPECIALIDAD, "id=" + id, null);
            if (resultado == -1){
                Toast.makeText(context, "Error en Eliminar Especialidad", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Eliminación Exitosa", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Especialidad> mostrarEspecialidad(){
        ArrayList<Especialidad> listaEspecialidad = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM " + Constantes.TABLA_ESPECIALIDAD, null);
            while (c.moveToNext()){
                listaEspecialidad.add(new Especialidad(
                        c.getInt(0),
                        c.getString(1)
                ));
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return listaEspecialidad;
    }
}
