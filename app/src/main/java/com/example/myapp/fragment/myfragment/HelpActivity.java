package com.example.myapp.fragment.myfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.adapter.FeedbackAdapter;
import com.example.myapp.adapter.QuestionListAdapter;
import com.example.myapp.entity.Feedback;
import com.example.myapp.entity.QuestionEntity;
import com.example.myapp.fragment.help.SearchHelpItemsActivity;
import com.example.myapp.fragment.setting.FeedBackHistoryDetailActivity;
import com.example.myapp.testActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HelpActivity extends BaseActivity {
    ListView guiding;
    ListView question;
    ListView view1;
    ListView view2;
    List<QuestionEntity> items;
    List<List<QuestionEntity>> datas;
    TextView backToSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_help;
    }

    @Override
    protected void initView() {
         view1 = findViewById(R.id.lv_for_guiding);
        // view2 = findViewById(R.id.lv_for_common_question);
         items = new ArrayList<QuestionEntity>();
         datas = new ArrayList<List<QuestionEntity>>();
         datas.add(new ArrayList<QuestionEntity>());
         datas.add(new ArrayList<QuestionEntity>());
         datas.add(new ArrayList<QuestionEntity>());
         datas.get(1).add(new QuestionEntity("新手引导",""));
         datas.get(2).add(new QuestionEntity("常见问题",""));
         backToSetting = findViewById(R.id.tv_back_to_setting);
    }
    @Override
    protected void initData() {
      List<QuestionEntity> data = new ArrayList<QuestionEntity>();
        AVQuery<AVObject> query = new AVQuery<>("questionlist");
        query.whereContainedIn("type", Arrays.asList(1,2));
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<AVObject> students) {
                System.out.println(students.size());
                items.add(new QuestionEntity("",""));
                items.add(new QuestionEntity("",""));
                items.add(new QuestionEntity("",""));
                List<QuestionEntity>  temporyItem = new ArrayList<QuestionEntity>();
                System.out.println("===========>");
                System.out.println(students.size());
                for (int i =0;i<students.size();i++){
                    QuestionEntity entity = new QuestionEntity(students.get(i).getString("title"),students.get(i).getString("url"));
                    datas.get(students.get(i).getInt("type")).add(entity);
                    temporyItem.add(entity);
                }
                QuestionListAdapter adapter1 = new QuestionListAdapter(mContext,0,items,datas);
                //QuestionListAdapter adapter2 = new QuestionListAdapter(mContext,0,datas2);
                //view2.setAdapter(adapter2);
                backToSetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                adapter1.setOnItemClickListener(new QuestionListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view,int position,int index) {
                        if(position==-1){
                            Intent intent = new Intent(mContext,SearchHelpItemsActivity.class);
                            intent.putExtra("datas",(Serializable)temporyItem);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(mContext,testActivity.class);
                            intent.putExtra("url","file:///android_asset/"+datas.get(position).get(index).getObjectId()+".html");
                            intent.putExtra("title",datas.get(position).get(index).getTitle());
                            startActivity(intent);
                        }

                      }
                });
                view1.setAdapter(adapter1);
            }
            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });

    }
}