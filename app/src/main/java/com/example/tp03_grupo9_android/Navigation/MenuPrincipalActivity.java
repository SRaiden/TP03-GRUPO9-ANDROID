package com.example.tp03_grupo9_android.Navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tp03_grupo9_android.MainActivity;
import com.example.tp03_grupo9_android.R;
import com.google.android.material.navigation.NavigationView;

public class MenuPrincipalActivity extends AppCompatActivity {

    Menu menuPrincipal;
    MenuItem navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Aquí puedes iniciar InicioActivity
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);

        // Puedes finalizar ContenidoMenuActivity si no la necesitas más
        finish();
    }

}