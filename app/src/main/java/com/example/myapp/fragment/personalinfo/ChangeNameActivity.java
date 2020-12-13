package com.example.myapp.fragment.personalinfo;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;
import com.example.myapp.activity.BaseActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import cn.leancloud.AVObject;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ChangeNameActivity extends BaseActivity {
    private TextView back;
    private TextView finish;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_change_name;
    }
    @Override
    protected void initView() {
        back = findViewById(R.id.back_to_person_info);
        finish = findViewById(R.id.change_name_finish);
        text = findViewById(R.id.et_name);
    }
    @Override
    protected void initData() {
        text.setText(getStringFromSp("nickName"));
        text.requestFocus();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = text.getText().toString();
              saveStringToSp("nickName",str);
                AVObject todo = AVObject.createWithoutData("android",getStringFromSp("objectId"));
                todo.put("nickName",str);
                todo.saveInBackground().subscribe(new Observer<AVObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        showToast("昵称更改成功");
                    }

                    @Override
                    public void onNext(@NonNull AVObject avObject) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
              navigateTo(PersonalInfoActivity.class);
            }
        });

    }
}