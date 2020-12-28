package com.example.broadcastreceiver8

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.broadcastreceiver8.broadcast.MyBroadCastReceiver
import com.example.broadcastreceiver8.broadcast.StaticBroadCastReceiver

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActionBarCustomViewBuilder(this)
                .withTitle("提现")
                .withThemeColor(ContextCompat.getColor(this, R.color.white))
                .withBackgroundColor(ContextCompat.getColor(this, R.color.transparent))
                .withRightItem(-1, "说明", { _ -> showInstruction() })
                .buildAndAttachToActionBar()
        init()
        //Apputils.log(StaticBroadCastReceiver::class.qualifiedName)
    }

    private fun showInstruction() {
    }

    private fun init() {
        val filter = IntentFilter()
        filter.addAction("broad_cast")
        registerReceiver(MyBroadCastReceiver(),filter)
    }

    fun send(v: View){
        val broadCast = Intent("broad_cast")
        sendBroadcast(broadCast)
    }
    fun sendStatic(v:View){
        val static = Intent("static_broad_cast")
        static.setComponent(ComponentName(packageName,packageName+".broadcast.StaticBroadCastReceiver"))
        sendBroadcast(static)
    }


}