package com.example.leon.myapplication.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 *   description: 简单的bind service例子
 *   created by crx on 2018/12/13 10:36
 */
class BindService : Service(){

    private val myBinder = MyBind()

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("BindService", "onBind")
        return myBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("BindService", "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("BindService", "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("BindService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    class MyBind : Binder(){

        fun doSomething(){
            Log.e("BindService", "MyBind is doing something now, Don't interrupt him.")
        }

        fun addNumbers(num1: Int, num2: Int): Int{
            Log.e("BindService", "MyBind is doing something else.")
            return num1.plus(num2)
        }
    }

}