package com.example.myapp.fragment.personalinfo;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.myapp.R;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.HomeActivity;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.fragment.MyFragment;

import org.w3c.dom.Text;

import java.security.PrivilegedAction;

public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{
    private View headIcon;
    private View personName;
    private Button loginOut;
    private TextView nickName;
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initView() {
        nickName = findViewById(R.id.tv_nickName);
        headIcon = findViewById(R.id.label_head_icon);
        personName = findViewById(R.id.label_person_name);
        loginOut = findViewById(R.id.login_out);
        back = findViewById(R.id.back_to_more);
    }

    @Override
    protected void initData() {
        headIcon.setOnClickListener(this);
        loginOut.setOnClickListener(this);
        personName.setOnClickListener(this);
        back.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Class cls = null;
        switch (v.getId()){
            case R.id.label_head_icon:
                changeHeadIcon();
                break;
            case R.id.label_person_name:
                cls = ChangeNameActivity.class;
                break;
            case R.id.login_out:
                loginOut();
                break;
            case R.id.back_to_more:
                cls = HomeActivity.class;
                break;
        }
        if(cls!=null){
            navigateTo(cls);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        nickName.setText(getStringFromSp("nickName"));
    }
    public void loginOut(){
        AlertDialog alert = null;
        AlertDialog.Builder bulider =null;
        bulider = new AlertDialog.Builder(mContext);
        alert = bulider.setTitle("退出登录...")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateTo(LoginActivity.class);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        alert.show();
    }
    public void changeHeadIcon(){
        AlertDialog alert = null;
        AlertDialog.Builder bulider =null;
        bulider = new AlertDialog.Builder(mContext);
        alert = bulider.setTitle("设置头像")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateTo(LoginActivity.class);
                    }
                })
                .setNegativeButton("从手机相册选择", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        alert.show();
    }
}