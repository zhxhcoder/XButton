package com.zhxh.xbuttonlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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
    private int drawablePadding = 0;
    private int drawableWidth;
    private DrawablePosition position;
    Rect bounds;

    private enum DrawablePosition {
        NONE,
        LEFT_AND_RIGHT,
        LEFT,
        RIGHT
    }

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
        drawablePadding = a.getDimensionPixelSize(R.styleable.XButton_XdrawablePadding, drawablePadding);

        if (null == bounds) {
            bounds = new Rect();
        }
        if (null == gradientDrawable) {
            gradientDrawable = new GradientDrawable();
        }

        setGravity(Gravity.CENTER);
        setDrawablePadding(drawablePadding);
        setBtnDrawable();

        //设置按钮点击之后的颜色更换
        setOnTouchListener((arg0, event) -> {
            setBackgroundDrawable(gradientDrawable);
            return setColor(event.getAction());
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Paint textPaint = getPaint();
        String text = getText().toString();
        textPaint.getTextBounds(text, 0, text.length(), bounds);

        int textWidth = bounds.width();
        int factor = (position == DrawablePosition.LEFT_AND_RIGHT) ? 2 : 1;
        int contentWidth = drawableWidth + drawablePadding * factor + textWidth;
        int horizontalPadding = (int) ((getWidth() / 2.0) - (contentWidth / 2.0));

        setCompoundDrawablePadding(-horizontalPadding + drawablePadding);

        switch (position) {
            case LEFT:
                setPadding(horizontalPadding, getPaddingTop(), 0, getPaddingBottom());
                break;

            case RIGHT:
                setPadding(0, getPaddingTop(), horizontalPadding, getPaddingBottom());
                break;

            case LEFT_AND_RIGHT:
                setPadding(horizontalPadding, getPaddingTop(), horizontalPadding, getPaddingBottom());
                break;

            default:
                setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        }
    }


    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        if (left != null && right != null) {
            drawableWidth = left.getIntrinsicWidth() + right.getIntrinsicWidth();
            position = DrawablePosition.LEFT_AND_RIGHT;
        } else if (left != null) {
            drawableWidth = left.getIntrinsicWidth();
            position = DrawablePosition.LEFT;
        } else if (right != null) {
            drawableWidth = right.getIntrinsicWidth();
            position = DrawablePosition.RIGHT;
        } else {
            position = DrawablePosition.NONE;
        }

        requestLayout();
    }

    public void setDrawablePadding(int padding) {
        drawablePadding = padding;
        requestLayout();
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
