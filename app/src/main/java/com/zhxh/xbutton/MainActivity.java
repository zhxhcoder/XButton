package com.zhxh.xbutton;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhxh.xbuttonlib.XButton;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XButton button1 = findViewById(R.id.XButton1);
        XButton button2 = findViewById(R.id.XButton2);
        XButton button4 = findViewById(R.id.XButton4);
        GifTextView gifText = findViewById(R.id.gifText);

        gifText.setBackgroundResource(R.drawable.like_bg_anim);

        GifDrawable gifDrawable = ((GifDrawable) gifText.getBackground());

        gifDrawable.seekToFrameAndGet(0);
        Drawable drawable = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(1));
        gifText.setBackground(drawable);

        gifDrawable.addAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationCompleted(int loopNumber) {
                Log.d("zhxhLog", "loopNumber " + loopNumber);
                Drawable drawable = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(gifDrawable.getNumberOfFrames() - 1));
                gifText.setBackground(drawable);
                gifText.setClickable(false);
            }
        });
        gifText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifText.setBackgroundResource(R.drawable.like_bg_anim);
                gifDrawable.setLoopCount(1);
                gifDrawable.start();
            }
        });


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
