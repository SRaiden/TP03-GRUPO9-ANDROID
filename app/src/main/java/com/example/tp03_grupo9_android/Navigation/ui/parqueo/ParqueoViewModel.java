package com.example.tp03_grupo9_android.Navigation.ui.parqueo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParqueoViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ParqueoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Parqueo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}