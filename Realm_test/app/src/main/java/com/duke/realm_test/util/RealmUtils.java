package com.duke.realm_test.util;

import com.duke.realm_test.annotation.AopAnnotationInterface;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Liyun on 2017/6/19.
 */

public class RealmUtils {
    private Realm mRealm;
    public RealmUtils(){
        mRealm = Realm.getDefaultInstance();
    }
    @AopAnnotationInterface("addRealmList")
    public boolean addRealmList(List<? extends RealmObject> list){

        boolean res = false;
        try {
            mRealm.copyToRealmOrUpdate(list);
            res = true;
        }catch (Exception e){
        }
        return res;
    }
    @AopAnnotationInterface("addRealmSingle")
    public boolean addRealmSingle(RealmObject object){
        boolean res = false;
        try {
            mRealm.copyToRealm(object);
            res = true;
        }catch (Exception e){
        }
        return res;
    }
    @AopAnnotationInterface("insertSingle")
    public boolean insertSingle(RealmObject object){
        boolean res = false;
        try{
            mRealm.insertOrUpdate(object);
            res = true;
        }catch (Exception e){

        }
        return res;
    }
    @AopAnnotationInterface("insertList")
    public boolean insertList(List<? extends RealmObject> list){
        boolean res = false;
        try{
            mRealm.insertOrUpdate(list);
            res = true;
        }catch (Exception e){

        }
        return res;
    }
    @AopAnnotationInterface("deleteAll")
    public boolean deleteAll(){
        boolean res = false;
        try{
            mRealm.deleteAll();
            res = true;
        }catch (Exception e){

        }
        return res;
    }
    public RealmObject findByName(Class<? extends RealmObject> clazz,String name){
        return mRealm.where(clazz).equalTo("userName",name).findFirst();
    }
    public RealmResults<? extends RealmObject> findAll(Class<? extends RealmObject> clazz){
        return mRealm.where(clazz).findAll();
    }
    @AopAnnotationInterface("deleteByName")
    public boolean deleteByName(Class<? extends RealmObject> clazz,String idFieldName,String name){
        boolean res = false;
        try{
            mRealm.where(clazz).equalTo(idFieldName, name).findAll().deleteFirstFromRealm();
            res = true;
        }catch (Exception e){

        }
        return res;
    }
}
