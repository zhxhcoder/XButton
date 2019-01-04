package com.zhxh.xbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhxh.xbuttonlib.XButton;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        XButton btnPost = findViewById(R.id.btnPost);
        XButton btnPostSticky = findViewById(R.id.btnPostSticky);




    }
}
