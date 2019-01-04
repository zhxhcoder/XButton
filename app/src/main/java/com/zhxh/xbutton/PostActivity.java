package com.zhxh.xbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhxh.xbutton.dummy.PostTestEvent;
import com.zhxh.xbuttonlib.XButton;

import org.greenrobot.eventbus.EventBus;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        XButton btnPost = findViewById(R.id.btnPost);
        XButton btnPostSticky = findViewById(R.id.btnPostSticky);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PostTestEvent("common"));
            }
        });
        btnPostSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PostTestEvent("sticky"));
            }
        });

    }
}
