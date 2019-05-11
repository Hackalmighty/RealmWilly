package com.example.tecsup.realmwilly;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import io.realm.RealmResults;

public class LIbrosAdapter extends BaseAdapter {

    Context context;
    int layout;
    RealmResults <Libros> libros;

    public LIbrosAdapter(Context context, int layout, RealmResults<Libros> libros) {
        this.context = context;
        this.layout = layout;
        this.libros = libros;
    }

    @Override
    public int getCount() {
        return libros.size();
    }

    @Override
    public Object getItem(int position) {
        return libros.get(position);
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
        ImageButton b = v.findViewById(R.id.mostrar_menu);

       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               MainActivityRealm m = (MainActivityRealm)context;
               m.MostrarMenuLibro();
           }
       });

        titulo.setText(libros.get(position).getNombre());
        autor.setText(libros.get(position).getAutor());
        return v;
    }
}
