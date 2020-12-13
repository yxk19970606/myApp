package com.example.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("父类BaseActivity开始创建---------------------");
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }
    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();

    public void showToast(String info){
        Toast.makeText(mContext,info,Toast.LENGTH_SHORT).show();
    }
    public void navigateTo(Class cls){
        Intent in  = new Intent(mContext,cls);
        startActivity(in);
    }
    protected void saveStringToSp(String key,String val){
        SharedPreferences sp =  getSharedPreferences("sp_tait",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,val);
        editor.commit();
    }
    protected String getStringFromSp(String key){
        SharedPreferences sp =  getSharedPreferences("sp_tait",MODE_PRIVATE);
        String str;
        str = sp.getString(key,"");
        return str;

    }
}

