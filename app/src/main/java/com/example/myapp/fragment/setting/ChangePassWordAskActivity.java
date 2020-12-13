package com.example.myapp.fragment.setting;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.activity.BaseActivity;
import com.example.myapp.fragment.myfragment.SettingActivity;

import org.w3c.dom.Text;

public class ChangePassWordAskActivity extends BaseActivity {
    private Button btnChangePassWord;
    private EditText etPassWord;
    private TextView backToSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_change_pass_word;
    }

    @Override
    protected void initView() {

        etPassWord = findViewById(R.id.et_password_old);
        btnChangePassWord = findViewById(R.id.btn_change_password);
        backToSetting = findViewById(R.id.tv_back_to_setting);
    }

    @Override
    protected void initData() {
        etPassWord.requestFocus();
        backToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }

        });
        btnChangePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str ;
                str = etPassWord.getText().toString();
                System.out.println(str);
                String pas=getStringFromSp("passWord");
                if(str.equals(getStringFromSp("passWord"))){
                    navigateTo(ChangePassWordConfirmActivity.class);
                }else{
                    Toast.makeText(mContext,"原密码不正确",Toast.LENGTH_SHORT).show();
                    etPassWord.setText("");
                }
            }
        });

    }
}