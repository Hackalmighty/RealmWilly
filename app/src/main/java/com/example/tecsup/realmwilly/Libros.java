package com.example.tecsup.realmwilly;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Libros extends RealmObject {
    @PrimaryKey
    int id;
    @Required
    String nombre;
    @Required
    String autor;
    RealmList<Ejemplar> ejemplares;

    public Libros() {
        this.ejemplares = new RealmList<>();

    }

    public Libros(String nombre, String autor) {
        this.id = RealmApp.cod_libro.incrementAndGet();
        this.nombre = nombre;
        this.autor = autor;
        this.ejemplares = new RealmList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
