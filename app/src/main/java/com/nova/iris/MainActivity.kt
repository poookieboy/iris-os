package com.nova.iris

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val bootDelay = 2500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        setContentView(R.layout.boot_screen)

        Handler(Looper.getMainLooper()).postDelayed({

            showHomeScreen()

        }, bootDelay)
    }

    private fun showHomeScreen() {

        val home = HomeScreen(this)

        setContentView(home.create())
    }
}
