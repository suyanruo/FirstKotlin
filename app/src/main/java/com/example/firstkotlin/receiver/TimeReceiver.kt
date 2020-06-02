package com.example.firstkotlin.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.firstkotlin.util.DateUtil

class TimeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent != null) {
            Log.e("TimeReceiver", DateUtil.nowDateTime + " 收到广播消息")
        }
    }
}
