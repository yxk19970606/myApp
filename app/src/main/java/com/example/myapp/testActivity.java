package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.content.Intent;
import android.widget.TextView;

import com.example.myapp.activity.BaseActivity;
import com.example.myapp.fragment.setting.FeedBackActivity;

public class testActivity extends BaseActivity {
    private WebView webView;
    private TextView title;
    private TextView back;
    private TextView historyFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = (WebView) findViewById(R.id.web_view);
        //调用setJavaScriptEnabled()方法让WebView支持JS脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //跳转网页仍然在当前WebView中显示
        //webView.setWebViewClient(new WebViewClient());
        //调用loadUrl()方法，并传入网址，展示相应内容
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.web_view);
        //调用setJavaScriptEnabled()方法让WebView支持JS脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //跳转网页仍然在当前WebView中显示
        //webView.setWebViewClient(new WebViewClient());
        //调用loadUrl()方法，并传入网址，展示相应内容
        title = findViewById(R.id.tv_question_detail_title);
        back = findViewById(R.id.tv_back_to_setting);
        historyFeedBack = findViewById(R.id.tv_feed_back_history);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String str = intent.getStringExtra("title");
        title.setText(str);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        historyFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                navigateTo(FeedBackActivity.class);
            }
        });
        webView.loadUrl(url);
    }
}
