package com.duke.realm_test.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * @DateTime: 2016-07-12 17:06
 * @Author: duke
 * @Deacription: 集成RealmObject，类中可以定义的类型：
 * boolean, byte, short, ìnt, long, float, double, String,
 * Date，byte[]. 以及RealmObject和RealmList
 */
@RealmClass
public class TestUser extends RealmObject {

    @PrimaryKey
    private int userId;//id,主键
    @Required
    private String userName;//用户姓名,必填字段
    private String userPwd;//密码
    private int userAge;//年龄
    private String userAddress;//住址
    private String userWork;//工作
    private String userSex;//性别

    //private RealmList<E> list;  集合


    public TestUser() {
    }

    public TestUser(int userId, String userName, String userPwd, int userAge,
                    String userAddress, String userWork, String userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userAge = userAge;
        this.userAddress = userAddress;
        this.userWork = userWork;
        this.userSex = userSex;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserWork() {
        return userWork;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserWork(String userWork) {
        this.userWork = userWork;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}