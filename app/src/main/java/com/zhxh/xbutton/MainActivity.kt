package com.zhxh.xbutton

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

import com.zhxh.xbutton.dummy.PostTestEvent
import com.zhxh.xbuttonlib.XButton

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    private lateinit var postText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<XButton>(R.id.XButton1)
        val button2 = findViewById<XButton>(R.id.XButton2)
        val button4 = findViewById<XButton>(R.id.XButton4)

        button1.setOnClickListener { v ->
            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show()
            button1.setTextColor(-0x100)
            button1.solidColor = -0x10000
        }

        button2.setOnClickListener { v ->
            startActivity(Intent(this@MainActivity, TabHomeActivity::class.java))
            startActivity(Intent(this@MainActivity, PostActivity::class.java))
        }

        button4.setAnimDrawable(R.drawable.like_bg_start, R.drawable.like_bg_end) {
            button4.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.C12))
            button4.text = button4.text.toString() + "后"
        }


        val btnRegister = findViewById<XButton>(R.id.btnRegister)
        val btnUnRegister = findViewById<XButton>(R.id.btnUnRegister)
        postText = findViewById(R.id.postText)

        btnRegister.setOnClickListener { v ->
            //不能重复订阅
            EventBus.getDefault().register(this@MainActivity)
        }

        btnUnRegister.setOnClickListener { v -> EventBus.getDefault().unregister(this@MainActivity) }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPostTest(event: PostTestEvent) {
        postText.append("*" + event.testMsg!!)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onPostStickyTest(event: PostTestEvent) {
        postText.append("#" + event.testMsg!!)
    }
}
