package com.example.tecsup.realmwilly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.realm.RealmList;
import io.realm.RealmResults;

public class EjemplarAdapter extends BaseAdapter {

    Context context;
    int layout;
    RealmList<Ejemplar> ejemplares;

    public EjemplarAdapter(Context context, int layout, RealmList<Ejemplar> ejemplares) {
        this.context = context;
        this.layout = layout;
        this.ejemplares = ejemplares;
    }

    @Override
    public int getCount() {
        return ejemplares.size();
    }

    @Override
    public Object getItem(int position) {
        return ejemplares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater l = LayoutInflater.from(context);
        View v = l.inflate(layout, null);
        TextView titulo = v.findViewById(R.id.tv_libro);
        TextView autor = v.findViewById(R.id.tv_autor);

        titulo.setText(ejemplares.get(position).getCodigo());
        autor.setText(ejemplares.get(position).getEstado());
        return v;
    }
}
