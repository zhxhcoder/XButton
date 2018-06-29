package com.zhxh.xbutton;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zhxh.xbuttonlib.XButton;
import com.zhxh.xbuttonlib.XGifButton;
import com.zhxh.xbuttonlib.XGifDrawable;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XButton button1 = findViewById(R.id.XButton1);
        XButton button2 = findViewById(R.id.XButton2);
        XButton button4 = findViewById(R.id.XButton4);
        XGifButton gifText = findViewById(R.id.gifText);

        try {
            XGifDrawable gifDrawable = new XGifDrawable(getResources(), R.drawable.like_bg_anim);

            gifDrawable.seekToFrameAndGet(0);
            Drawable drawable = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(0));
            gifText.setBackground(drawable);
            gifText.setTextColor(Color.parseColor("#ffffff"));

            gifText.setOnClickListener(v -> {

                gifDrawable.addAnimationListener(loopNumber -> {
                    gifDrawable.stop();
                    Drawable drawable1 = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(gifDrawable.getNumberOfFrames() - 1));
                    gifText.setBackground(drawable1);
                    gifText.setTextColor(Color.parseColor("#ff4c51"));
                    gifText.setText(String.valueOf(Integer.parseInt(gifText.getText().toString()) + 1));
                    gifText.setClickable(false);
                });

                gifText.setBackground(gifDrawable);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        button1.setOnClickListener(v -> {
            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show();
            button1.setDefaultColor(0xffff0000);
        });
        button2.setOnClickListener(v -> Toast.makeText(this, "button2", Toast.LENGTH_LONG).show());


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button4.requestLayout();
                button4.invalidate();
                handler.postDelayed(this, 15000);
            }
        }, 15000);

    }
}
