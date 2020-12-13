package com.example.myapp.adapter;

import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapp.R;


public class ViewAdapter extends PagerAdapter {
    private List<View> datas;
    private List<TextView> radioGroup;
    private int currentIndex=0;
    private int times = 0;
    private Context mContext;
    public ViewAdapter(List<View> list,List<TextView> radioGroup,Context context) {
        datas=list;
        mContext = context;
        this.radioGroup = radioGroup;
        radioGroup.get(0).setTextColor(mContext.getResources().getColor(R.color.teal_200));
        for(int i=1;i<datas.size();i++){
            radioGroup.get(i).setTextColor(mContext.getResources().getColor(R.color.dot_unchecked));
        }
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = datas.get(position);
        container.addView(view);
        times++;
        if (times > 2) {  //子视图大于三则由销毁程序处理
            radioGroup.get(currentIndex).setTextColor(mContext.getResources().getColor(R.color.dot_unchecked));
            if (currentIndex < position) {
                radioGroup.get(++currentIndex).setTextColor(mContext.getResources().getColor(R.color.teal_200));
            } else {
                radioGroup.get(--currentIndex).setTextColor(mContext.getResources().getColor(R.color.teal_200));
            }
        }
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
        if( (currentIndex==1&&position==2) || (currentIndex==datas.size()-2)&&position==datas.size()-3){
            radioGroup.get(currentIndex).setTextColor(mContext.getResources().getColor(R.color.dot_unchecked));
            if(currentIndex<position){
                radioGroup.get(--currentIndex).setTextColor(mContext.getResources().getColor(R.color.teal_200));
            }else{
                radioGroup.get(++currentIndex).setTextColor(mContext.getResources().getColor(R.color.teal_200));
            }
        }
    }
}
