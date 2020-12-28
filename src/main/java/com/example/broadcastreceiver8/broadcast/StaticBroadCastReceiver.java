package com.example.broadcastreceiver8.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.broadcastreceiver8.Apputils;

public class StaticBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "收到静态态广播" + StaticBroadCastReceiver.class.getName();
        Apputils.log(msg);
        Apputils.toast(context, msg);

    }
}
