package com.zhxh.xbutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zhxh.xbuttonlib.XButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XButton button1 = findViewById(R.id.XButton1);
        XButton button2 = findViewById(R.id.XButton2);
        XButton button4 = findViewById(R.id.XButton4);

        button1.setOnClickListener(v -> {
            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show();
            button1.setTextColor(0xffffff00);
            button1.setSolidColor(0xffff0000);
        });


        button2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TabHomeActivity.class)));

        button4.setAnimDrawable(R.drawable.like_bg_start, R.drawable.like_bg_end, () -> {
            button4.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.C12));
            button4.setText(button4.getText().toString()+"后");
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}
