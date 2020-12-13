package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapp.R;
import com.example.myapp.entity.SuitcaseItemEntity;

import java.util.List;

public class SetDrugReminderAdapter extends ArrayAdapter implements View.OnClickListener{
    List<SuitcaseItemEntity> datas;
    Context mContext;
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public SetDrugReminderAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        datas = objects;
    }
    public interface OnItemClickListener{
        void ItemClickListener(View view);
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.item_for_buy_drug_layou,parent,false);
      TextView currentNumber = view.findViewById(R.id.tv_current_number);
      TextView reminderNumber = view.findViewById(R.id.tv_reminder_number);
      TextView reminderTime = view.findViewById(R.id.tv_reminder_time);
      currentNumber.setText(datas.get(position).getCurrentNumber());
      reminderNumber.setText("剩余"+datas.get(position).getReminderNumber()+"片时");
      reminderTime.setText(datas.get(position).getReminderTime());
      reminderNumber.setOnClickListener(this);
      reminderTime.setOnClickListener(this);
      currentNumber.setOnClickListener(this);
      return view;
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener!=null){
            onItemClickListener.ItemClickListener(v);
        }
    }
}
