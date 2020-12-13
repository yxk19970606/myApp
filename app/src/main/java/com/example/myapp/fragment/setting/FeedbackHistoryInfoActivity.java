package com.example.myapp.fragment.setting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.adapter.FeedbackAdapter;
import com.example.myapp.entity.Feedback;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import io.reactivex.Observer;
import com.example.myapp.entity.Feedback;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Timer;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
public class FeedbackHistoryInfoActivity extends BaseActivity {
    private RecyclerView view;
    private TextView backToFeedBack;
    private List<Feedback> data;
    private FeedbackAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_feedback_history_info;
    }

    @Override
    protected void initView() {
        backToFeedBack = findViewById(R.id.tv_back_to_feedback);
        view = findViewById(R.id.rcv_recyclerview);
    }

    @Override
    protected void initData() {
        data = new ArrayList<Feedback>();
        backToFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(FeedBackActivity.class);
            }
        });

//
//        ListView list_main = (ListView) findViewById(R.id.li_main);
        SwipeRefreshLayout sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        sr.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
              @Override
             public void onRefresh() {
                  System.out.println("开始刷新----------");
                  sr.setRefreshing(false);
                  adapter.changeData();
              }
           });
//          sr.setRefreshing(true);
//        Thread myThread1 = new MyThread(adapter,view,data); // 创建一个新的线程  myThread1  此线程进入新建状态
//        myThread1.start();  // 调用start()方法使得线程进入可执行状态
        AVQuery<AVObject> query = new AVQuery<>("feedback");
        query.whereEqualTo("id",Integer.valueOf(getStringFromSp("id")));

                query.findInBackground().subscribe(new Observer<List<AVObject>>() {
                public void onSubscribe(Disposable disposable) {}
                public void onNext(List<AVObject> students) {
                    System.out.println(students.size());
                    for(int i =0;i<students.size();i++){
                        Date d = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sd = sdf.format(new Date(students.get(i).getCreatedAt().getTime())); // 时间戳转换日期
                        Feedback item = new Feedback(students.get(i).getString("feedContent"),students.get(i).getString("state"),sd,students.get(i).getObjectId());
                        data.add(item);
                    }
                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    view.setLayoutManager(manager);
                    adapter = new FeedbackAdapter(mContext,data);
                    adapter.setOnItemClickListener(new FeedbackAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                           Intent intent = new Intent(view.getContext(),FeedBackHistoryDetailActivity.class);
                           intent.putExtra("objectId",data.get(position).getObjectId());
                           intent.putExtra("time",data.get(position).getTime());
                           intent.putExtra("content",data.get(position).getContent());
                           startActivity(intent);
                        }
                    });
                    view.setAdapter(adapter);
                }
                public void onError(Throwable throwable) {}
                public void onComplete() {}
            });
    }
}