package com.zhxh.android.xbuttonlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

public class XButton extends AppCompatButton {

    GradientDrawable gradientDrawable;

    private int pressedColor = Color.TRANSPARENT;
    private int defaultColor = Color.TRANSPARENT;
    private int solidColor = Color.TRANSPARENT;
    private int strokeColor = Color.TRANSPARENT;

    private int angleCorner = 0;
    private int strokeWidth = 0;

    boolean isTouchPass = true;

    public XButton(Context context) {
        this(context, null);
    }

    public XButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XButton);
        defaultColor = a.getColor(R.styleable.XButton_XdefaultColor, defaultColor);
        pressedColor = a.getColor(R.styleable.XButton_XpressedColor, pressedColor);
        solidColor = a.getColor(R.styleable.XButton_XsolidColor, solidColor);
        strokeColor = a.getColor(R.styleable.XButton_XstrokeColor, strokeColor);
        angleCorner = a.getDimensionPixelSize(R.styleable.XButton_XangleCorner, angleCorner);
        strokeWidth = a.getDimensionPixelSize(R.styleable.XButton_XstrokeWidth, strokeWidth);

        setGravity(Gravity.CENTER);
        gradientDrawable = new GradientDrawable();

        setBtnDrawable();

        //设置按钮点击之后的颜色更换
        setOnTouchListener((arg0, event) -> {
            setBackgroundDrawable(gradientDrawable);
            return setColor(event.getAction());
        });
    }

    private void setBtnDrawable() {
        //设置按钮颜色
        gradientDrawable.setColor(defaultColor);
        //设置按钮的边框宽度
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        //设置按钮圆角大小
        gradientDrawable.setCornerRadius(angleCorner);
        setBackgroundDrawable(gradientDrawable);
    }

    //处理按钮点击事件无效
    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        isTouchPass = false;
    }

    //处理按下去的颜色
    public boolean setColor(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                gradientDrawable.setColor(pressedColor);
                break;
            case MotionEvent.ACTION_UP:
                gradientDrawable.setColor(defaultColor);
                break;
            case MotionEvent.ACTION_CANCEL:
                gradientDrawable.setColor(defaultColor);
                break;
        }

        return isTouchPass;
    }

    /**
     * 对外定义接口
     **/
    public void setPressedColor(int pressedColor) {
        this.pressedColor = pressedColor;
        setBtnDrawable();
    }
    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        setBtnDrawable();
    }
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        setBtnDrawable();
    }
}
