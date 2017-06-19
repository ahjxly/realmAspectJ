package com.duke.realm_test.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.duke.realm_test.R;
import com.duke.realm_test.bean.TestUser;
import com.duke.realm_test.util.RealmUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RealmChangeListener<Realm> {
    private Realm mRealm;

    private Button btnInit, btnAddOne, btnInsertOne,btnInsertlist, btnClear,btnUpdateOne,btnDelOne;
    private TextView show;
    private RealmUtils realmUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();
        realmUtils = new RealmUtils();
        btnInit = (Button) findViewById(R.id.btnInit);
        btnAddOne = (Button) findViewById(R.id.btnAddOne);
        btnInsertOne = (Button) findViewById(R.id.btnInsertOne);
        btnInsertlist = (Button)findViewById(R.id.btnInsertList);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnDelOne = (Button)findViewById(R.id.deleteOne);
        btnUpdateOne = (Button)findViewById(R.id.updateOne);
        btnInit.setOnClickListener(this);
        btnAddOne.setOnClickListener(this);
        btnInsertOne.setOnClickListener(this);
        btnInsertlist.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDelOne.setOnClickListener(this);
        btnUpdateOne.setOnClickListener(this);
        show = (TextView) findViewById(R.id.show);

        /**
         * 数据库数据更新监听
         */
        mRealm.addChangeListener(this);
    }

    @Override
    public void onChange(Realm element) {
        findAll();
    }

    @Override
    public void onClick(View v) {
        boolean bl = false;
        switch (v.getId()) {
            case R.id.btnInit:
                List<TestUser> list = new ArrayList<>();
                list.add(new TestUser(0,"android", "123123", 20, "河南常德", "传菜员", "女"));
                list.add(new TestUser(1,"angel", "13588889988", 21, "云南西双版纳", "飞行员", "男"));
                bl = realmUtils.addRealmList(list);
                toast(bl ? "添加list成功" : "添加list失败");
                break;
            case R.id.btnAddOne:
                TestUser user2 = new TestUser(4,"hibrid", "120250", 26, "赣州", "贼", "男");
                bl = realmUtils.addRealmSingle(user2);
                toast(bl ? "添加单条成功" : "添加单条失败,数据库id值已存在");
                break;
            case R.id.btnInsertOne://如果存在该记录，会更新该记录,比add会快
                TestUser user = new TestUser(6,"admin", "123456", 40, "湖北汉城", "程序员", "男");
                bl = realmUtils.insertSingle(user);
                toast(bl ? "插入单条成功" : "插入单条失败");
                break;
            case R.id.btnInsertList:
                List<TestUser> list1 = new ArrayList<>();
                list1.add(new TestUser(7,"android", "123123", 20, "河南常德", "传菜员", "女"));
                list1.add(new TestUser(8,"angel", "13588889988", 21, "云南西双版纳", "飞行员", "男"));
                list1.add(new TestUser(9,"adidass", "110119", 28, "云南德克萨斯州", "海员", "男"));
                bl = realmUtils.insertList(list1);
                toast(bl ? "插入list成功" : "插入list失败");
                break;
            case R.id.btnClear:
                bl = realmUtils.deleteAll();
                toast(bl ? "删除全部成功" : "删除全部失败");
                break;
            case R.id.updateOne:
                TestUser result = new TestUser(7,"android", "123123", 60, "河南常德", "传菜员", "女");
                realmUtils.insertSingle(result);
                break;
            case R.id.deleteOne:
                TestUser results1 = (TestUser) realmUtils.findByName(TestUser.class,"android");
                realmUtils.deleteByName(TestUser.class,"userName",results1.getUserName());
                break;
        }
    }

    public void findAll() {
        try {
            RealmResults<TestUser> results = (RealmResults<TestUser>) realmUtils.findAll(TestUser.class);
            int size = results.size();
            TestUser user;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                user = results.get(i);
                stringBuilder.append(user.getUserId());
                stringBuilder.append("-");
                stringBuilder.append(user.getUserName());
                stringBuilder.append("-");
                stringBuilder.append(user.getUserPwd());
                stringBuilder.append("-");
                stringBuilder.append(user.getUserWork());
                stringBuilder.append("-");
                stringBuilder.append(user.getUserAge());
                stringBuilder.append("\r\n");
            }
            if (!TextUtils.isEmpty(stringBuilder)) {
                show.setText(stringBuilder.toString());
            } else {
                show.setText("查询完毕，无数据");
                toast("查询完毕，无数据");
            }
        } catch (Exception e) {
            toast("查询失败");
            e.printStackTrace();
        }
    }

    public void toast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }
}
