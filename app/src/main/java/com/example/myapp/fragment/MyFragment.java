package com.example.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapp.R;
import com.example.myapp.fragment.drug.DrugSuitcaseActivity;
import com.example.myapp.fragment.myfragment.EmailActivity;
import com.example.myapp.fragment.myfragment.HelpActivity;
import com.example.myapp.fragment.personalinfo.PersonalInfoActivity;
import com.example.myapp.fragment.myfragment.SettingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private Context context;
    TextView account;  //带头像中的字符表示
    TextView nickName; //昵称
    TextView id;       //id编号显示
    View setting;      //设置面板
    View help;         //帮助面板
    View suitcase;     //医疗箱面板
    View record;       //家人记录面板
    View addDrug;      //添加智能药盒面板
    View login;        //登录面板
    View email;        //邮件面板


    public MyFragment() {
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        context = container.getContext();
        setClickEventForButtons(view);
        return view;
        //return null;
    }
    public void setClickEventForButtons(View view){
        setting = view.findViewById(R.id.label_setting);
        help = view.findViewById(R.id.label_help);
        suitcase = view.findViewById(R.id.label_dragSuitcase);
        record = view.findViewById(R.id.label_familyRecording);
        addDrug = view.findViewById(R.id.label_addDragBox);
        login = view.findViewById(R.id.label_login);
        email = view.findViewById(R.id.label_email);
        //设定动态的个人信息
        setBaseInfo(view);
        setting.setOnClickListener(this);
        help.setOnClickListener(this);
        suitcase.setOnClickListener(this);
        record.setOnClickListener(this);
        addDrug.setOnClickListener(this);
        login.setOnClickListener(this);
        email.setOnClickListener(this);

    }

    public void navigationToNewActivity(Class cls){
        Intent in  = new Intent(context,cls);
        startActivity(in);
    }
    public void setBaseInfo(View view){
        account = view.findViewById(R.id.iv_account);
        nickName = view.findViewById(R.id.tv_nickName);
        id = view.findViewById(R.id.tv_id);
        SharedPreferences sp =  getActivity().getSharedPreferences("sp_tait",android.content.Context.MODE_PRIVATE);
        String accountName = sp.getString("nickName","暂无");
        nickName.setText(accountName);
        if(accountName.length()>2){
            accountName = accountName.substring(0,2);
        }
        account.setText(accountName);
        id.setText("健康号:"+sp.getString("id",""));
    }
    @Override
    public void onClick(View v) {
      Class cls = null;
      switch (v.getId()){
          case R.id.label_setting:
              cls = SettingActivity.class;
              break;
          case R.id.label_email:
              cls = EmailActivity.class;
              break;
          case R.id.label_help:
              cls = HelpActivity.class;
              break;
          case R.id.label_login:
              cls = PersonalInfoActivity.class;
              break;
          case R.id.label_dragSuitcase:
              cls = DrugSuitcaseActivity.class;
              break;
      }
      if (cls!=null){
          navigationToNewActivity(cls);
      }else{
          Toast.makeText(context,"还没有定义该页面",Toast.LENGTH_SHORT).show();
      }
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp =  getActivity().getSharedPreferences("sp_tait",android.content.Context.MODE_PRIVATE);
        String accountName = sp.getString("nickName","暂无");
        nickName.setText(accountName);
        if(accountName.length()>2){
            accountName = accountName.substring(0,2);
        }
        account.setText(accountName);
    }
}