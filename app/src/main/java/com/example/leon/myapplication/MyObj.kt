package com.example.leon.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *   description:
 *   created by crx on 2018/12/13 16:09
 */
@Parcelize
data class MyObj(var name: String = "myObj", var hh: String = "Hello World!") : Parcelable {


}