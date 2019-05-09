package com.example.tecsup.realmwilly;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Ejemplar extends RealmObject {
    @PrimaryKey
    int id;
    @Required
    String estado;
    @Required
    String codigo;

    String observacion;


    public Ejemplar(){
    }

    public Ejemplar(String estado, String codigo, String observacion) {
        this.id = RealmApp.cod_ejemplar.incrementAndGet();
        this.estado = estado;
        this.codigo = codigo;
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
