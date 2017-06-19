package com.duke.realm_test.view;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.lang.reflect.Field;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @DateTime: 2016-07-12 11:23
 * @Author: duke
 * @Deacription:
 */
public class MyApplication extends Application {
    private String realmName = "dk.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);//Realm数据库初始化
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .name(realmName)
//                //.assetFile(this,"realm file path in assets，will copy this file to Context.getFilesDir() replace an empty realm file")
//                .build();
//        Realm.setDefaultConfiguration(realmConfig);
//        Log.i("yunli","realmConfig = " + Realm.get.toString());
        Class clazz = Realm.class;
        try {
            Field field = clazz.getDeclaredField("defaultConfiguration");
            field.setAccessible(true);
            RealmConfiguration configuration = (RealmConfiguration)field.get(clazz);
            Log.i("yunli","config = " + configuration.toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Stetho.initialize(//Stetho初始化
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build()
        );
    }
}
