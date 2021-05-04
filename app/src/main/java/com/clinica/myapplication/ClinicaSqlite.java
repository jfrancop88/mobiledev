package com.clinica.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.clinica.myapplication.entidades.Paciente;
import com.clinica.myapplication.modelo.DAOPaciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ClinicaSqlite extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatPacient;

    DAOPaciente daoPaciente = new DAOPaciente(this);
    ArrayList<Paciente> listapaciente = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica_sqlite);
        daoPaciente.abrirBD();
        asignarReferencias();
        mostrarPacientes();
    }

    private void mostrarPacientes(){
        listapaciente = daoPaciente.mostrarPacientes();
        customAdapter = new CustomAdapter(ClinicaSqlite.this, this,listapaciente);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ClinicaSqlite.this));
    }

    private void asignarReferencias(){
        recyclerView = findViewById(R.id.recyclerview);
        floatPacient = findViewById(R.id.floatPacient);
        floatPacient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ClinicaSqlite.this, "Click correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ClinicaSqlite.this, AgregarPaciente.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
}