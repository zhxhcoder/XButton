
# XButton介绍
[XButton取代各种shape文件](https://www.jianshu.com/p/40887388770e)，因为大家项目中基本都是用到的button都不需要变化大小，所以写了个简单的[XButton]

参考效果

![](https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.gif)

## 0.控件源码
先上代码：https://github.com/zhxhcoder/XButton
参考我的上篇文章：https://www.jianshu.com/p/c8f8818a7baf

## 1. 引用方法

通过Maven或Gradle引用
~~~
<dependency>
  <groupId>com.zhxh</groupId>
  <artifactId>xbuttonlib</artifactId>
  <version>3.9</version>
  <type>pom</type>
</dependency>
~~~

~~~
implementation 'com.zhxh:xbuttonlib:3.9'
~~~
## 2. 使用方法
~~~
    <com.zhxh.android.xbuttonlib.XButton
        android:id="@+id/XButton4"
        android:layout_width="100dp"
        android:layout_height="40dp"
        android:layout_marginLeft="112dp"
        android:layout_marginStart="112dp"
        android:layout_marginTop="204dp"
        android:text="圆角矩形 "
        android:textColor="@color/colorPrimary"
        app:XangleCorner="2dp"
        app:XstrokeColor="@color/colorPrimary"
        app:XstrokeWidth="1dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
~~~

实体的圆角矩形，需要配置三个字段
~~~
        app:XangleCorner="2dp"   //表示圆角
        app:XdefaultColor="@color/colorPrimary" //正常颜色
        app:XpressedColor="@color/colorPrimaryDark" //按压后的颜色
~~~

~~~
    <com.zhxh.android.xbuttonlib.XButton
        android:id="@+id/XButton5"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_marginLeft="140dp"
        android:layout_marginStart="140dp"
        android:layout_marginTop="276dp"
        android:text="圆形 "
        android:textColor="@color/colorPrimary"
        app:XangleCorner="45dp"
        app:XstrokeColor="@color/colorPrimary"
        app:XstrokeWidth="1dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

~~~
空心圆角矩形，需配置三个字段

~~~
        app:XangleCorner="45dp"    //表示圆角
        app:XstrokeColor="@color/colorPrimary" //表示边框颜色
        app:XstrokeWidth="1dp"               //表示边框宽度
~~~

## 3. 版本变更

##### V1.3版本加上了可以代码控制的功能

比如
~~~
    //对外定义接口
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
~~~
使用方法
~~~
            button1.setDefaultColor(0xffff0000);
~~~

##### V1.4版本加上了自定义的XdrawablePadding属性

原生的android:drawablePadding这个属性在 我们给view设置的宽度或者高度足够小（以至于将两者挤压在一起）的时候，这个属性才会起作用，也即在图片和文字之间会有间距产生。如果你的view所设置的宽度或者高度大于drawableLeft/drawableRight或者drawableTop/drawableBottom所产生的间距，那么这个属性当然也就不会起作用。

可以通过自定义View来精确的计算：

我们先自定义属性drawablePadding来设置间距，并提供方法给外部调用
重写setCompoundDrawablesWithIntrinsicBounds()方法来获取我们设置的drawable宽度。
最后重写onLayout方法，因为这里面改变了一些位置属性，需要通过重新布局才能起作用。


~~~
    <com.zhxh.xbuttonlib.XButton
        android:id="@+id/XButton6"
        android:layout_width="322dp"
        android:layout_height="51dp"
        android:layout_marginEnd="28dp"
        android:layout_marginRight="28dp"
        android:layout_marginTop="352dp"
        android:drawableRight="@drawable/ic_arrow"
        android:gravity="center"
        android:text="圆角矩形"
        android:textColor="@android:color/white"
        app:XangleCorner="2dp"
        app:XdefaultColor="@color/colorPrimary"
        app:XdrawablePadding="10dp"
        app:XpressedColor="@color/colorPrimaryDark"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
~~~

在这里加入新的配置XdrawablePadding
~~~
        app:XdrawablePadding="10dp"
~~~

##### V2.1版本加上了动画设置

~~~
        app:XisShaderAnim="true"

~~~

##### V2.8版本删除了defaultColor 并添加了 pressTextColor 以及方便地从 solid型到stroke型button的相互转换

~~~
        button1.setOnClickListener(v -> {
            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show();
            button1.setStrokeAttr(0xffff0000, 2);
        });

~~~

~~~
    <com.zhxh.xbuttonlib.XButton
        android:id="@+id/XButton5"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_marginLeft="140dp"
        android:layout_marginStart="140dp"
        android:layout_marginTop="276dp"
        android:gravity="center"
        android:text="圆形"
        android:textColor="@color/colorPrimary"
        app:XangleCorner="45dp"
        app:XpressedColor="@color/colorPrimaryDark"
        app:XpressedTextColor="@android:color/white"
        app:XstrokeColor="@color/colorPrimary"
        app:XstrokeWidth="1dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
~~~

~~~
        android:textColor="@color/colorPrimary"

        app:XpressedColor="@color/colorPrimaryDark"

~~~

代码实现

~~~
    //处理按下去的颜色 区分solid和stroke模式
    public boolean setColor(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                gradientDrawable.setColor(pressedColor);
                this.setTextColor(pressedTextColor);
                break;
            case MotionEvent.ACTION_UP:
                gradientDrawable.setColor(solidColor);
                this.setTextColor(defaultTextColor);
                break;
            case MotionEvent.ACTION_CANCEL:
                gradientDrawable.setColor(solidColor);
                this.setTextColor(defaultTextColor);
                break;
        }

        return isTouchPass;
    }

