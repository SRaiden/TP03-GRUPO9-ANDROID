package com.example.tp03_grupo9_android.Navigation.ui.parqueo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp03_grupo9_android.Class.UsuarioGlobal;
import com.example.tp03_grupo9_android.MainActivity;
import com.example.tp03_grupo9_android.R;
import com.example.tp03_grupo9_android.RegistrarseActivity;
import com.example.tp03_grupo9_android.SQLite.AdminSQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarParqueoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarParqueoFragment extends DialogFragment {

    private OnParqueoAddedListener parqueoAddedListener;
    TextView btnRegistrarse, btnCancelar;
    EditText txtNumParqueo, txtTiempo;

    public static AgregarParqueoFragment newInstance(String param1, String param2) {
        AgregarParqueoFragment fragment = new AgregarParqueoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            parqueoAddedListener = (OnParqueoAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar OnParqueoAddedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_parqueo, container, false);

        // Inicializa los botones
        btnRegistrarse = view.findViewById(R.id.btnRegistrar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        // Inicializa EDITTEXT
        txtNumParqueo = view.findViewById(R.id.txtNumMatricula);
        txtTiempo = view.findViewById(R.id.txtTiempo);

        eventoBotones();

        return view;
    }

    private void eventoBotones() {
        btnCancelar.setOnClickListener(view -> {
            dismiss();
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GUARDAR INFO EN VARIABLES
                String numParqueo = txtNumParqueo.getText().toString();
                String tiempo = txtTiempo.getText().toString();

                //VALIDAR QUE INGRESO BIEN TODO
                boolean validar = validaciones(numParqueo, tiempo);
                if (!validar) {
                    return;
                }

                // INICIALIZAR LA BBDD
                AdminSQLiteOpenHelper sqlite = new AdminSQLiteOpenHelper(getActivity(), "DB_Automovil", null, 1);
                SQLiteDatabase bd = sqlite.getWritableDatabase();

                // OBTENER ULTIMO ID DE LA TABLA PARQUEO
                int ultimoID = 0;
                String consulta = "SELECT IdAutomovil FROM Automovil ORDER BY IdAutomovil DESC LIMIT 1";
                Cursor cursor = bd.rawQuery(consulta, null);

                if (cursor.moveToFirst()) {
                    ultimoID = cursor.getInt(0);
                }
                ultimoID++;

                // INGRESAR A LA TABLA AUTOMOVIL
                ContentValues registro = new ContentValues();
                registro.put("IdAutomovil", ultimoID);
                registro.put("IdUser", UsuarioGlobal.Id);
                registro.put("NumMatricula", numParqueo);
                registro.put("Tiempo", tiempo);
                bd.insert("Automovil", null, registro);
                bd.close();

                // RECARGAR LA PAGINA DE PARQUEOFRAGMENT
                if (parqueoAddedListener != null) {
                    parqueoAddedListener.onParqueoAdded();
                }

                // MOSTRAR CARTEL DE CORRECTO
                Toast.makeText(getActivity(), "Nuevo parqueo registrado", Toast.LENGTH_LONG).show();
                dismiss();
            }

            private boolean validaciones(String numParqueo, String tiempo) {
                // Validar el número de parqueo
                if (!isValidNumParqueo(numParqueo)) {
                    Toast.makeText(getActivity(), "Número de parqueo no válido", Toast.LENGTH_LONG).show();
                    txtNumParqueo.requestFocus();
                    return false;
                }

                // Validar el formato del tiempo (HH:mm)
                if (!isValidTiempo(tiempo)) {
                    Toast.makeText(getActivity(), "Formato de tiempo no válido (HH:mm)", Toast.LENGTH_LONG).show();
                    txtTiempo.requestFocus();
                    return false;
                }

                return true;
            }

            private boolean isValidNumParqueo(String numParqueo) {
                return !numParqueo.isEmpty();
            }

            private boolean isValidTiempo(String tiempo) {
                String pattern = "HH:mm";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.setLenient(false);

                try {
                    sdf.parse(tiempo);
                    return true;
                } catch (ParseException e) {
                    return false;
                }
            }

        });
    }

    public interface OnParqueoAddedListener {
        void onParqueoAdded();
    }
}