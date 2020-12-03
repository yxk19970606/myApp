package com.example.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.fragment.myfragment.EmailActivity;
import com.example.myapp.fragment.myfragment.HelpActivity;
import com.example.myapp.fragment.myfragment.SettingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private Context context;
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
        View setting = view.findViewById(R.id.label_setting);
        View help = view.findViewById(R.id.label_help);
        View suitcase = view.findViewById(R.id.label_dragSuitcase);
        View record = view.findViewById(R.id.label_familyRecording);
        View addDrug = view.findViewById(R.id.label_addDragBox);
        View login = view.findViewById(R.id.label_login);
        View email = view.findViewById(R.id.label_email);

        setting.setOnClickListener(this);
        help.setOnClickListener(this);
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
      }
      if (cls!=null){
          navigationToNewActivity(cls);
      }else{
          Toast.makeText(context,"还没有定义该页面",Toast.LENGTH_SHORT).show();
      }
    }
}