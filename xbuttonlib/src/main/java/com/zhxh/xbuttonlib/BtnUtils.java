package com.zhxh.xbuttonlib;

/**
 * Created by zhxh on 2018/7/25
 */
public class BtnUtils {

    private static long lastClickTime;
    public synchronized static boolean isQuickClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
