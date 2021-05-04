package com.clinica.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PacienteBD extends SQLiteOpenHelper {

    public PacienteBD(Context context){
        super(context, Constantes.NOMBRE_BD, null,Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + Constantes.NOMBRE_TABLA +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " documento TEXT NOT NULL, " +
                " numdocumento INTEGER NOT NULL, " +
                " nombre TEXT NOT NULL, " +
                " appaterno TEXT NOT NULL, " +
                " apmaterno TEXT NOT NULL, " +
                " sexo TEXT NOT NULL, " +
                " correo TEXT NOT NULL, " +
                " contrasena TEXT NOT NULL, " +
                " direccion TEXT NOT NULL ) ;";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
