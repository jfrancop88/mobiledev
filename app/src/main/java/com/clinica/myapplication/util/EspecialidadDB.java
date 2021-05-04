package com.clinica.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.clinica.myapplication.entidades.Especialidad;

import java.sql.SQLClientInfoException;

public class EspecialidadDB extends SQLiteOpenHelper {

    public EspecialidadDB(Context context){
        super(context, Constantes.NOMBRE_BD, null,Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + Constantes.TABLA_ESPECIALIDAD +
                        " (coEspecialidad INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " noEspecialidad TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
