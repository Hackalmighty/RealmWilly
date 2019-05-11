package com.example.tecsup.realmwilly;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class activity_ejemplar extends AppCompatActivity {
    Realm realm; //se necesita crear un objeto realm
    ListView lv;
    FloatingActionButton fab;
    Libros libro;
    EjemplarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplar);
        lv = findViewById(R.id.lv_libros);
        fab = findViewById(R.id.fab_boton);
        realm = Realm.getDefaultInstance();

        //int libro_id = 1;

        Intent intent = getIntent();
        // Saco el extra llamano message
        Bundle bb = intent.getExtras();
        Integer id_obtenido = bb.getInt("id_libro");
        int libro_id = id_obtenido;

        libro = realm.where(Libros.class).
                equalTo("id", libro_id).findFirst();
        Log.d("id" , libro_id+"");
        //LLamar a listview y adaptador
        adapter = new EjemplarAdapter(this,
                R.layout.item_libro, libro.getEjemplares());
        EjemplarAdapter adapter = new EjemplarAdapter(this, R.layout.item_libro, libro.getEjemplares());

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
        builder.setTitle("Nuevo Ejemplar");
        builder.setMessage("Ingrese los datos para un nuevo ejemplar");
        LayoutInflater inflater = LayoutInflater.from(this);
        //inflando la vista
        View v = inflater.inflate(R.layout.agregar_ejemplar, null);
        //Creo los textview
        final TextView estado = v.findViewById(R.id.estado);
        final TextView codigo = v.findViewById(R.id.codigo);
        final TextView observacion = v.findViewById(R.id.observacion);
        //asigno el textview a mi bbuilder
        builder.setView(v);
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Ejemplar l =new Ejemplar(estado.getText().toString(),
                        codigo.getText().toString(), observacion.getText().toString());
                realm.beginTransaction();
                libro.getEjemplares().add(l);
                realm.commitTransaction();
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }
}
