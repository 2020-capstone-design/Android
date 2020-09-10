package com.example.whattoeattoday

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.whattoeattoday.vo.SharedPreference
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    val SPLASH_VIEW_TIME: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({ //delay를 위한 handler
            SharedPreference.init(this)
            if (SharedPreference.universalNum != -1) {
//                startActivity<MainActivity>()
                startActivity<SelectUniv>()
            } else {
                startActivity<SelectUniv>()
            }
            finish()
        }, SPLASH_VIEW_TIME)
    }

}