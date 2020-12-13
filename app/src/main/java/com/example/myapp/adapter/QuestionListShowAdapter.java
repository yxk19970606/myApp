package com.example.myapp.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapp.R;
import com.example.myapp.entity.QuestionEntity;
import com.example.myapp.testActivity;
import java.util.ArrayList;
import java.util.List;

public class QuestionListShowAdapter extends ArrayAdapter<QuestionEntity> {
    private List<QuestionEntity> datas;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    public QuestionListShowAdapter(@NonNull Context context, int resource, @NonNull List<QuestionEntity> objects) {
        super(context, resource,objects);
        this.datas = objects;
        mContext = context;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LinearLayout view;
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_for_help_show_layout, parent, false);
            TextView tv = (TextView) item.findViewById(R.id.tv_title);
            tv.setText(datas.get(position).getTitle());
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(v,position);
                    }
                }
            });
            return item;
        }

}
