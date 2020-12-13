package com.example.myapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.adapter.VideoAdapter;
import com.example.myapp.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;


public class VideoFragment extends Fragment {
    private String title;
    public VideoFragment() {
    }
    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        fragment.title = title;
        return fragment;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_video, container, false);
//        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((getActivity()));
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        List<VideoEntity> datas = new ArrayList<VideoEntity>();
//        for (int i = 0; i< 8; i++){
//            VideoEntity ve = new VideoEntity();
//            ve.setTitle("士大夫撒旦");
//            ve.setName("士大夫撒旦");
//            ve.setDzCount(100);
//            ve.setCommentCount(300);
//            ve.setCollectionCount(1000);
//            datas.add(ve);
//        }
//        VideoAdapter videoAdapter = new VideoAdapter(getActivity(),datas);
//        recyclerView.setAdapter(videoAdapter);
        return v;
    }
}