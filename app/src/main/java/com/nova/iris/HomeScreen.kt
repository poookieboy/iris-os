package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class HomeScreen(private val context: Context) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        val systemBar = SystemBar(context).create()

        root.addView(
            systemBar,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }

        val time = TextView(context).apply {
            text = "12:00"
            textSize = 64f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT
            gravity = Gravity.CENTER
        }

        val title = TextView(context).apply {
            text = "Iris OS"
            textSize = 24f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 0)
        }

        val message = TextView(context).apply {
            text = "Welcome to your Nova."
            textSize = 16f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 0)
        }

        content.addView(time)
        content.addView(title)
        content.addView(message)

        root.addView(
            content,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        return root
    }
}
