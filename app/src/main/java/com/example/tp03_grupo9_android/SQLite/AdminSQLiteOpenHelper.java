package com.example.tp03_grupo9_android.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD_Automovil) {
        BD_Automovil.execSQL("create table User(Id int primary key, Nombre text, Email text, Password text)");
        BD_Automovil.execSQL("create table Automovil(IdAutomovil int primary key, IdUser int, NumMatricula text unique, Tiempo time)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}