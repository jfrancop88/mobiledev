package com.clinica.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Clinica_Cruz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica__cruz);
        cargarFragmento(new HomeFragment());
        BottomNavigationView ClinicaView = findViewById(R.id.BottonNavigationViewClinica);
        ClinicaView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        cargarFragmento(new HomeFragment());
                        break;
                    case R.id.registerFragment:
                        cargarFragmento(new RegisterFragment());
                        break;
                    case R.id.reserveFragment:
                        cargarFragmento(new ReserveFragment());
                        break;
                   // case R.id.sedeFragment:
                     //   cargarFragmento(new SedeFragment());
                       // break;
                }
                return false;
            }
        });
    }
    private void cargarFragmento (Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerClinica, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}