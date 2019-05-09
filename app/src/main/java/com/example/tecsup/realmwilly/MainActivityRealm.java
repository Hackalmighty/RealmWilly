package com.example.tecsup.realmwilly;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivityRealm extends AppCompatActivity {
    Realm realm; //se necesita crear un objeto realm
    ListView lv;
    LIbrosAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realm);
        lv = findViewById(R.id.lv_libros);
        fab = findViewById(R.id.fab_boton);
        realm = Realm.getDefaultInstance(); //para instanciar, es que se declaro, pero no se instanció
        Log.d("inicio", "actividad");

        //Listando libros
        RealmResults<Libros> libros = realm.where(Libros.class).findAll();
        adapter = new LIbrosAdapter (this, R.layout.item_libro,libros);
        lv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLibroDialog();
            }
        });
    }



    void AddLibroDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo Libro");
        builder.setMessage("Ingrese los datos para un nuevo libro");
        LayoutInflater inflater = LayoutInflater.from(this);
        //inflando la vista
        View v = inflater.inflate(R.layout.agregar_libro, null);
        //Creo los textview
        final TextView titulo = v.findViewById(R.id.titulo);
        final TextView autor = v.findViewById(R.id.autor);
        builder.setView(v);
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Libros l =new Libros(titulo.getText().toString(), autor.getText().toString());
                realm.beginTransaction();
                realm.copyToRealm(l);
                realm.commitTransaction();
            }
        });
        builder.show();
    }
}
