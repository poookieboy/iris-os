package com.nova.iris

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val handler =
        Handler(Looper.getMainLooper())

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )

        // Iris OS boot screen
        setContentView(
            R.layout.boot_screen
        )

        // Boot → Lock Screen
        handler.postDelayed({

            showLockScreen()

        }, 2500)
    }

    private fun showLockScreen() {

        val lockScreen =
            LockScreen(this) {

                showHome()
            }

        setContentView(
            lockScreen.create()
        )
    }

    private fun showHome() {

        val homeScreen =
            HomeScreen(this)

        setContentView(
            homeScreen.create()
        )
    }
}
