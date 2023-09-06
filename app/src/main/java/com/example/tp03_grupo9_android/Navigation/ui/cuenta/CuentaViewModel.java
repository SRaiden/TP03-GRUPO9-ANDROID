package com.example.tp03_grupo9_android.Navigation.ui.cuenta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp03_grupo9_android.Class.UsuarioGlobal;
import com.example.tp03_grupo9_android.SQLite.AdminSQLiteOpenHelper;

public class CuentaViewModel extends ViewModel {
    private final MutableLiveData<String> mTextEmail;
    private final MutableLiveData<String> mTextUser;



    public CuentaViewModel() {
        mTextEmail = new MutableLiveData<>();
        mTextUser = new MutableLiveData<>();

    }

    public LiveData<String> getTextEmail() {
        return mTextEmail;
    }
    public LiveData<String> getTextUser() {
        return mTextUser;
    }
}