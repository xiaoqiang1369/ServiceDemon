package com.example.leon.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.leon.myapplication.services.BgService
import com.example.leon.myapplication.services.BindService
import com.example.leon.myapplication.services.MessengerService
import com.example.leon.myapplication.services.SimpleService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var myBinder: BindService.MyBind
    private lateinit var messenger: Messenger

    private val simpleServiceIntent: Intent by lazy { Intent(this, SimpleService::class.java) }
    private val bindServiceIntent: Intent by lazy { Intent(this, BindService::class.java) }
    private val intentServiceIntent: Intent by lazy { Intent(this, BgService::class.java).putExtra("url", "www.baidu.com") }
    private val messengerServiceIntent: Intent by lazy { Intent(this, MessengerService::class.java) }

    private val btnSimpleService: StateButton by lazy(LazyThreadSafetyMode.NONE) { btn_simple_service }
    private val btnBindService: StateButton by lazy(LazyThreadSafetyMode.NONE) { btn_bind_service }
    private val btnIntentService: StateButton by lazy(LazyThreadSafetyMode.NONE) { btn_intent_service }
    private val btnMessengerService: StateButton by lazy(LazyThreadSafetyMode.NONE) { btn_messenger }

    private val bindConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            myBinder = binder as BindService.MyBind
            Log.e(TAG, "onServiceConnected")
            myBinder.doSomething()
            val resultNum = myBinder.addNumbers(1, 11)
            Log.e(TAG, "result = $resultNum")
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.e(TAG, "onServiceDisconnected")
        }
    }

    private val messengerConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            Log.e(TAG, "onServiceConnected")
            messenger = Messenger(binder)
            val message = Message.obtain(null, MSG_SAY_HELLO, 1, 2, null)
            message.data.putParcelable("obj", MyObj())
            message.replyTo = clientMessenger
            try {
                messenger.send(message)
            }catch (exception: Exception){
                exception.printStackTrace()
            }

        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.e(TAG, "onServiceDisconnected")
        }
    }

    private val clientMessenger = Messenger(handler)

    private object handler: Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what){
                MSG_FROM_SERVER ->{
                    Log.e("MainActivity", "arg1 = ${msg.arg1}, obj = ${msg.obj}")
                }

                else -> super.handleMessage(msg)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
    }

    private fun initButtons(){
        //simple service
        btnSimpleService.setOnClickListener {
            if(btnSimpleService.hasStart()){
                stopService(simpleServiceIntent)
                btnSimpleService.setStart(false)
            }else{
                startService(simpleServiceIntent)
                btnSimpleService.setStart(true)
            }
        }
        //bind service
        btnBindService.setOnClickListener {
            if(btnBindService.hasStart()){
                unbindService(bindConnection)
                btnBindService.setStart(false)
            }else{
                bindService(bindServiceIntent, bindConnection, Context.BIND_AUTO_CREATE)
                btnSimpleService.setStart(true)
            }
        }
        //intend service
        btnIntentService.setOnClickListener {
            if(btnIntentService.hasStart()){
                stopService(intentServiceIntent)
                btnIntentService.setStart(false)
            }else{
                startService(intentServiceIntent)
                btnIntentService.setStart(true)
            }
        }
        //messenger service
        btnMessengerService.setOnClickListener {
            if(btnMessengerService.hasStart()){
                unbindService(messengerConnection)
                btnMessengerService.setStart(false)
            }else{
                bindService(messengerServiceIntent, messengerConnection, Context.BIND_AUTO_CREATE)
                btnMessengerService.setStart(true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
