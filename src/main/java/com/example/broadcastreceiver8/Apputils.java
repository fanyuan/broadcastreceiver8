package com.example.broadcastreceiver8;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.widget.Toast;

public class Apputils {
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 只有在调度模式时打印日志
     * @param context
     * @param msg
     */
    public static void log(Context context,String msg){
        if(isApkDebugable(context)){
            Log.d("ddebug",msg);
        }
    }
    public static void log(String msg){
        Log.d("ddebug",msg);
    }
    public static void toast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
