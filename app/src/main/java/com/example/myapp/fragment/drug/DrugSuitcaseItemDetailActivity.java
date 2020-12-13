package com.example.myapp.fragment.drug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.adapter.SetDrugReminderAdapter;
import com.example.myapp.entity.SuitcaseItemEntity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DrugSuitcaseItemDetailActivity extends BaseActivity {
    private List<SuitcaseItemEntity> datas;
    private List<Integer> lists;
    private ListView view;
    private EditText drugName;
    private SetDrugReminderAdapter adapter;
    private SuitcaseItemEntity name;
    private int position;
    private TextView back;
    private TextView save;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_drug_suitcase_item_detail;
    }

    @Override
    protected void initView() {
       // test = findViewById(R.id.tv_drug_detail_item);
        back = findViewById(R.id.tv_back_to_suitcase);
        delete = findViewById(R.id.btn_delete_drug);
        save = findViewById(R.id.tv_save);
        lists = new ArrayList<Integer>();
        view = findViewById(R.id.list_view_container);
        drugName = findViewById(R.id.et_drug_name);
        name=(SuitcaseItemEntity)getIntent().getSerializableExtra("data");
        position =Integer.valueOf(getIntent().getStringExtra("position"));
        datas = new ArrayList<SuitcaseItemEntity>();
        datas.add(name);
        drugName.setText(name.getDrugName());
        String position = getIntent().getStringExtra("position");
        System.out.println("接收的数据的名称"+name.getDrugName());
        System.out.println("接收的数据的位置"+position);
        System.out.println("接收的数据的状态"+String.valueOf(name.getIsNeedReminder()));
        System.out.println("接收的当前数量"+name.getCurrentNumber());
        System.out.println("接收的提醒数量"+name.getReminderNumber());
        System.out.println("接收的提醒时间"+name.getReminderTime());
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
                        datas.add(name);
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
        if (name.getIsNeedReminder() == 1) {
            aSwitch.setChecked(true);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aSwitch.isChecked()){
                    name.setIsNeedReminder(1);
                }else{
                    name.setIsNeedReminder(0);
                }
                name.setDrugName(drugName.getText().toString());
                Intent intent = new Intent("broadsend.action");
                intent.putExtra("data",(Serializable)name);
                intent.putExtra("type","save");
                intent.putExtra("position",String.valueOf(position));
                sendBroadcast(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                //alertDialog.setIcon(R.mipmap.advise);
                alertDialog.setTitle("删除药品");
                alertDialog.setMessage("这个药品除历史服用记录以外的所有信息包括：服用提醒、买药提醒、药品箱记录都将被删除。是否确认删除？");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       showToast("你点击了取消按钮"+String.valueOf(which));
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("broadsend.action");
                        intent.putExtra("type","delete");
                        intent.putExtra("position",String.valueOf(position));
                        sendBroadcast(intent);
                        finish();
                    }
                });
                alertDialog.show();
            }
        });

        //        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("broadsend.action");
//                datas.setDrugName("更改的名称");
//                datas.setIsNeedReminder(1);
//                intent.putExtra("data",(Serializable)datas);
//                intent.putExtra("position",String.valueOf(position));
//                sendBroadcast(intent);
//                finish();
//            }
//        });

    }
}






//        oplantime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //自定义控件
//                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                View view = (LinearLayout) getLayoutInflater().inflate(R.layout.time_dialog, null);
//                final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
//                //初始化时间
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                timePicker.setIs24HourView(true);
//                timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
//                timePicker.setCurrentMinute(Calendar.MINUTE);
//                //设置time布局
//                builder.setView(view);
//                builder.setTitle("设置时间信息");
//                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int mHour = timePicker.getCurrentHour();
//                                int mMinute = timePicker.getCurrentMinute();
//                                //时间小于10的数字 前面补0 如01:12:00
//                                oplantime.setText(new StringBuilder().append(mHour < 10 ? "0" + mHour : mHour).append(":")
//                                        .append(mMinute < 10 ? "0" + mMinute : mMinute).append(":00"));
//                                dialog.cancel();
//                            }
//                        });
//                builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.create().show();
//            }
//        });

//        oplantime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
//                    @Override
//                    public void onTimeSelect(Date date, View v) {
//                        SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");
//                       Toast.makeText(mContext,ft.format(date),Toast.LENGTH_SHORT).show();
//                    }
//                }).setCancelText("取消")
//                  .setType(new boolean[]{false, false, false,true, true, false})
//                  .setTitleText("选择数量")
//                  .isCyclic(true)
//                  .setSubmitText("确定").build();
//                pvTime.show();

//条件选择器
//
//                OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
//                    @Override
//                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                            //返回的分别是三个级别的选中位置
//                            String tx = lists.get(options1).toString();
//                            showToast(tx);
//                    }
//                }).setCancelText(" ")
//                  .setSubmitText("完成")
//                  .setSubmitColor(R.color.teal_200)
//                  .setOutSideCancelable(true)
//                  .setTitleText("设置用量")
//                  .build();
//                pvOptions.setPicker(lists);
//                pvOptions.show();

//                showToast("开始生成时间选择器具");
//                WheelView wheelView = findViewById(R.id.wheelview);
//
//                wheelView.setCyclic(false);
//
//                final List<String> mOptionsItems = new ArrayList<>();
//                mOptionsItems.add("item0");
//                mOptionsItems.add("item1");
//                mOptionsItems.add("item2");
//
//                wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
//                wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(int index) {
//                        Toast.makeText(mContext, "" + mOptionsItems.get(index), Toast.LENGTH_SHORT).show();
//                    }
//                });
//                wheelView.buildLayer();

//  }
//});
//}