package com.example.myapp.fragment.myfragment;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutUsActivity extends BaseActivity {
    TextView privace;
    TextView clause;
    TextView backToSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
       clause = findViewById(R.id.tv_clause);
       privace = findViewById(R.id.tv_privacy);
       backToSetting = findViewById(R.id.back_to_setting);
    }

    @Override
    protected void initData() {
       clause.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
       privace.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
       backToSetting.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }
}