package com.example.tp03_grupo9_android;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;

import com.example.tp03_grupo9_android.Class.UsuarioGlobal;
import com.example.tp03_grupo9_android.SQLite.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView btnRegistrarse, txt_user, txt_password;
    Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Declaracion();
        ClickListener();
    }

    private void Declaracion() {
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        txt_user = findViewById(R.id.txtUser);
        txt_password = findViewById(R.id.txtContraseña);

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
    }

    private void ClickListener(){
        btnRegistrarse.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RegistrarseActivity.class));
        });

        btnIniciarSesion.setOnClickListener(view -> {
            // GUARDAR INFO
            String nombre = txt_user.getText().toString();
            String password = txt_password.getText().toString();

            // LLAMADA
            AdminSQLiteOpenHelper sqlite = new AdminSQLiteOpenHelper(MainActivity.this, "DB_Automovil", null, 1);
            SQLiteDatabase bd = sqlite.getWritableDatabase();

            // VERIFICAR SI EXISTE UN USUARIO Y CONTRASEÑA EN LA BBDD
            String consulta = "SELECT * FROM User WHERE Nombre = ? AND Password = ?";
            Cursor cursor = bd.rawQuery(consulta, new String[]{nombre, password});

            if (cursor.moveToFirst()) {
                // GUARDAR EL ID DEL USUARIO EN UNA VARIABLE GLOBAL
                Integer idUser = cursor.getInt(0);
                UsuarioGlobal.Id = idUser;

                // IR AL MENU PRINCIPAL, EL MENU PRINCIPAL DENTRO DE LA CARPETA NAVIGATION
                startActivity(new Intent(MainActivity.this, com.example.tp03_grupo9_android.Navigation.MenuPrincipalActivity.class));
            } else {
                // MENSAJE DE USUARIO Y CONTRASEÑA INCORRECTO
                Toast.makeText(MainActivity.this, "Usuario y Contraseña INCORRECTO", Toast.LENGTH_LONG).show();
            }
        });
    }
}