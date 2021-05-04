package com.clinica.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clinica.myapplication.entidades.Paciente;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.miVista> {
    private Context context;
    private ArrayList<Paciente> listapaciente = new ArrayList<>();
    private Activity activity;

    public CustomAdapter (Activity activity, Context context, ArrayList<Paciente> listapaciente ){
        this.context = context;
        this.listapaciente = listapaciente;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomAdapter.miVista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nueva_fila, parent, false);
        return new miVista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.miVista holder, int position) {
            holder.paciente_dni.setText(String.valueOf(listapaciente.get(position).getNumdocumenot()));
            holder.paciente_nombre.setText(String.valueOf(listapaciente.get(position).getNombre()));
            holder.paciente_paterno.setText(String.valueOf(listapaciente.get(position).getAppaterno()));
            holder.paciente_materno.setText(String.valueOf(listapaciente.get(position).getApmaterno()));
            holder.paciente_sexo.setText(String.valueOf(listapaciente.get(position).getSexo()));
            holder.paciente_direccion.setText(String.valueOf(listapaciente.get(position).getDireccion()));
            holder.paciente_correo.setText(String.valueOf(listapaciente.get(position).getCorreo()));
            holder.filaLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ActualizarPaciente.class);
                    intent.putExtra("id",String.valueOf(listapaciente.get(position).getId()));
                    intent.putExtra("documento",String.valueOf(listapaciente.get(position).getDocumento()));
                    intent.putExtra("numdocumento", String.valueOf(listapaciente.get(position).getNumdocumenot()));
                    intent.putExtra("nombre",String.valueOf(listapaciente.get(position).getNombre()));
                    intent.putExtra("paterno", String.valueOf(listapaciente.get(position).getAppaterno()));
                    intent.putExtra("materno", String.valueOf(listapaciente.get(position).getApmaterno()));
                    intent.putExtra("sexo", String.valueOf(listapaciente.get(position).getSexo()));
                    intent.putExtra("correo", String.valueOf(listapaciente.get(position).getCorreo()));
                    intent.putExtra("contrasena", String.valueOf(listapaciente.get(position).getContrasena()));
                    intent.putExtra("direccion", String.valueOf(listapaciente.get(position).getDireccion()));

                   //context.startActivity(intent);
                    activity.startActivityForResult(intent, 1);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listapaciente.size();
    }

    public class miVista extends RecyclerView.ViewHolder {
        TextView paciente_dni, paciente_nombre, paciente_paterno, paciente_materno, paciente_sexo, paciente_direccion, paciente_correo;
        LinearLayout filaLayout;

        public miVista(@NonNull View itemView) {
            super(itemView);
            paciente_dni = itemView.findViewById(R.id.paciente_dni);
            paciente_nombre = itemView.findViewById(R.id.paciente_nombre);
            paciente_paterno = itemView.findViewById(R.id.paciente_paterno);
            paciente_materno = itemView.findViewById(R.id.paciente_materno);
            paciente_sexo = itemView.findViewById(R.id.paciente_sexo);
            paciente_direccion = itemView.findViewById(R.id.paciente_direccion);
            paciente_correo = itemView.findViewById(R.id.paciente_correo);
            filaLayout = itemView.findViewById(R.id.filaLayout);
        }
    }
}
