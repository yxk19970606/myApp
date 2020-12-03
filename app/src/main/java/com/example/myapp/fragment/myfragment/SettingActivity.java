package com.example.myapp.fragment.myfragment;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.fragment.MyFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private TextView backToMore;
    private View funcIntroduce;
    private View feedBack;
    private View aboutUs;
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
    }

    @Override
    protected void initData() {
       backToMore.setOnClickListener(this);
       feedBack.setOnClickListener(this);
       aboutUs.setOnClickListener(this);
       funcIntroduce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       Class cls = null;
       switch (v.getId()){
           case R.id.back_to_more:
               finish();
               break;
           case R.id.label_about_us:
               cls = AboutUsActivity.class;
               break;
       }
       if (cls==null){
           Toast.makeText(this.mContext, "测试", Toast.LENGTH_SHORT).show();
       } else{
           navigateTo(cls);
       }


    }
}