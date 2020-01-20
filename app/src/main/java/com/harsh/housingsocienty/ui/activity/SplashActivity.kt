package com.harsh.housingsocienty.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.harsh.housingsocienty.R
import com.harsh.housingsocienty.extension.openNewActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            openNewActivity(MainActivity::class.java)
            finish()
        }, 2000)
    }
}
