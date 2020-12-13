package com.example.myapp.fragment.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myapp.adapter.ViewAdapter;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.adapter.FunctionIntroduceAdapter;
import com.example.myapp.entity.IntorduceItemEntity;
import java.util.List;
import java.util.ArrayList;

public class FunctionIntroduceActivity extends BaseActivity {
    private List<View> data;
    private ViewAdapter adapter;
    private RelativeLayout back;
    private ViewPager view;
    private List<TextView> radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_function_introduce;
    }

    @Override
    protected void initView() {
        view = findViewById(R.id.recycler_container);
        data = new ArrayList<View>();
        radioGroup = new ArrayList<TextView>();
        back = findViewById(R.id.layout_back_to_setting);
    }

    @Override
    protected void initData() {
        BasicInfoInit();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new ViewAdapter(data,radioGroup,mContext);
        //LinearLayoutManager manager = new LinearLayoutManager(mContext);
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //view.setLayoutManager(manager);
        view.setAdapter(adapter);
    }

    public void BasicInfoInit(){
        List<Integer> images = new ArrayList();
        images.add(R.mipmap.tips1);
        images.add(R.mipmap.tips2);
        images.add(R.mipmap.tips3);
        images.add(R.mipmap.tips4);
        images.add(R.mipmap.tips5);
        images.add(R.mipmap.tips6);
        images.add(R.mipmap.tips7);
        images.add(R.mipmap.tips8);
        images.add(R.mipmap.tips9);
        images.add(R.mipmap.tips10);
        String[] contents = new String[]{"无需打开软件、作画通知即可“记录已服药”或“推迟提醒”。","医嘱、药品、说明书...任何你需要的信息拍张照片就搞定！","长按“+”即可选择需要的时长。",
        "长按提醒即可快速进入编辑模式。","历史记录中可以快速添加药呦~","错过服药时间没关系、服药后可在<服药记录>详情 轻松“标记为已服药”。","未携带药盒离开预设地点、手机会提醒你、不会忘记带药哦！",
        "找不到药盒点一下“找盒子”,药盒会“告诉”你它在哪儿~","想要体验最新功能？无限升级来帮你。更多>智能药盒>软件升级。","家人忘记吃药通知你，还可以一键免费通话提醒他哦！"
        };
        String[] titles   = new String[]{"快速记录","无需繁琐输入","多个推迟时间可选","快速编辑","药品历史记录","更新服药状态","忘带药提醒","找药更方便","无线升级药盒软件","查看家人服药记录"};
        LinearLayout listView = findViewById(R.id.layout_index_list);
        for(int i =0;i<10;i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_function_introduce_layout,null,false);
            ImageView imageView = view.findViewById(R.id.iv_head_image);
            TextView title = view.findViewById(R.id.tv_title);
            TextView content = view.findViewById(R.id.tv_content);
            imageView.setBackgroundResource(images.get(i));
            System.out.println(images.get(i));
            title.setText(titles[i]);
            content.setText(contents[i]);
            data.add(view);
            radioGroup.add((TextView)listView.getChildAt(i));
        }
    }
}