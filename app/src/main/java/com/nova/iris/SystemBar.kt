package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SystemBar(private val context: Context) {

    fun create(): View {

        val bar = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(24, 12, 24, 12)
            setBackgroundColor(Color.WHITE)
        }

        val time = TextView(context).apply {
            text = getCurrentTime()
            textSize = 14f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        val spacer = View(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                1,
                1f
            )
        }

        val signal = TextView(context).apply {
            text = "▮▮▮"
            textSize = 13f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }

        val wifi = TextView(context).apply {
            text = "◉"
            textSize = 16f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(12, 0, 12, 0)
        }

        val battery = TextView(context).apply {
            text = "▰ 100%"
            textSize = 13f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }

        bar.addView(time)
        bar.addView(spacer)
        bar.addView(signal)
        bar.addView(wifi)
        bar.addView(battery)

        return bar
    }

    private fun getCurrentTime(): String {

        return SimpleDateFormat(
            "HH:mm",
            Locale.getDefault()
        ).format(Date())
    }
}
