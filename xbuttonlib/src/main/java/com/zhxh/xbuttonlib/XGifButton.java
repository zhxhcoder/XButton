package com.zhxh.xbuttonlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.io.IOException;

import pl.droidsonroids.gif.GifTextView;

/**
 * Created by zhxh on 2018/6/29
 */
public final class XGifButton extends GifTextView {

    XGifDrawable gifDrawable;
    boolean isAnimComplete;
    int beforeTextColor;
    int afterTextColor;

    public XGifButton(Context context) {
        super(context);
    }

    public XGifButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XGifButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public XGifButton(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
    }


    public XGifButton bindGifSource(int rGif) {

        try {
            gifDrawable = new XGifDrawable(getResources(), rGif);
            return this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public XGifButton bindBeforeTextColor(int beforeTextColor) {

        this.beforeTextColor = beforeTextColor;

        return this;
    }

    public XGifButton bindAfterTextColor(int afterTextColor) {

        this.afterTextColor = afterTextColor;

        return this;
    }

    public void show() {
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public XGifDrawable getGifDrawable() {
        return gifDrawable;
    }

    public void setIsAnimComplete(boolean isAnimComplete) {

        this.isAnimComplete = isAnimComplete;

        if (isAnimComplete) {
            setLastFrame();
        } else {
            setFirstFrame();
        }
    }

    public void setFirstFrame() {
        isAnimComplete = false;
        Drawable drawable = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(0));
        this.setBackground(drawable);
        this.setTextColor(beforeTextColor);
    }

    public void setLastFrame() {
        isAnimComplete = true;
        gifDrawable.stop();
        Drawable drawable1 = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(gifDrawable.getNumberOfFrames() - 1));
        this.setBackground(drawable1);
        this.setClickable(false);
        this.setTextColor(afterTextColor);
    }


}