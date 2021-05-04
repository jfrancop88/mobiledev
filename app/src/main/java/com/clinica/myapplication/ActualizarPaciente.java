package com.clinica.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clinica.myapplication.entidades.Paciente;
import com.clinica.myapplication.modelo.DAOPaciente;

public class ActualizarPaciente extends AppCompatActivity {

    EditText edit_documento, edit_numdocumento, edit_nombre, edit_appaterno, edit_apmaterno, edit_sexo, edit_correo, edit_contrasena, edit_direccion;
    Button buttonActualizar, buttonEliminar;

    String documento, nombre, appaterno, apmaterno, sexo, correo, contrasena, direccion;
    int numdocumento, id;

    Paciente paciente;
    DAOPaciente daoPaciente = new DAOPaciente(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_paciente);
        asignarReferencias();
        recibirInformacion();
    }
    private void asignarReferencias(){
        edit_documento = findViewById(R.id.edit_documento);
        edit_numdocumento = findViewById(R.id.edit_numdocumento);
        edit_nombre = findViewById(R.id.edit_nombre);
        edit_appaterno = findViewById(R.id.edit_appaterno);
        edit_apmaterno = findViewById(R.id.edit_apmaterno);
        edit_sexo = findViewById(R.id.edit_sexo);
        edit_correo = findViewById(R.id.edit_correo);
        edit_contrasena = findViewById(R.id.edit_contrasena);
        edit_direccion = findViewById(R.id.edit_direccion);
        buttonActualizar = findViewById(R.id.buttonActualizar);
        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoPaciente.abrirBD();
                capturarDatos();
                daoPaciente.modificarPaciente(paciente);
            }
        });
        buttonEliminar = findViewById(R.id.buttonEliminar);
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //daoPaciente.abrirBD();
                //daoPaciente.eliminarPaciente(id);
               confirmar();
            }
        });
    }
   private void confirmar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar a: " + nombre + appaterno + " con DNI: " + numdocumento + " ?");
        builder.setMessage("Desea eliminar al paciente con DNI: " + numdocumento + " ?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                daoPaciente.abrirBD();
                daoPaciente.eliminarPaciente(id);
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void capturarDatos(){
        documento = edit_documento.getText().toString();
        numdocumento = Integer.parseInt(edit_numdocumento.getText().toString());
        nombre = edit_nombre.getText().toString();
        appaterno = edit_appaterno.getText().toString();
        apmaterno = edit_apmaterno.getText().toString();
        sexo = edit_sexo.getText().toString();
        correo = edit_correo.getText().toString();
        contrasena = edit_contrasena.getText().toString();
        direccion = edit_direccion.getText().toString();
        paciente = new Paciente(id, documento, numdocumento, nombre, appaterno, apmaterno, sexo, correo, contrasena, direccion);
    }
    private void recibirInformacion(){
        documento = getIntent().getStringExtra("documento");
        numdocumento = Integer.parseInt(getIntent().getStringExtra("numdocumento"));
        nombre = getIntent().getStringExtra("nombre");
        appaterno = getIntent().getStringExtra("paterno");
        apmaterno = getIntent().getStringExtra("materno");
        sexo = getIntent().getStringExtra("sexo");
        correo = getIntent().getStringExtra("correo");
        contrasena = getIntent().getStringExtra("contrasena");
        direccion = getIntent().getStringExtra("direccion");
        id = Integer.parseInt(getIntent().getStringExtra("id"));

        edit_documento.setText(documento);
        edit_numdocumento.setText(String.valueOf(numdocumento));
        edit_nombre.setText(nombre);
        edit_appaterno.setText(appaterno);
        edit_apmaterno.setText(apmaterno);
        edit_sexo.setText(sexo);
        edit_correo.setText(correo);
        edit_contrasena.setText(contrasena);
        edit_direccion.setText(direccion);
    }
}