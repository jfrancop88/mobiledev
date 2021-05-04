package com.clinica.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.clinica.myapplication.entidades.Doctor;
import com.clinica.myapplication.entidades.Especialidad;
import com.clinica.myapplication.entidades.Paciente;
import com.clinica.myapplication.modelo.DAODoctor;
import com.clinica.myapplication.modelo.DAOEspecialidad;
import com.clinica.myapplication.modelo.DAOPaciente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class InicioPaciente extends AppCompatActivity {
    Button reservar;
    TextView nombrepaciente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_paciente);

        String nombre = getIntent().getStringExtra("Nombre");
        nombrepaciente = findViewById(R.id.lbl_nombres);
        nombrepaciente.setText(nombre);

        reservar = (Button) findViewById(R.id.btn_reservar);
        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetDoctores().execute("0");
                new GetEspecialidades().execute("0");
            }
        });

    }

    public class GetEspecialidades extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg) {

            String myemail = arg[0];
            DAOEspecialidad daoEspecialidad = new DAOEspecialidad(InicioPaciente.this);

            try {
                URL url = new URL(  "https://6sa9kuuzwf.execute-api.us-east-1.amazonaws.com/test");

                /*JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", myemail );
                Log.e("params",postDataParams.toString());*/

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                //conn.setDoOutput(true);

                int responseCode=conn.getResponseCode();
                Log.e("code" , Integer.toHexString(responseCode) );

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    InputStream inputStream = null;
                    BufferedReader reader = null;

                    inputStream = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    while ((line=reader.readLine()) !=null){buffer.append(line);}

                    reader.close();

                    JSONObject resultado = new JSONObject(buffer.toString());
                    JSONArray dataArray = resultado.getJSONArray("Resultado");

                    Log.e("length" , String.valueOf(dataArray.length()));


                    for(int i = 0 ; i < dataArray.length(); i++){
                        JSONObject data = dataArray.getJSONObject(i);
                        String noEspecialidad = data.getString("nombre");

                        Log.e("especialidad" , noEspecialidad);

                        daoEspecialidad.abrirDB();
                        Especialidad especialidad = new Especialidad(noEspecialidad);
                        daoEspecialidad.registrarEspecialidad(especialidad);
                    }

                    Intent intent = new Intent(InicioPaciente.this, ReservarCita.class);
                    //intent.putExtra("Especialidades",data.toString());
                    startActivity(intent);

                    return "Especialidades cargadas correctamente";

                }else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }
    }

    public class GetDoctores extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg) {

            String myemail = arg[0];
            DAODoctor daoDoctor = new DAODoctor(InicioPaciente.this);

            try {
                URL url = new URL(  "https://zr6oq80n9a.execute-api.us-east-1.amazonaws.com/test");

                /*JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", myemail );
                Log.e("params",postDataParams.toString());*/

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                //conn.setDoOutput(true);

                int responseCode=conn.getResponseCode();
                Log.e("code" , Integer.toHexString(responseCode) );

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    InputStream inputStream = null;
                    BufferedReader reader = null;

                    inputStream = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    while ((line=reader.readLine()) !=null){buffer.append(line);}

                    reader.close();

                    JSONObject resultado = new JSONObject(buffer.toString());
                    JSONArray dataArray = resultado.getJSONArray("Resultado");

                    Log.e("length" , String.valueOf(dataArray.length()));


                    for(int i = 0 ; i < dataArray.length(); i++){
                        JSONObject data = dataArray.getJSONObject(i);
                        String noDoctor = data.getString("name");
                        String apePatDoctor = data.getString("firstLastName");
                        String apeMatDoctor = data.getString("secondLastName");
                        String cellphone = data.getString("cellphone");

                        Log.e("especialidad" , noDoctor+' '+apePatDoctor+' '+apeMatDoctor);

                        daoDoctor.abrirDB();
                        Doctor doctor = new Doctor(noDoctor,apePatDoctor,apeMatDoctor,cellphone);
                        daoDoctor.registrarDoctor(doctor);
                    }

                    //Intent intent = new Intent(InicioPaciente.this, ReservarCita.class);
                    //intent.putExtra("Especialidades",data.toString());
                    //startActivity(intent);

                    return "Doctores cargados correctamente";

                }else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }
    }

}
