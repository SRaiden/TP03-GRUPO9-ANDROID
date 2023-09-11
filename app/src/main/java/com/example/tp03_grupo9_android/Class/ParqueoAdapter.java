package com.example.tp03_grupo9_android.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp03_grupo9_android.R;

import java.util.List;

public class ParqueoAdapter extends BaseAdapter {
    private Context context;
    private List<ParqueoData> parqueos;

    public ParqueoAdapter(Context context, List<ParqueoData> parqueos) {
        this.context = context;
        this.parqueos = parqueos;
    }

    @Override
    public int getCount() {
        return parqueos.size();
    }

    @Override
    public Object getItem(int position) {
        return parqueos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_parqueo_adapter, parent, false);
        }

        TextView numeroParqueoTextView = convertView.findViewById(R.id.numeroParqueoTextView);
        TextView tiempoTextView = convertView.findViewById(R.id.tiempoTextView);

        ParqueoData parqueo = parqueos.get(position);
        numeroParqueoTextView.setText("N° de Matricula: " + parqueo.getNumeroParqueo());
        tiempoTextView.setText("Tiempo: " +parqueo.getTiempo());

        return convertView;
    }
}
