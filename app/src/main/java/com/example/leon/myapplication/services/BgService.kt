package com.example.leon.myapplication.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 *   description: 用IntentService创建一个后台运行的服务
 *   created by crx on 2018/12/13 10:36
 */
class BgService : IntentService(BgService::class.java.simpleName){

    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("url")
        Log.e("BgService", "url = $url")
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("BgService", "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("BgService", "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("BgService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

}