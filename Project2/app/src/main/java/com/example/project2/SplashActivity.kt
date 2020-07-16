package com.example.project2

import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    val SPLASH_VIEW_TIME: Long = 2000 //2초간 스플래시 화면을 보여줌 (ms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({ //delay를 위한 handler
//            startActivity(Intent(this, SelectUniv::class.java))
             loadData()
            finish()
        }, SPLASH_VIEW_TIME)
    }

    fun loadData() {
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val universal = pref.getInt("UniversalNum", -1)

        if (universal != -1) {
            startActivity<MainActivity>()
        } else {
            startActivity<SelectUniv>()
        }
    }

}