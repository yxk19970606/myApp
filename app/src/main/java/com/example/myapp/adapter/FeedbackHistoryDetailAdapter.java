package com.example.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.entity.FeedBackDetailEntity;
import com.example.myapp.entity.VideoEntity;
import com.example.myapp.fragment.setting.FeedBackHistoryDetailActivity;

import java.util.List;

public class FeedbackHistoryDetailAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<FeedBackDetailEntity> datas;
    public FeedbackHistoryDetailAdapter(Context context, List<FeedBackDetailEntity> datas){
        mContext = context;
        this.datas = datas;
    }

    public void changeData(){
        datas.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feedback_history_detail_layout,parent,false);
        FeedbackHistoryDetailAdapter.ViewHolder viewHolder = new FeedbackHistoryDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeedbackHistoryDetailAdapter.ViewHolder vh = (FeedbackHistoryDetailAdapter.ViewHolder) holder;
        FeedBackDetailEntity videoEntity = datas.get(position);
        if (videoEntity!=null){
            vh.content.setText(videoEntity.getContent());
            vh.time.setText(videoEntity.getTime());
            vh.id.setText(videoEntity.getId());
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView content;
        private TextView time;
        private TextView id;
        private View viewContent;
        public ViewHolder(@NonNull View view) {
            super(view);
            content = view.findViewById(R.id.tv_content);
            time = view.findViewById(R.id.tv_time);
            id = view.findViewById(R.id.tv_feedback_history_detail_id);
            viewContent = view.findViewById(R.id.layout_item_container);
        }
    }
}
