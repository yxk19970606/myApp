package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.entity.FeedBackDetailEntity;
import com.example.myapp.entity.IntorduceItemEntity;

import java.util.List;

public class FunctionIntroduceAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private Context mContext;
        private List<IntorduceItemEntity> datas;
        public FunctionIntroduceAdapter(Context context, List<IntorduceItemEntity> datas){
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_function_introduce_layout,parent,false);
            FunctionIntroduceAdapter.ViewHolder viewHolder = new FunctionIntroduceAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            FunctionIntroduceAdapter.ViewHolder vh = (FunctionIntroduceAdapter.ViewHolder) holder;
            IntorduceItemEntity videoEntity = datas.get(position);
            if (videoEntity!=null){
                vh.content.setText(videoEntity.getDesc());
                vh.title.setText(videoEntity.getTitle());
                vh.headImg.setBackgroundResource(videoEntity.getImageId());
            }
        }
        @Override
        public int getItemCount() {
            return datas.size();
        }



        static class ViewHolder extends  RecyclerView.ViewHolder{
            private TextView content;
            private TextView title;
            private ImageView headImg;
            public ViewHolder(@NonNull View view) {
                super(view);
                content = view.findViewById(R.id.tv_content);
                title = view.findViewById(R.id.tv_title);
                headImg = view.findViewById(R.id.iv_head_image);
            }
        }

}
