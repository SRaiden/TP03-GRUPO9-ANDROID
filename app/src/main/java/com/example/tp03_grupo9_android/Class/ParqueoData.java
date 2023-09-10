package com.example.tp03_grupo9_android.Class;

public class ParqueoData {
    private String numeroParqueo;
    private String tiempo;

    public ParqueoData(String numeroParqueo, String tiempo) {
        this.numeroParqueo = numeroParqueo;
        this.tiempo = tiempo;
    }

    public String getNumeroParqueo() {
        return numeroParqueo;
    }

    public String getTiempo() {
        return tiempo;
    }
}