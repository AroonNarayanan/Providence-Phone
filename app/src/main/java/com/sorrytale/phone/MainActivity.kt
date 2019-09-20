package com.sorrytale.phone

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#00CA08")
        if (intent.getStringExtra("caller") != null && intent.getStringExtra("caller") != "") {
            findViewById<TextView>(R.id.callerName).text = intent.getStringExtra("caller")
            findViewById<ImageView>(R.id.endCall).setOnClickListener {
//                Intent(Intent.ACTION_MAIN).apply {
//                    addCategory(Intent.CATEGORY_HOME)
//                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                }.also {
//                    startActivity(it)
//                }
                finish()
            }
        }
        val callTime = findViewById<TextView>(R.id.callTime)
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            callTime.text = "00:00"
            object : CountDownTimer(600000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    val time = (600000 - millisUntilFinished) / 1000
                    callTime.text =
                        "${stringifyTime(getMinutes(time))}:${stringifyTime(getSeconds(time))}"
                }

                override fun onFinish() {

                }
            }.start()
        }, 3000)

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            Intent().apply {
                component =
                    ComponentName("com.sorrytale.amazon", "com.sorrytale.amazon.Notifier")
                action = "com.sorrytale.amazon.action.NOTIFY"
            }.also {
                startForegroundService(it)
            }
        }, 5000)
    }

    fun stringifyTime(time: Long): String {
        if (time >= 10) {
            return time.toString()
        } else {
            return "0$time"
        }
    }

    fun getSeconds(time: Long): Long {
        return time % 60
    }

    fun getMinutes(time: Long): Long {
        return (time - (time % 60)) / 60
    }
}
