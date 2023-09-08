package com.example.tp03_grupo9_android.Navigation.ui.cuenta;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp03_grupo9_android.Class.UsuarioGlobal;
import com.example.tp03_grupo9_android.MainActivity;
import com.example.tp03_grupo9_android.SQLite.AdminSQLiteOpenHelper;
import com.example.tp03_grupo9_android.databinding.FragmentCuentaBinding;

public class CuentaFragment extends Fragment {

    private FragmentCuentaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CuentaViewModel slideshowViewModel =
                new ViewModelProvider(this).get(CuentaViewModel.class);

        binding = FragmentCuentaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        obtenerUser();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void obtenerUser(){
        final TextView textEmail = binding.txtEmail;
        final TextView textUser = binding.txtNombre;

        // Asegura que los TextViews sean de solo lectura
        textEmail.setFocusable(false);
        textEmail.setFocusableInTouchMode(false);
        textUser.setFocusable(false);
        textUser.setFocusableInTouchMode(false);

        Context contexto = requireContext();

        // LLAMADA
        AdminSQLiteOpenHelper sqlite = new AdminSQLiteOpenHelper(contexto, "DB_Automovil", null, 1);
        SQLiteDatabase bd = sqlite.getWritableDatabase();

        // VERIFICAR SI EXISTE UN USUARIO Y CONTRASEÑA EN LA BBDD
        String consulta = "SELECT * FROM User WHERE Id = ?";
        String a = String.valueOf(UsuarioGlobal.Id);
        Cursor cursor = bd.rawQuery(consulta, new String[]{String.valueOf(UsuarioGlobal.Id)});

        if (cursor.moveToFirst()) {
            String email = cursor.getString(2);
            String User =cursor.getString(1);

            // Actualiza las vistas con los valores de la base de datos
            textEmail.setText(email);
            textUser.setText(User);
        }
    }
}