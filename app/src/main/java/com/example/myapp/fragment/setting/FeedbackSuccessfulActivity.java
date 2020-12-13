package com.example.myapp.fragment.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapp.activity.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.fragment.myfragment.SettingActivity;

public class FeedbackSuccessfulActivity extends BaseActivity {
    TextView backToSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_feedback_successful;
    }

    @Override
    protected void initView() {
        backToSetting = findViewById(R.id.tv_back_to_setting);
    }

    @Override
    protected void initData() {
        backToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(SettingActivity.class);
            }
        });
    }
}