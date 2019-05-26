package com.zhxh.xbutton

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.zhxh.xbutton.dummy.PostTestEvent
import com.zhxh.xbuttonlib.XButton

import org.greenrobot.eventbus.EventBus

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val btnPost = findViewById<XButton>(R.id.btnPost)
        val btnPostSticky = findViewById<XButton>(R.id.btnPostSticky)


        btnPost.setOnClickListener { EventBus.getDefault().post(PostTestEvent("common")) }
        btnPostSticky.setOnClickListener { EventBus.getDefault().post(PostTestEvent("sticky")) }

    }
}
