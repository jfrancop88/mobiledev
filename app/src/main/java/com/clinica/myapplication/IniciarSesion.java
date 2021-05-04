package com.clinica.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class IniciarSesion extends AppCompatActivity {

    Button login;
    EditText email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        email = findViewById(R.id.txt_usuario);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendPostRequest().execute(email.getText().toString().trim());

            }
        });
    }

    public void logueo(){

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String mail = email.getText().toString();

            String url = "https://2n39nri97l.execute-api.us-east-1.amazonaws.com/test";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", mail);
            final String requestBody = jsonBody.toString();

            // METODO COMO CADENA
            StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast toast = Toast.makeText(IniciarSesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    Log.e("respuesta: ", response);

                    startActivity(new Intent(IniciarSesion.this, InicioPaciente.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(IniciarSesion.this, error.toString(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            })
                {
                @Override
                public String getBodyContentType(){
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(peticion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void logueo2() {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String mail = email.getText().toString();

            String url = "https://2n39nri97l.execute-api.us-east-1.amazonaws.com/test";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", mail);
            final String requestBody = jsonBody.toString();

            //METODO COMO JSONOBJECT
            JsonObjectRequest peticionJson = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray ja = response.getJSONArray("Resultado");
                        Log.e("mensaje: ", ja.getJSONObject(0).toString());
                        startActivity(new Intent(IniciarSesion.this, InicioPaciente.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley", "Error");

                }
            });
            requestQueue.add(peticionJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg) {

            String myemail = arg[0];

            try {
                URL url = new URL(  "https://2n39nri97l.execute-api.us-east-1.amazonaws.com/test");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", myemail );
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                //Mando la información del POST
                OutputStream os = conn.getOutputStream();
                byte[] input = postDataParams.toString().getBytes("utf-8");
                os.write(input, 0, input.length);


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

                    //Log.e("data", buffer.toString());
                    reader.close();

                    JSONObject resultado = new JSONObject(buffer.toString());
                    JSONArray dataArray = resultado.getJSONArray("Resultado");
                    Log.e("data", dataArray.toString());

                    JSONObject name = dataArray.getJSONObject(0);
                    String nombre = name.getString("name");
                    String apepat = name.getString("firstLastName");
                    String apemat = name.getString("secondLastName");

                    Log.e("nombre", nombre.toString() +' '+ apepat.toString() +' '+ apemat.toString());


                    Intent intent = new Intent(IniciarSesion.this, InicioPaciente.class);
                    intent.putExtra("Nombre", nombre.toString()+ ' '+ apepat.toString()+' '+ apemat.toString());
                    startActivity(intent);

                    return  new String("Bienvenido " + dataArray.getJSONObject(0).getString("name") +"\nInicio de Sesión exitoso");

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

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


}
