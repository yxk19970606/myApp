package com.example.myapp.fragment.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.adapter.FeedbackHistoryDetailAdapter;
import com.example.myapp.entity.FeedBackDetailEntity;
import com.example.myapp.entity.Feedback;

import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FeedBackHistoryDetailActivity extends BaseActivity {
    private TextView backToFeedBack;
    private RecyclerView view;
    private FeedbackHistoryDetailAdapter adapter;
    private List<FeedBackDetailEntity> data;
    private TextView save;
    private EditText message;
    private SwipeRefreshLayout sr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_feed_back_history_detail;
    }

    @Override
    protected void initView() {
        backToFeedBack = findViewById(R.id.tv_back_to_feedback);
        view = findViewById(R.id.rcv_recyclerview);
        save = findViewById(R.id.tv_feedback_message_save);
        message = findViewById(R.id.et_feed_back_message);
        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String parentId = intent.getStringExtra("objectId");
        String time = intent.getStringExtra("time");
        String content = intent.getStringExtra("content");
        FeedBackDetailEntity item = new FeedBackDetailEntity(content,time,parentId,getStringFromSp("id"));
        System.out.println("------------------->"+getStringFromSp("id"));
        data = new ArrayList<FeedBackDetailEntity>();
        data.add(item);
        backToFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(FeedBackActivity.class);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getText().toString().length()>0) {
                    AVObject todo = new AVObject("feedbackhistory");
                    todo.put("parentId",parentId);
                    todo.put("id",Integer.valueOf(getStringFromSp("id")));
                    todo.put("content",message.getText().toString());
                    todo.saveInBackground().subscribe(new Observer<AVObject>() {
                        public void onSubscribe(Disposable disposable) {}
                        public void onNext(AVObject todo) {
                            // 成功保存之后，执行其他逻辑
                            //System.out.println("保存成功。objectId：" + todo.getObjectId());
                            //showToast("注册成功");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String sd = sdf.format(new Date(todo.getCreatedAt().getTime())); // 时间戳转换日期
                            FeedBackDetailEntity item = new FeedBackDetailEntity(todo.getString("content"),sd,parentId,getStringFromSp("nickName"));
                            data.add(item);
                            adapter.notifyDataSetChanged();
                            message.setText("");
                            showToast("提交成功");
                        }
                        public void onError(Throwable throwable) {
                            // 异常处理
                            System.out.println(throwable);
                            showToast("保存失败请检查你的网络连接");
                        }
                        public void onComplete() {
                            //navigateTo(LoginActivity.class);
                            //showToast("保存成功");
                        }
                    });
                }else{
                    showToast("请输入内容...");
                }

            }
        });

//
//        ListView list_main = (ListView) findViewById(R.id.li_main);
        sr.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
              sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("开始刷新----------");
                sr.setRefreshing(false);
                //adapter.changeData();
            }
        });
//          sr.setRefreshing(true);
//        Thread myThread1 = new MyThread(adapter,view,data); // 创建一个新的线程  myThread1  此线程进入新建状态
//        myThread1.start();  // 调用start()方法使得线程进入可执行状态
        AVQuery<AVObject> query = new AVQuery<>("feedbackhistory");
        query.whereEqualTo("parentId",parentId);
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<AVObject> students) {
                System.out.println(students.size());
                for(int i =0;i<students.size();i++){
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String sd = sdf.format(new Date(students.get(i).getCreatedAt().getTime())); // 时间戳转换日期
                    FeedBackDetailEntity item = new FeedBackDetailEntity(students.get(i).getString("content"),sd,students.get(i).getObjectId(),getStringFromSp("id"));
                    data.add(item);
                }
                LinearLayoutManager manager = new LinearLayoutManager(mContext);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                view.setLayoutManager(manager);
                adapter = new FeedbackHistoryDetailAdapter(mContext,data);
                view.setAdapter(adapter);
            }
            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });
    }
}