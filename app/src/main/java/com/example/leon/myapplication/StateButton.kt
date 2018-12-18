package com.example.leon.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.Button

/**
 *   description:
 *   created by crx on 2018/12/13 10:47
 */

class StateButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int = android.R.attr.buttonStyle)
    : Button(context, attributeSet, defStyleAttr){

    private var start: Boolean = false
    private var originalText = text.toString()

    fun setStart(start: Boolean){
        this.start = start
        text = if(start) "STOP $originalText" else originalText
    }

    fun hasStart() = start
}