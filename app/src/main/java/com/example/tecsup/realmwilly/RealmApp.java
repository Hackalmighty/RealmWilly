package com.example.tecsup.realmwilly;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmApp extends Application {

    public static AtomicInteger cod_libro = new AtomicInteger();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("inicio", "actividad");
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        cod_libro = getIdByTable(realm, Libros.class);
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
