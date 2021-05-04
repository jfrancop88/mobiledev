package com.clinica.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoctorDB extends SQLiteOpenHelper {

    public DoctorDB(Context context){
        super(context, Constantes.NOMBRE_BD, null,Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + Constantes.TABLA_DOCTOR +
                        " (coDoctor INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " noDoctor TEXT NOT NULL, " +
                        " apePatDoctor TEXT NOT NULL, " +
                        " apeMatDoctor TEXT NOT NULL, " +
                        " cellphone TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
