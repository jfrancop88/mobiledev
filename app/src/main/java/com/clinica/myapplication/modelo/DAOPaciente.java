package com.clinica.myapplication.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.clinica.myapplication.entidades.Paciente;
import com.clinica.myapplication.util.Constantes;
import com.clinica.myapplication.util.PacienteBD;

import java.util.ArrayList;

public class DAOPaciente {
        PacienteBD pacienteBD;
        SQLiteDatabase db;
        private Context context;

        public DAOPaciente(Context context){
            pacienteBD = new PacienteBD(context);
            this.context = context;
        }

        public void abrirBD(){
                db = pacienteBD.getWritableDatabase();
        }

        public void registrarPaciente(Paciente paciente){
                try {
                        ContentValues valores = new ContentValues();
                        valores.put("documento", paciente.getDocumento());
                        valores.put("numdocumento",paciente.getNumdocumenot());
                        valores.put("nombre", paciente.getNombre());
                        valores.put("appaterno", paciente.getAppaterno());
                        valores.put("apmaterno", paciente.getApmaterno());
                        valores.put("sexo", paciente.getSexo());
                        valores.put("correo", paciente.getCorreo());
                        valores.put("contrasena", paciente.getContrasena());
                        valores.put("direccion", paciente.getDireccion());
                        long resultado = db.insert(Constantes.NOMBRE_TABLA,null, valores);
                        if (resultado == -1){
                                Toast.makeText(context, "Error en registrar Paciente", Toast.LENGTH_SHORT).show();
                        }else{
                                Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        }
                }catch (Exception e){
                   Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
        public void modificarPaciente(Paciente paciente){
                try {
                        ContentValues valores = new ContentValues();
                        valores.put("documento", paciente.getDocumento());
                        valores.put("numdocumento",paciente.getNumdocumenot());
                        valores.put("nombre", paciente.getNombre());
                        valores.put("appaterno", paciente.getAppaterno());
                        valores.put("apmaterno", paciente.getApmaterno());
                        valores.put("sexo", paciente.getSexo());
                        valores.put("correo", paciente.getCorreo());
                        valores.put("contrasena", paciente.getContrasena());
                        valores.put("direccion", paciente.getDireccion());
                        long resultado = db.update(Constantes.NOMBRE_TABLA, valores, "id="+paciente.getId(),null);
                                if (resultado == -1){
                                        Toast.makeText(context, "Error en Actualizar Paciente", Toast.LENGTH_SHORT).show();
                                }else{
                                        Toast.makeText(context, "Actualización Exitosa", Toast.LENGTH_SHORT).show();
                                }
                }catch (Exception e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
        public void eliminarPaciente(int id){
                try {
                        long resultado = db.delete(Constantes.NOMBRE_TABLA, "id=" + id, null);
                        if (resultado == -1){
                                Toast.makeText(context, "Error en Eliminar Paciente", Toast.LENGTH_SHORT).show();
                        }else{
                                Toast.makeText(context, "Eliminación Exitosa", Toast.LENGTH_SHORT).show();
                        }
                }catch (Exception e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }

        public ArrayList<Paciente> mostrarPacientes(){
                ArrayList<Paciente> listaPaciente = new ArrayList<>();
                try {
                        Cursor c = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA, null);
                        while (c.moveToNext()){
                                listaPaciente.add(new Paciente(
                                        c.getInt(0),
                                        c.getString(1),
                                        c.getInt(2),
                                        c.getString(3),
                                        c.getString(4),
                                        c.getString(5),
                                        c.getString(6),
                                        c.getString(7),
                                        c.getString(8),
                                        c.getString(9)
                                ));
                        }
                }catch (Exception e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return listaPaciente;
        }
}
