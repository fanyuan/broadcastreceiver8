package com.example.broadcastreceiver8.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.broadcastreceiver8.Apputils

class MyBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val msg = "收到动态广播"
        Apputils.log(msg)
        Apputils.toast(context, msg)
    }

}