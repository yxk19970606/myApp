package com.example.myapp.fragment.drug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapp.activity.BaseActivity;
import com.example.myapp.adapter.SuitcaseItemAdapter;
import com.example.myapp.entity.SuitcaseItemEntity;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class DrugSuitcaseActivity extends BaseActivity {
    private TextView back;
    private ListView view;
    private BroadcastReceiver receiver;
    private SuitcaseItemAdapter adapter;
    private List<SuitcaseItemEntity> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_drug_suitcase;
    }
    @Override
    protected void initView() {
        back = findViewById(R.id.tv_back_to_setting);
        view = findViewById(R.id.lv_drug_item_container);
        datas = new ArrayList<SuitcaseItemEntity>();
        datas.add(new SuitcaseItemEntity("10","2","10:10",0,"阿莫西林"));
        datas.add(new SuitcaseItemEntity("2","2","10:10",0,"感冒"));
        datas.add(new SuitcaseItemEntity("2","2","10:10",0,"发烧"));
        datas.add(new SuitcaseItemEntity("10","2","10:10",1,"泻药"));
        datas.add(new SuitcaseItemEntity("1","1","10:10",0,"双黄连"));
        datas.add(new SuitcaseItemEntity("2","10","10:10",1,"板蓝根"));
        datas.add(new SuitcaseItemEntity("0","0","10:10",0,"解毒丸"));
        datas.add(new SuitcaseItemEntity("5","1","10:10",1,"鲱鱼玩"));
        datas.add(new SuitcaseItemEntity("0","0","10:10",0,"有盾"));
        datas.add(new SuitcaseItemEntity("2","6","10:10",1,"士大夫"));
        adapter = new SuitcaseItemAdapter(mContext,0,datas);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int position = Integer.valueOf(intent.getStringExtra("position"));
                String type = intent.getStringExtra("type");
                if(type.equals("save")){
                    SuitcaseItemEntity temp = (SuitcaseItemEntity)intent.getSerializableExtra("data");
                    datas.get(position).setDrugName(temp.getDrugName());
                    datas.get(position).setCurrentNumber(temp.getCurrentNumber());
                    datas.get(position).setIsNeedReminder(temp.getIsNeedReminder());
                    datas.get(position).setReminderNumber(temp.getReminderNumber());
                    datas.get(position).setReminderTime(temp.getReminderTime());
                }else if(type.equals("delete")){
                    datas.remove(position);
                }else if(type.equals("add")){
                    SuitcaseItemEntity temp = (SuitcaseItemEntity)intent.getSerializableExtra("data");
                    datas.add(datas.size()-1,temp);
                }
                adapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    protected void initData() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnItemClickListener(new SuitcaseItemAdapter.OnItemClickListener() {
            @Override
            public void ItemClickEvent(View view,int position) {
                System.out.println("HASHConde=======>"+datas.hashCode());
               if(position==datas.size()-1){
                   registerReceiver(receiver,new IntentFilter("broadsend.action"));
                   Intent intent = new Intent(mContext,DrugSuitcaseAddDrugActivity.class);
                   intent.putExtra("position",String.valueOf(datas.size()));
                   startActivity(intent);
               }else{
                   switch (view.getId()){
                       case R.id.layout_drug_detail:
                           registerReceiver(receiver,new IntentFilter("broadsend.action"));
                           Intent intent = new Intent(mContext,DrugSuitcaseItemDetailActivity.class);
                           intent.putExtra("data",(Serializable)datas.get(position));
                           intent.putExtra("position",String.valueOf(position));
                           startActivity(intent);
                           break;
                       case R.id.tv_add_suitcase_reminder:
                           showToast("添加提醒药品按钮");
                           break;
                       case R.id.tv_stop_suitcase_reminder:
                           AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                           //alertDialog.setIcon(R.mipmap.advise);
                           alertDialog.setTitle("停服此药？");
                           alertDialog.setMessage("将会自动帮您停止关于此药品的所有提醒，是否确认停服？");
                           alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   showToast("你点击了取消按钮"+String.valueOf(which));
                               }
                           });
                           alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   showToast("你点击了确认按钮");
                               }
                           });
                           alertDialog.show();
                           break;
                       case R.id.tv_have_brought_drug:
                           registerReceiver(receiver,new IntentFilter("broadsend.action"));
                           Intent buyIntent = new Intent(mContext,DrugSuitcaseBuyDrugAcitvity.class);
                           buyIntent.putExtra("data",(Serializable)datas.get(position));
                           buyIntent.putExtra("position",String.valueOf(position));
                           startActivity(buyIntent);
                           break;
                   }
               }
            }
        });
        view.setAdapter(adapter);
    }
}