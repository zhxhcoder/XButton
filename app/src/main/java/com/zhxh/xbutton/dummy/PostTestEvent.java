package com.zhxh.xbutton.dummy;

/**
 * Created by zhxh on 2019/1/4
 */
public class PostTestEvent {

    public PostTestEvent(String testMsg) {
        this.testMsg = testMsg;
    }

    private String testMsg;

    public String getTestMsg() {
        return testMsg;
    }

    public void setTestMsg(String testMsg) {
        this.testMsg = testMsg;
    }
}
