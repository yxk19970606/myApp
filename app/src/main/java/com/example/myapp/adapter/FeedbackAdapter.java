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
import com.example.myapp.entity.Feedback;
import com.example.myapp.entity.VideoEntity;
import com.example.myapp.fragment.setting.FeedBackHistoryDetailActivity;

import java.util.List;

public class FeedbackAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<Feedback> datas;
    private OnItemClickListener onItemClickListener;
    public FeedbackAdapter(Context context, List<Feedback> datas){
        mContext = context;
        this.datas = datas;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void changeData(){
        datas.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feedback_layout,parent,false);
        FeedbackAdapter.ViewHolder viewHolder = new FeedbackAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeedbackAdapter.ViewHolder vh = (FeedbackAdapter.ViewHolder) holder;
        Feedback videoEntity = datas.get(position);
        vh.content.setText(videoEntity.getContent());
        vh.state.setText(videoEntity.getState());
        vh.time.setText(videoEntity.getTime());
        if (onItemClickListener != null) {
             vh.viewContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView content;
        private TextView time;
        private TextView state;
        private View viewContent;
        public ViewHolder(@NonNull View view) {
            super(view);
            content = view.findViewById(R.id.tv_content);
            time = view.findViewById(R.id.tv_time);
            state = view.findViewById(R.id.tv_feedback_state);
            viewContent = view.findViewById(R.id.layout_item_container);
        }
    }
}
