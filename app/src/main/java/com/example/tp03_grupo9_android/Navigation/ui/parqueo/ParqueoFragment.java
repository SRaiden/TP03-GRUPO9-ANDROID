package com.example.tp03_grupo9_android.Navigation.ui.parqueo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tp03_grupo9_android.Navigation.ui.cuenta.CuentaViewModel;
import com.example.tp03_grupo9_android.R;
import com.example.tp03_grupo9_android.databinding.FragmentCuentaBinding;
import com.example.tp03_grupo9_android.databinding.FragmentParqueoBinding;

public class ParqueoFragment extends Fragment {

    private FragmentParqueoBinding binding;

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

}