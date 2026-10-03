package com.nova.iris

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val handler =
        Handler(Looper.getMainLooper())

    private var showingQuickPanel = false

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

        showingQuickPanel = false

        val lockScreen =
            LockScreen(this) {

                showHome()
            }

        setContentView(
            lockScreen.create()
        )
    }

    private fun showHome() {

        showingQuickPanel = false

        val homeScreen =
            HomeScreen(this)

        setContentView(
            homeScreen.create()
        )
    }

    fun showQuickPanel() {

        showingQuickPanel = true

        val quickPanel =
            QuickPanelScreen(this) {

                showHome()
            }

        setContentView(
            quickPanel.create()
        )
    }

    override fun onBackPressed() {

        if (showingQuickPanel) {

            showHome()

        } else {

            super.onBackPressed()
        }
    }
}
