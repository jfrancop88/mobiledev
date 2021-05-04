package com.clinica.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clinica.myapplication.entidades.Paciente;
import com.clinica.myapplication.modelo.DAOPaciente;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AgregarPaciente extends AppCompatActivity {

    EditText documento, numdocumento, nombre, appaterno, apmaterno, sexo, correo, contrasena, direccion;
    Button buttonRegistrar;

    DAOPaciente daoPaciente = new DAOPaciente(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);
        asignarReferencia();
    }

    private void asignarReferencia() {
        documento = findViewById(R.id.documento);
        numdocumento = findViewById(R.id.numdocumento);
        nombre = findViewById(R.id.nombre);
        appaterno = findViewById(R.id.appaterno);
        apmaterno = findViewById(R.id.apmaterno);
        sexo = findViewById(R.id.sexo);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        direccion = findViewById(R.id.direccion);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoPaciente.abrirBD();
                Paciente paciente = new Paciente(documento.getText().toString(), Integer.parseInt(numdocumento.getText().toString()), nombre.getText().toString(), appaterno.getText().toString(), apmaterno.getText().toString(), sexo.getText().toString(), correo.getText().toString(), contrasena.getText().toString(), direccion.getText().toString());
                daoPaciente.registrarPaciente(paciente);
            }
        });
    }
}

