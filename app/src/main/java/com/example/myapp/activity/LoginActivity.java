package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.util.StringUtils;

import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity{
    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;
    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                login(account,pwd);
            }
        });
    }

    private void login(String account,String pwd){
        if (StringUtils.isEmpty(account)){
            showToast("账号不能为空");
        }
        else if (StringUtils.isEmpty(pwd)){
           showToast("密码不能为空");
        }
        else{
            AVQuery<AVObject> query = new AVQuery<>("android");
            query.whereEqualTo("userName",account);
            query.findInBackground().subscribe(new Observer<List<AVObject>>() {
                public void onSubscribe(Disposable disposable) {
                    System.out.println("查询结果");
                }
                public void onNext(List<AVObject> students) {
                    if (students.size()==0){
                        showToast("账号不存在");
                        etAccount.setText("");
                        etPwd.setText("");
                        etAccount.requestFocus();
                    }
                    else if(students.get(0).getString("passWord").equals(pwd)){
                        saveStringToSp("token",students.get(0).getString("objectId"));
                        navigateTo(HomeActivity.class);
                        showToast("登录成功");
                    }else{
                        showToast("密码不正确");
                        etPwd.setText("");
                    }

                }
                public void onError(Throwable throwable) {
                    System.out.println(throwable);
                }
                public void onComplete() {}
            });
        }


    }
}