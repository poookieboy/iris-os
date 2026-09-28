package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class ControlCenter(private val context: Context) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(24, 24, 24, 24)
        }

        val header = TextView(context).apply {
            text = "Control Center"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 16, 0, 24)
        }

        root.addView(header)

        val firstRow = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        firstRow.addView(createControl("Wi-Fi"))
        firstRow.addView(createControl("Bluetooth"))

        root.addView(firstRow)

        val secondRow = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        secondRow.addView(createControl("Mobile Data"))
        secondRow.addView(createControl("Airplane"))

        root.addView(secondRow)

        val brightness = TextView(context).apply {
            text = "Brightness"
            textSize = 18f
            setTextColor(Color.BLACK)
            setPadding(8, 32, 8, 8)
        }

        root.addView(brightness)

        val brightnessBar = View(context).apply {
            setBackgroundColor(Color.BLACK)
        }

        root.addView(
            brightnessBar,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                8
            ).apply {
                setMargins(8, 8, 8, 24)
            }
        )

        val volume = TextView(context).apply {
            text = "Volume"
            textSize = 18f
            setTextColor(Color.BLACK)
            setPadding(8, 8, 8, 8)
        }

        root.addView(volume)

        val volumeBar = View(context).apply {
            setBackgroundColor(Color.DKGRAY)
        }

        root.addView(
            volumeBar,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                8
            ).apply {
                setMargins(8, 8, 8, 24)
            }
        )

        val settings = TextView(context).apply {
            text = "Settings"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(16, 20, 16, 20)
        }

        root.addView(settings)

        return root
    }

    private fun createControl(label: String): TextView {

        return TextView(context).apply {
            text = label
            textSize = 16f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
            setPadding(24, 24, 24, 24)

            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            ).apply {
                setMargins(8, 8, 8, 8)
            }
        }
    }
}
