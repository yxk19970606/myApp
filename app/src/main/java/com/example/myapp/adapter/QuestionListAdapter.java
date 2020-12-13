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

public class QuestionListAdapter extends ArrayAdapter<QuestionEntity> {
    private List<List<QuestionEntity>> datas;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private int index;
    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull List<QuestionEntity> objects,List<List<QuestionEntity>> datas) {
        super(context, resource,objects);
        this.datas = datas;
        mContext = context;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position,int index);
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
        System.out.println("position:------"+String.valueOf(position));
        if(position==0){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_help_search,parent,false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(view,-1,-1);
                }
            });
            return view;
        }else {
            LinearLayout view;
            view= (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.item_card_container, parent, false);
            for(int i=0;i<datas.get(position).size();i++){
                View item = LayoutInflater.from(mContext).inflate(R.layout.item_for_help_layout, parent, false);
                TextView tv = (TextView) item.findViewById(R.id.tv_title);
                ImageView iv = (ImageView) item.findViewById(R.id.iv_right_arrow);
                tv.setText(datas.get(position).get(i).getTitle());
                tv.setTag(String.valueOf(i));
                if(i!=0){
                    index = i;
                    iv.setBackgroundResource(R.mipmap.icon_left_arrow);
                    if(onItemClickListener!=null) {
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String str;
                                TextView tv;
                                tv= (view.findViewById(R.id.tv_title));
                                str = tv.getTag().toString();
                                onItemClickListener.onItemClick(view,position,Integer.valueOf(str));
                            }
                        });
                    }
                }
                view.addView(item);
            }
            return view;
        }
        }

}
