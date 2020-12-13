package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.activity.RegisterActivity;

import cn.leancloud.AVOSCloud;

public class MainActivity extends BaseActivity {
    private Button btnLogin;
    private Button btnRegister;
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        AVOSCloud.initialize(this, "TpI3JVs5djUS0fY2YlxFY3EM-gzGzoHsz", "jEFJOzEMoR0cDExCi5xOEm2G", "https://tpi3jvs5.lc-cn-n1-shared.com");

    }

    @Override
    protected void initData() {
            btnLogin.setOnClickListener((v)->{
                    navigateTo(LoginActivity.class);
            });
            btnRegister.setOnClickListener((e)->{
                    navigateTo(RegisterActivity.class);
            });
    }

}