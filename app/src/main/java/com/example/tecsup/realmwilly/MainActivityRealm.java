package com.example.tecsup.realmwilly;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivityRealm extends AppCompatActivity {
    Realm realm; //se necesita crear un objeto realm
    ListView lv;
    LIbrosAdapter adapter;
    FloatingActionButton fab;
    RealmResults<Libros> libros;
    AlertDialog dialogo_opciones;

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realm);
        lv = findViewById(R.id.lv_libros);
        fab = findViewById(R.id.fab_boton);
        realm = Realm.getDefaultInstance(); //para instanciar, es que se declaro, pero no se instanció
        Log.d("inicio", "actividad");

        //Listando libros
        libros = realm.where(Libros.class).findAll();
        adapter = new LIbrosAdapter (this, R.layout.item_libro,libros);
        lv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLibroDialog();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), activity_ejemplar.class);
                // a nuestro intent le agregamos un mensaje sacado de nuestro edittext
                Toast.makeText(MainActivityRealm.this, position + "", Toast.LENGTH_SHORT).show();
                intent.putExtra("id_libro", libros.get(position).getId());
                //startActivity(intent);
                startActivityForResult(intent,TEXT_REQUEST);

            }
        });

    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_libros, menu);
    }

    public void MostrarMenuLibro(final int posicion){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones");
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.menu_libros, null);
        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);
        builder.setView(v);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libros l = libros.get(posicion);
                realm.beginTransaction();
                l.deleteFromRealm();
                realm.commitTransaction();
                adapter.notifyDataSetChanged();
                dialogo_opciones.dismiss();
            }
        });

        builder.setView(v);
        dialogo_opciones = builder.create();
        dialogo_opciones.show();
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
        //asigno el textview a mi bbuilder
        builder.setView(v);
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Libros l =new Libros(titulo.getText().toString(), autor.getText().toString());
                realm.beginTransaction();
                realm.copyToRealm(l);
                realm.commitTransaction();
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }
}
