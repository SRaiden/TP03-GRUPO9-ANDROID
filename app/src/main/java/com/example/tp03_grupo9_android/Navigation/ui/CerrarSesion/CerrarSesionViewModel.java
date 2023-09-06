package com.example.tp03_grupo9_android.Navigation.ui.CerrarSesion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CerrarSesionViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CerrarSesionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is CerrarSesion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}