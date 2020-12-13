package com.example.myapp.fragment.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import java.util.Calendar;
import android.app.DatePickerDialog;

import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.fragment.myfragment.SettingActivity;

import org.w3c.dom.Text;

import cn.leancloud.AVObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {
    private TextView backToSetting;
    private TextView historyFeedBack;
    private EditText feedBackmessage;
    private View questionHappenTime;
    private TextView questionHappentTimeShow;
    private Button feedback;
    private int mYear;
    private int mMonth;
    private int mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView() {
        backToSetting = findViewById(R.id.tv_back_to_setting);
        historyFeedBack = findViewById(R.id.tv_feed_back_history);
        feedBackmessage = findViewById(R.id.et_feed_back_message);
        questionHappenTime = findViewById(R.id.layout_question_happen_time);
        feedback = findViewById(R.id.btn_feedback);
        questionHappentTimeShow = findViewById(R.id.tv_question_happen_time);
    }
    @Override
    protected void initData() {
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        backToSetting.setOnClickListener(this);
        historyFeedBack.setOnClickListener(this);
        feedBackmessage.requestFocus();
        questionHappentTimeShow.setText(String.valueOf(mYear)+"-"+String.valueOf(mMonth+1)+"-"+String.valueOf(mDay));
        questionHappenTime.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class cls = null;
        switch(v.getId()){
            case R.id.tv_back_to_setting:
                cls = SettingActivity.class;
                break;
            case R.id.tv_feed_back_history:
                cls = FeedbackHistoryInfoActivity.class;
                break;
            case R.id.layout_question_happen_time:
                new DatePickerDialog(mContext, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.btn_feedback:
                saveFeedbackInfo();
                break;
        }
        if (cls!=null){
            navigateTo(cls);
        }
    }

       public void saveFeedbackInfo(){
          if(feedBackmessage.getText().toString().length()==0){
              showToast("请填写数据后提交");
          }else{
              //保存成功以后可以跳转到一个新的页面
              AVObject todo = new AVObject("feedback");
              todo.put("feedContent",feedBackmessage.getText().toString());
              todo.put("id",Integer.valueOf(getStringFromSp("id")));
              todo.put("happenTime",questionHappentTimeShow.getText().toString());
              todo.saveInBackground().subscribe(new Observer<AVObject>() {
                  public void onSubscribe(Disposable disposable) {}
                  public void onNext(AVObject todo) {
                  }
                  public void onError(Throwable throwable) {
                      // 异常处理
                      System.out.println(throwable);
                      showToast("请检查你的网络连接");
                  }
                  public void onComplete() {
                      //跳转到提示保存成功页面
                      navigateTo(FeedbackSuccessfulActivity.class);
                      showToast("保存成功");
                  }
              });

          }
       }
       //日期选择的回调函数
       private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   if (mYear<year||(mYear==year && mMonth<month) ||(mYear==year&&mMonth==month&&mDay<dayOfMonth)){
                       showToast("日期选择有误请重新选择");
                       return;
                   }
                   String days;
                   if (month + 1 < 10) {
                       if (dayOfMonth < 10) {
                           days = new StringBuffer().append(year).append("年").append("0").
                                   append(month + 1).append("月").append("0").append(dayOfMonth).append("日").toString();
                       } else {
                           days = new StringBuffer().append(year).append("年").append("0").
                                   append(month + 1).append("月").append(dayOfMonth).append("日").toString();
                       }

                   } else {
                       if (mDay < 10) {
                           days = new StringBuffer().append(year).append("年").
                                   append(month + 1).append("月").append("0").append(dayOfMonth).append("日").toString();
                       } else {
                           days = new StringBuffer().append(year).append("年").
                                   append(month + 1).append("月").append(dayOfMonth).append("日").toString();
                       }
                   }
                   days=days.replace("年","-");
                   days=days.replace("月","-");
                   days=days.replace("日","");
                   questionHappentTimeShow.setText(days);
               }
       };
}