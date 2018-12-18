package com.example.leon.myapplication.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 *   description: 简单的service例子
 *   created by crx on 2018/12/13 10:36
 */

class SimpleService : Service(){
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.e("SimpleService", "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SimpleService", "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("SimpleService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

}