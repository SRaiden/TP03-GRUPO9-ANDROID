package com.example.tp03_grupo9_android.Navigation.ui.CerrarSesion;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp03_grupo9_android.MainActivity;
import com.example.tp03_grupo9_android.Navigation.ui.CerrarSesion.CerrarSesionViewModel;
import com.example.tp03_grupo9_android.R;
import com.example.tp03_grupo9_android.databinding.FragmentCerrarSesionBinding;

public class CerrarSesion extends Fragment {

    private FragmentCerrarSesionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCerrarSesionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // CerrarSesion
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}