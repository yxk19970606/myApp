package com.example.myapp.fragment.help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.adapter.QuestionListAdapter;
import com.example.myapp.adapter.QuestionListShowAdapter;
import com.example.myapp.entity.QuestionEntity;
import com.example.myapp.testActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchHelpItemsActivity extends BaseActivity {
    private EditText search;
    private List<QuestionEntity> datas;
    private TextView back;
    private ListView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_search_help_items;
    }

    @Override
    protected void initView() {
        search = findViewById(R.id.et_search);
        datas = (List<QuestionEntity>) getIntent().getSerializableExtra("datas");
        back = findViewById(R.id.tv_back_to_helping);
        view = findViewById(R.id.lv_question_item);
    }

    @Override
    protected void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    String str = search.getText().toString();
                    List<QuestionEntity> tempData = new ArrayList<QuestionEntity>();
                    for(int i=0;i<datas.size();i++){
                        if(datas.get(i).getTitle().contains(str)){
                            tempData.add(datas.get(i));
                        }
                    }
                    if(tempData.size()==0){
                        showToast("没有匹配到内容:"+datas.size());
                    }
                    QuestionListShowAdapter adapter = new QuestionListShowAdapter(mContext,0,tempData);
                    adapter.setOnItemClickListener(new QuestionListShowAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(mContext, testActivity.class);
                            intent.putExtra("url","file:///android_asset/"+datas.get(position).getObjectId()+".html");
                            intent.putExtra("title",datas.get(position).getTitle());
                            startActivity(intent);
                        }
                    });

                    view.setAdapter(adapter);
                    return true;
                }
                return false;
            }
        });
        search.requestFocus();
    }
}