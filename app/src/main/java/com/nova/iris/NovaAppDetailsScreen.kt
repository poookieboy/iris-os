package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class NovaAppDetailsScreen(
    private val context: Context,
    private val appName: String,
    private val description: String,
    private val onBack: () -> Unit
) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(24, 24, 24, 24)
        }

        val header = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
        }

        val back = TextView(context).apply {
            text = "‹"
            textSize = 40f
            setTextColor(Color.BLACK)
            setPadding(0, 0, 24, 0)

            setOnClickListener {
                onBack()
            }
        }

        val title = TextView(context).apply {
            text = appName
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val icon = TextView(context).apply {
            text = appName.take(1).uppercase()
            textSize = 42f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            icon,
            LinearLayout.LayoutParams(
                130,
                130
            ).apply {
                gravity = Gravity.CENTER
                topMargin = 35
                bottomMargin = 20
            }
        )

        val appTitle = TextView(context).apply {
            text = appName
            textSize = 26f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        root.addView(appTitle)

        val descriptionView = TextView(context).apply {
            text = description
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(20, 10, 20, 20)
        }

        root.addView(descriptionView)

        val getButton = TextView(context).apply {
            text = "GET"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)

            setOnClickListener {
                text = "Coming Soon"
            }
        }

        root.addView(
            getButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        val screenshotsTitle = TextView(context).apply {
            text = "Screenshots"
            textSize = 20f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 35, 0, 15)
        }

        root.addView(screenshotsTitle)

        val screenshots = TextView(context).apply {
            text = "App previews will appear here"
            textSize = 16f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            screenshots,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                180
            )
        )

        val information = TextView(context).apply {
            text = """
Version 1.0

Developer
Nova Developer

Compatibility
Nova / Iris OS

Category
Apps
            """.trimIndent()

            textSize = 15f
            setTextColor(Color.DKGRAY)
            setPadding(0, 25, 0, 0)
        }

        root.addView(
            information,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        return root
    }
}
