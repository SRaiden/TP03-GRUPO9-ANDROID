package com.example.tp03_grupo9_android.Navigation.ui.parqueo;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp03_grupo9_android.Class.ParqueoAdapter;
import com.example.tp03_grupo9_android.Class.ParqueoData;
import com.example.tp03_grupo9_android.Class.UsuarioGlobal;
import com.example.tp03_grupo9_android.R;
import com.example.tp03_grupo9_android.SQLite.AdminSQLiteOpenHelper;
import com.example.tp03_grupo9_android.databinding.FragmentParqueoBinding;

import java.util.ArrayList;
import java.util.List;

public class ParqueoFragment extends Fragment {

    private FragmentParqueoBinding binding;
    GridView grillaParqueo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParqueoViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ParqueoViewModel.class);

        binding = FragmentParqueoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        grilla();

        // Configurar OnClickListener para el botón agregarParqueo
        binding.agregarParqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el diálogo personalizado
                showDialog();
            }
        });
    }

    private void grilla(){
        // INVOCACION
        final GridView grillaParqueo = binding.grillaParqueo;

        Context contexto = requireContext();

        // LLAMADA
        AdminSQLiteOpenHelper sqlite = new AdminSQLiteOpenHelper(contexto, "DB_Automovil", null, 1);
        SQLiteDatabase bd = sqlite.getWritableDatabase();

        // CONSULTA
        String consulta = "SELECT NumMatricula, Tiempo FROM Automovil WHERE IdUser = ? ORDER BY IdAutomovil";
        Cursor cursor = bd.rawQuery(consulta, new String[]{String.valueOf(UsuarioGlobal.Id)});

            // PONER CADA PARQUEO EN UNA LISTA
        List<ParqueoData> parqueos = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String numeroParqueo = cursor.getString(0);
                String tiempo = cursor.getString(1);
                ParqueoData parqueoData = new ParqueoData(numeroParqueo, tiempo);
                parqueos.add(parqueoData);
            } while (cursor.moveToNext());
        }

        // Crear el adaptador personalizado y configurarlo con la lista de parqueos
        ParqueoAdapter adapter = new ParqueoAdapter(requireContext(), parqueos);
        grillaParqueo.setAdapter(adapter);
    }


    private void showDialog() {
        AgregarParqueoFragment dialogFragment = new AgregarParqueoFragment();
        dialogFragment.show(getChildFragmentManager(), "MyDialogFragment");
    }


}