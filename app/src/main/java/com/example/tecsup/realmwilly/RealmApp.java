package com.example.tecsup.realmwilly;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmApp extends Application {

    public static AtomicInteger cod_libro = new AtomicInteger();
    public static AtomicInteger cod_ejemplar = new AtomicInteger();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("inicio", "actividad");
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
        cod_libro = getIdByTable(realm, Libros.class);
        cod_ejemplar = getIdByTable(realm, Ejemplar.class);
        realm.close();

    }
    <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T>object){
        RealmResults<T> results = realm.where(object).findAll();

        if(results.size()>0){
            return new AtomicInteger(results.max("id").intValue());
        }else{
            return new AtomicInteger();
        }
    }


}