~~~

## 4. 关键代码分析

```
    GradientDrawable gradientDrawable;
```

GradientDrawable可以在res/drawable目录下以xml文件用<shape>标签来定义。看看官方文档给出的xml定义说明吧。
```
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape=["rectangle" | "oval" | "line" | "ring"] >
    <corners
        android:radius="integer"
        android:topLeftRadius="integer"
        android:topRightRadius="integer"
        android:bottomLeftRadius="integer"
        android:bottomRightRadius="integer" />
    <gradient
        android:angle="integer"
        android:centerX="integer"
        android:centerY="integer"
        android:centerColor="integer"
        android:endColor="color"
        android:gradientRadius="integer"
        android:startColor="color"
        android:type=["linear" | "radial" | "sweep"]
        android:useLevel=["true" | "false"] />
    <padding
        android:left="integer"
        android:top="integer"
        android:right="integer"
        android:bottom="integer" />
    <size
        android:width="integer"
        android:height="integer" />
    <solid
        android:color="color" />
    <stroke
        android:width="integer"
        android:color="color"
        android:dashWidth="integer"
        android:dashGap="integer" />
</shape>

```
```
    private void setBtnDrawable() {
        //设置按钮颜色
        gradientDrawable.setColor(solidColor);
        //设置按钮的边框宽度
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        //设置按钮圆角大小
        gradientDrawable.setCornerRadius(angleCorner);
        setBackgroundDrawable(gradientDrawable);
        ...
    }

```
自定义GradientDrawable,并根据属性自定义，以取代shape文件



原生的android:drawablePadding这个属性在 我们给view设置的宽度或者高度足够小（以至于将两者挤压在一起）的时候，这个属性才会起作用，也即在图片和文字之间会有间距产生。如果你的view所设置的宽度或者高度大于drawableLeft/drawableRight或者drawableTop/drawableBottom所产生的间距，那么这个属性当然也就不会起作用。

可以通过自定义View来精确的计算：

我们先自定义属性drawablePadding来设置间距，并提供方法给外部调用
重写**setCompoundDrawablesWithIntrinsicBounds()**方法来获取我们设置的drawable宽度。
最后重写**onLayout**方法，因为这里面改变了一些位置属性，需要通过重新布局才能起作用。

```

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


    //重新设置位置
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
```





## 开源协议

 > Copyright (C) 2018, zhxh
 >
 > Licensed under the Apache License, Version 2.0 (the "License");
 > you may not use this file except in compliance with the License.
 > You may obtain a copy of the License at
 >
 > http://www.apache.org/licenses/LICENSE-2.0
 >
 > Unless required by applicable law or agreed to in writing, software
 > distributed under the License is distributed on an "AS IS" BASIS,
 > WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 > See the License for the specific language governing permissions and
 > limitations under the License.