package com.example.tp03_grupo9_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp03_grupo9_android.SQLite.AdminSQLiteOpenHelper;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText txtNombre, txtEmail, txtPassword, txtRepetirPassword;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        declaraciones();
        setClickListener();
    }

    private void declaraciones(){
        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtContraseña);
        txtRepetirPassword = findViewById(R.id.txtRepetirContraseña);

        btnAceptar = findViewById(R.id.btnAceptar);
    }

    private void setClickListener(){
        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // GUARDAR INFO
                String nombre = txtNombre.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String repetir = txtRepetirPassword.getText().toString();

                // VALIDAR
                boolean validar = validaciones(nombre, email, password, repetir);
                if(!validar){
                    return;
                }

                // LLAMADA
                AdminSQLiteOpenHelper sqlite = new AdminSQLiteOpenHelper(RegistrarseActivity.this, "DB_Automovil", null, 1);
                SQLiteDatabase bd = sqlite.getWritableDatabase();

                // OBTENER ULTIMO ID DE LA TABLA USER -------------
                Integer Id;
                Cursor cursor = bd.rawQuery("SELECT * FROM User ORDER BY Id DESC LIMIT 1", null);
                if (cursor.moveToFirst()) {
                    Id = cursor.getColumnIndex("Id");
                    Id = Id + 1;
                } else {
                    Id = 1;
                }

                // GUARDAR EN BASE DE DATOS (DB_Automovil) - TABLA USUARIOS
                ContentValues registro = new ContentValues();
                registro.put("Id", Id); // modificar luego
                registro.put("Nombre", nombre);
                registro.put("Email", email);
                registro.put("Password", password);
                bd.insert("User", null, registro); // insertamos registros en la tabla User
                bd.close();

                // Finalizar
                Toast.makeText(RegistrarseActivity.this, "Nuevo usuario registrado", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegistrarseActivity.this, MainActivity.class));
            }

            private boolean validaciones(String nombre, String email, String password, String repetir){
                if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || repetir.isEmpty()) {
                    Toast.makeText(RegistrarseActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_LONG).show();
                    return false;
                }else if (!isValidEmail(email)) {
                    Toast.makeText(RegistrarseActivity.this, "Por favor, ingrese un email válido.", Toast.LENGTH_LONG).show();
                    return false;
                }else if(!password.equals(repetir)){
                    Toast.makeText(RegistrarseActivity.this, "Ambas contraseñas deben de coincidir.", Toast.LENGTH_LONG).show();
                    return false;
                }else{
                    return true;
                }
            }

            private boolean isValidEmail(String email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            }

        });


    }
}