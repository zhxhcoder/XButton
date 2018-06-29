package com.zhxh.xbuttonlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.io.IOException;

import pl.droidsonroids.gif.GifTextView;

/**
 * Created by zhxh on 2018/6/29
 */
public final class XGifButton extends GifTextView {

    Context context;

    XGifDrawable gifDrawable;
    boolean isAnimComplete;
    int beforeTextColor;
    int afterTextColor;

    public XGifButton(Context context) {
        super(context);

        init(context);
    }

    public XGifButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XGifButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    public XGifButton bindGifSource(int rGif) {

        try {
            gifDrawable = new XGifDrawable(context.getResources(), rGif);
            this.setBackground(gifDrawable);
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
        this.setClickable(true);
        this.setTextColor(beforeTextColor);
    }

    public void setLastFrame() {
        isAnimComplete = true;
        gifDrawable.stop();
        Drawable drawable = new BitmapDrawable(getResources(), gifDrawable.seekToFrameAndGet(gifDrawable.getNumberOfFrames() - 1));
        this.setBackground(drawable);
        this.setClickable(false);
        this.setTextColor(afterTextColor);
    }


}
