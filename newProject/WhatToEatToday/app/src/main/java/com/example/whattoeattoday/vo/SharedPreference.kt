package com.example.whattoeattoday.vo

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPreference {

    lateinit var pref: SharedPreferences

    var universalName: String
        get() = pref.getString("UniversalName", "") ?: ""
        set(value) {
            pref.edit().putString("UniversalName", value).apply()
        }

    var universalNum: Int
        get() = pref.getInt("UniversalNum", -1)
        set(value) {
            pref.edit().putInt("UniversalNum", value).apply()
        }

    fun init(context: Context) {
        pref = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
    }

}