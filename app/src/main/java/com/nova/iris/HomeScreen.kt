package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class HomeScreen(
    private val context: Context
) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(24, 24, 24, 24)
        }

        // System bar
        val systemBar = SystemBar(context).create()
        root.addView(
            systemBar,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        // Main content
        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }

        val clock = TextView(context).apply {
            text = "12:00"
            textSize = 56f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val welcome = TextView(context).apply {
            text = "Iris OS"
            textSize = 26f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val subtitle = TextView(context).apply {
            text = "Welcome to your Nova"
            textSize = 16f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
        }

        content.addView(clock)
        content.addView(welcome)
        content.addView(subtitle)

        // Phone button
        val phoneButton = TextView(context).apply {
            text = "☎  Phone"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(30, 20, 30, 20)

            setBackgroundColor(Color.LTGRAY)

            setOnClickListener {

                val phoneScreen = PhoneScreen(
                    context = context,
                    onBack = {
                        root.removeAllViews()
                        root.addView(
                            create(),
                            LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                            )
                        )
                    }
                )

                root.removeAllViews()

                root.addView(
                    phoneScreen.create(),
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                )
            }
        }

        content.addView(
            phoneButton,
            LinearLayout.LayoutParams(
                220,
                80
            ).apply {
                gravity = Gravity.CENTER
                topMargin = 40
            }
        )

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
