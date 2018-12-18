package com.example.leon.myapplication.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import com.example.leon.myapplication.MSG_FROM_SERVER
import com.example.leon.myapplication.MSG_SAY_HELLO
import com.example.leon.myapplication.MyObj
import java.util.*
import kotlin.concurrent.timer

/**
 *   description: 使用messenger与其它组件通信的服务
 *   created by crx on 2018/12/13 15:27
 */
class MessengerService : Service() {

    private val messenger: Messenger = Messenger(MyHandler())
    lateinit var clientMessenger: Messenger
    lateinit var myTimer: Timer

    inner class MyHandler: Handler(){

        override fun handleMessage(msg: Message?) {
            when(msg?.what){
                MSG_SAY_HELLO -> {
                    val bundle = msg.data
                    bundle.classLoader = MessengerService::class.java.classLoader
                    Log.e("MessengerService ${msg.arg1}", "hello ${bundle.getParcelable<MyObj>("obj")}")
                    clientMessenger = msg.replyTo
                    startTimeTask()
                }
                else -> super.handleMessage(msg)
            }

        }
    }

    private fun startTimeTask(){
        myTimer = timer("hh",false, 1000, 1000) {
            clientMessenger.send(Message.obtain(null, MSG_FROM_SERVER, 111, 222, MyObj("hh", "jj")))
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("MessengerService", "onBind")
        return messenger.binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("MessengerService", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("MessengerService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("MessengerService", "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MessengerService", "onDestroy")
        myTimer.cancel()

    }
}