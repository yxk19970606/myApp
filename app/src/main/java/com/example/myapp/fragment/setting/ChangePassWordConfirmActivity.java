package com.example.myapp.fragment.setting;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.fragment.myfragment.SettingActivity;
import com.example.myapp.fragment.personalinfo.PersonalInfoActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
public class ChangePassWordConfirmActivity extends BaseActivity {
    EditText passWordNew;
    EditText passWordConfirm;
    Button save;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_change_pass_word_confirm;
    }

    @Override
    protected void initView() {
       passWordConfirm = findViewById(R.id.et_password_new_confirm);
       passWordNew = findViewById(R.id.et_password_new);
       save = findViewById(R.id.btn_save_change_password);
       passWordNew.requestFocus();
       back = findViewById(R.id.tv_back_to_setting);
    }

    @Override
    protected void initData() {
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               save.setClickable(false);
               if (passWordConfirm.getText().toString().equals(passWordNew.getText().toString())){
                   if(passWordConfirm.getText().toString().length()<6){
                       if(passWordConfirm.getText().toString().length()==0){
                           showToast("密码不能为空");
                       }else{
                           showToast("密码不能小于六位");
                       }
                   }else{
                       System.out.println("objectId-------"+getStringFromSp("objectId"));
                       AVObject todo = AVObject.createWithoutData("android",getStringFromSp("objectId"));
                       System.out.println("password:"+passWordConfirm.getText().toString());
                       todo.put("passWord",passWordConfirm.getText().toString());
                       todo.saveInBackground().subscribe(new Observer<AVObject>() {
                           public void onSubscribe(Disposable disposable) {}
                           public void onNext(AVObject todo) { }
                           public void onError(Throwable throwable) {
                               showToast("请查看网络连接");
                           }
                           public void onComplete() {
                               navigateTo(SettingActivity.class);
                               saveStringToSp("passWord",passWordConfirm.getText().toString());
                               showToast("更改密码成功");
                           }
                       });
                   }

               }else{
                   Toast.makeText(mContext,"两次密码不一致",Toast.LENGTH_SHORT).show();
               }
               save.setClickable(true);
           }
       });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               navigateTo(SettingActivity.class);
           }
       });
    }
}