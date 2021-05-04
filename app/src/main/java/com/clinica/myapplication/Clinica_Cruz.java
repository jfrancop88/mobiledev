package com.clinica.myapplication;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.clinica.myapplication.entidades.Paciente;
import com.clinica.myapplication.modelo.DAODoctor;
import com.clinica.myapplication.modelo.DAOEspecialidad;
import com.clinica.myapplication.modelo.DAOPaciente;


public class Clinica_Cruz extends AppCompatActivity {

    Button registrar;
    Button login;
    Button admin;

    DAOPaciente daoPaciente = new DAOPaciente(this);
    DAODoctor daoDoctor = new DAODoctor(this);
    DAOEspecialidad daoEspecialidad = new DAOEspecialidad(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica__cruz);

        daoPaciente.abrirBD();
        daoDoctor.abrirDB();
        daoEspecialidad.abrirDB();


        registrar = (Button) findViewById(R.id.registro);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clinica_Cruz.this, AgregarPaciente.class);
                startActivity(intent);
            }
        });

        admin = (Button) findViewById((R.id.btn_admin));
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Clinica_Cruz.this, ClinicaSqlite.class);
                startActivity(intent);
            }
        });

        login = (Button) findViewById(R.id.sesion);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Clinica_Cruz.this, IniciarSesion.class));
            }
        });

    }

}