package com.zhxh.xbutton;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zhxh.xbuttonlib.XButton;
import com.zhxh.xbuttonlib.XGifButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XButton button1 = findViewById(R.id.XButton1);
        XButton button2 = findViewById(R.id.XButton2);
        XButton button4 = findViewById(R.id.XButton4);
        XGifButton gifButton = findViewById(R.id.gifText);

        gifButton.bindGifSource(R.drawable.like_bg_anim)
                .bindBeforeTextColor(Color.parseColor("#ffffff"))
                .bindAfterTextColor(Color.parseColor("#ff4c51"));

        gifButton.setIsAnimComplete(false);

        gifButton.setOnClickListener(v -> {

            if (!gifButton.isAnimComplete()) {
                gifButton.getGifDrawable().addAnimationListener(loopNumber -> {

                    gifButton.setText(String.valueOf(Integer.parseInt(gifButton.getText().toString()) + 1));
                    gifButton.setIsAnimComplete(true);
                });

                gifButton.bindGifSource(R.drawable.like_bg_anim);
            }

        });


        /* try {
            XGifDrawable gifDrawable = new XGifDrawable(getResources(), R.drawable.like_bg_anim);

            Drawable drawable = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(0));
            gifButton.setBackground(drawable);
            gifButton.setTextColor(Color.parseColor("#ffffff"));

            gifButton.setOnClickListener(v -> {

                gifDrawable.addAnimationListener(loopNumber -> {
                    gifDrawable.stop();
                    Drawable drawable1 = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(gifDrawable.getNumberOfFrames() - 1));
                    gifButton.setBackground(drawable1);
                    gifButton.setTextColor(Color.parseColor("#ff4c51"));
                    gifButton.setText(String.valueOf(Integer.parseInt(gifButton.getText().toString()) + 1));
                    gifButton.setClickable(false);
                });

                gifButton.setBackground(gifDrawable);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        button1.setOnClickListener(v -> {
            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show();
            button1.setSolidColor(0xffff0000);
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
