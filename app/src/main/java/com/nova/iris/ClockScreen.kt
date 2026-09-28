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

class ClockScreen(
    private val context: Context,
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
            text = "Clock"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val time = TextView(context).apply {
            textSize = 64f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val date = TextView(context).apply {
            textSize = 20f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            time,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        root.addView(
            date,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                80
            )
        )

        val formatTime = SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        )

        val formatDate = SimpleDateFormat(
            "EEEE, d MMMM yyyy",
            Locale.getDefault()
        )

        fun updateClock() {
            val now = Date()

            time.text = formatTime.format(now)
            date.text = formatDate.format(now)

            time.postDelayed(
                { updateClock() },
                1000
            )
        }

        updateClock()

        return root
    }
}
