package com.example.myapp.activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.R;
import com.example.myapp.util.StringUtils;

import cn.leancloud.AVObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnRegister;
    private EditText etNickName;
    @Override
    public int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnRegister = findViewById(R.id.btn_register);
        etNickName = findViewById(R.id.et_nickName);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String nickName = etNickName.getText().toString().trim();
                register(account,pwd,nickName);
            }
        });
    }
    private void register(String account,String pwd,String nickName){
        if (StringUtils.isEmpty(account)){
            showToast("账号不能为空");
        }
        else if (StringUtils.isEmpty(pwd)){
            showToast("密码不能为空");
        }else if (StringUtils.isEmpty(nickName)){
            showToast("昵称不能为空");
        }
        else{
            // 构建对象
            AVObject todo = new AVObject("android");
            todo.put("userName",account);
            todo.put("passWord",pwd);
            todo.put("nickName",nickName);

            todo.saveInBackground().subscribe(new Observer<AVObject>() {
                public void onSubscribe(Disposable disposable) {}
                public void onNext(AVObject todo) {
                    // 成功保存之后，执行其他逻辑
                    //System.out.println("保存成功。objectId：" + todo.getObjectId());
                    //showToast("注册成功");
                }
                public void onError(Throwable throwable) {
                    // 异常处理
                    System.out.println(throwable);
                    showToast("账号已存在");
                }
                public void onComplete() {
                    navigateTo(LoginActivity.class);
                    showToast("注册成功");
                }
            });
        }
    }
}