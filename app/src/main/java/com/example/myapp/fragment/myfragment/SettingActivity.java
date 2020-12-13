package com.example.myapp.fragment.myfragment;

import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.HomeActivity;
import com.example.myapp.fragment.setting.ChangePassWordAskActivity;
import com.example.myapp.fragment.setting.FeedBackActivity;
import com.example.myapp.fragment.setting.FunctionIntroduceActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private TextView backToMore;   //返回到more也买你
    private View funcIntroduce;    //功能介绍
    private View feedBack;         //反馈
    private View aboutUs;          //关于我们
    private View identity;         //身份设置
    private View password;         //更改密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
       backToMore = findViewById(R.id.back_to_more);
       funcIntroduce = findViewById(R.id.label_function_introduce);
       feedBack = findViewById(R.id.label_feedback);
       aboutUs = findViewById(R.id.label_about_us);
       identity = findViewById(R.id.label_identity_setting);
       password = findViewById(R.id.label_change_password);
    }

    @Override
    protected void initData() {
       backToMore.setOnClickListener(this);
       feedBack.setOnClickListener(this);
       aboutUs.setOnClickListener(this);
       funcIntroduce.setOnClickListener(this);
       identity.setOnClickListener(this);
       password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       Class cls = null;
       switch (v.getId()){
           case R.id.back_to_more:
               navigateTo(HomeActivity.class);
               break;
           case R.id.label_about_us:
               cls = AboutUsActivity.class;
               break;
           case R.id.label_change_password:
               navigateTo(ChangePassWordAskActivity.class);
               break;
           case R.id.label_identity_setting:
               Toast.makeText(mContext,"弹出确认按钮",Toast.LENGTH_SHORT).show();
               break;
           case R.id.label_feedback:
               cls = FeedBackActivity.class;
               break;
           case R.id.label_function_introduce:
               cls = FunctionIntroduceActivity.class;
               break;

       }
       if (cls==null){
           Toast.makeText(this.mContext, "测试", Toast.LENGTH_SHORT).show();
       } else{
           navigateTo(cls);
       }
    }
}