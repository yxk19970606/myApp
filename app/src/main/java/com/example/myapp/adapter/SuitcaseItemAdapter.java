package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapp.R;
import com.example.myapp.entity.QuestionEntity;
import com.example.myapp.entity.SuitcaseItemEntity;
import java.util.List;
public class SuitcaseItemAdapter extends ArrayAdapter<SuitcaseItemEntity> {
    private List<SuitcaseItemEntity> datas;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    public SuitcaseItemAdapter(@NonNull Context context, int resource, List<SuitcaseItemEntity> datas) {
        super(context, resource, datas);
        this.datas = datas;
        mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void ItemClickEvent(View view,int position);
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item;
        if (position==datas.size()-1){
            item  = LayoutInflater.from(mContext).inflate(R.layout.item_suitcase_button_layout,parent,false);
            item.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.ItemClickEvent(v,position);
                    }
                }
            });
        }else{
            item = LayoutInflater.from(mContext).inflate(R.layout.item_for_suitcase_layout,parent,false);
            LinearLayout layout = item.findViewById(R.id.layout_drug_detail);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.ItemClickEvent(v,position);
                    }
                }
            });
            if(datas.get(position).getIsNeedReminder()==1){
                TextView title = item.findViewById(R.id.tv_suitcase_title);
                if(Integer.valueOf(datas.get(position).getCurrentNumber())<Integer.valueOf(datas.get(position).getReminderNumber())){
                    title.setBackgroundResource(R.color.red);
                    title.setText("你需要补药了");
                }else{
                    title.setBackgroundResource(R.color.teal_200);
                    title.setText("已添加提醒");
                }
            }
            TextView addReminder = item.findViewById(R.id.tv_add_suitcase_reminder);
            TextView stopReminder = item.findViewById(R.id.tv_stop_suitcase_reminder);
            TextView groughtDrug = item.findViewById(R.id.tv_have_brought_drug);
            TextView headIcon = item.findViewById(R.id.tv_suitcase_head_icon);
            TextView drugName = item.findViewById(R.id.tv_drug_name);
            drugName.setText(datas.get(position).getDrugName());
            if(datas.get(position).getDrugName().length()>2){
                headIcon.setText(datas.get(position).getDrugName().substring(0,2));
            }else{
                headIcon.setText(datas.get(position).getDrugName());
            }

            addReminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.ItemClickEvent(v,position);
                    }
                }
            });
            stopReminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.ItemClickEvent(v,position);
                    }
                }
            });
            groughtDrug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.ItemClickEvent(v,position);
                    }
                }
            });
        }
        return item;
    }
}
