package com.example.myapp.fragment.drug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.adapter.SetDrugReminderAdapter;
import com.example.myapp.entity.SuitcaseItemEntity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrugSuitcaseAddDrugActivity extends BaseActivity {
    private List<SuitcaseItemEntity> datas;
    private List<Integer> lists;
    private ListView view;
    private SetDrugReminderAdapter adapter;
    private SuitcaseItemEntity name;
    private int position;
    private TextView back;
    private TextView save;
    private TextView drugName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_drug_suitcase_add_drug;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.tv_back_to_suitcase);
        save = findViewById(R.id.tv_save);
        drugName = findViewById(R.id.et_drug_name);
        lists = new ArrayList<Integer>();
        view = findViewById(R.id.list_view_container);
        position =Integer.valueOf(getIntent().getStringExtra("position"));
        datas = new ArrayList<SuitcaseItemEntity>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        name = new SuitcaseItemEntity("0","0",df.format(new Date()),0,"");
        datas.add(name);
        adapter = new SetDrugReminderAdapter(mContext,0,datas);
        for(int i=0;i<1000;i++){
            lists.add(i);
        }
    }
    public void showPickTimer(TextView tv,int type){
        int location=0;
        if(type==1){
            location = Integer.valueOf(tv.getText().toString());
        }else{
            String temp = tv.getText().toString();
            location = Integer.valueOf(temp.substring(2,temp.length()-2));
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = lists.get(options1).toString();
                if(type==1){
                    tv.setText(tx);
                    name.setCurrentNumber(tx);
                }else if(type==2){
                    tv.setText("剩余"+tx+"片时");
                    name.setReminderNumber(tx);
                }

            }
        }).setCancelText(" ")
                .setSubmitText("完成")
                .setSubmitColor(R.color.teal_200)
                .setOutSideCancelable(true)
                .setTitleText("设置用量")
                .setSelectOptions(location)
                .build();
        pvOptions.setPicker(lists);
        pvOptions.show();

    }
    public void showPickDate(TextView tv){
        List<Integer> hours = new ArrayList<Integer>();
        List<List<Integer>> secon = new ArrayList<List<Integer>>();
        String[] time = tv.getText().toString().split(":");
        for(int i=0;i<24;i++){
            hours.add(i);
            secon.add(new ArrayList<>());
            for(int j=0;j<60;j++){
                secon.get(i).add(j);
            }
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String hour="";
                String minu="";
                if(options1<10){
                    hour = "0";
                }
                if(options2<10){
                    minu = "0";
                }
                hour+= String.valueOf(options1);
                minu+= String.valueOf(options2);
                String tx = hour+":"+minu;
                tv.setText(tx);
                name.setReminderTime(tx);
            }
        }).setCancelText(" ")
                .setSubmitText("完成")
                .setSubmitColor(R.color.teal_200)
                .setOutSideCancelable(true)
                .setTitleText("提醒时间")
                .setSelectOptions(Integer.valueOf(time[0]),Integer.valueOf(time[1]))
                .build();
        pvOptions.setPicker(hours,secon);
        pvOptions.show();
    }
    @Override
    protected void initData() {
        final Switch aSwitch = (Switch) findViewById(R.id.s_v);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    if (datas.size() == 0) {
                        datas.add(new SuitcaseItemEntity("0", "剩余0片时", "08:00", 0, ""));
                        adapter.notifyDataSetChanged();
                    } else {
                        view.setAdapter(adapter);
                    }
                    adapter.setOnItemClickListener(new SetDrugReminderAdapter.OnItemClickListener() {
                        @Override
                        public void ItemClickListener(View view) {
                            switch (view.getId()) {
                                case R.id.tv_current_number:
                                    showPickTimer(view.findViewById(R.id.tv_current_number), 1);
                                    break;
                                case R.id.tv_reminder_number:
                                    showPickTimer(view.findViewById(R.id.tv_reminder_number), 2);
                                    break;
                                case R.id.tv_reminder_time:
                                    showPickDate(view.findViewById(R.id.tv_reminder_time));
                                    break;
                            }
                        }
                    });

                } else {
                    datas.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drugName.getText().length()==0){
                    showToast("药物名称不能为空");
                }else{
                    if(aSwitch.isChecked()){
                        name.setIsNeedReminder(1);
                    }else{
                        name.setIsNeedReminder(0);
                    }
                    Intent intent = new Intent("broadsend.action");
                    name.setDrugName(drugName.getText().toString());
                    intent.putExtra("data",(Serializable)name);
                    intent.putExtra("type","add");
                    intent.putExtra("position",String.valueOf(position));
                    sendBroadcast(intent);
                    finish();
                }
            }
        });
    }
}