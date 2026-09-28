package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarScreen(
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
            text = "Calendar"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val calendar = Calendar.getInstance()

        val monthFormat = SimpleDateFormat(
            "MMMM yyyy",
            Locale.getDefault()
        )

        val month = TextView(context).apply {
            text = monthFormat.format(calendar.time)
            textSize = 30f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 30, 0, 30)
        }

        root.addView(month)

        val days = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        val dayNames = arrayOf(
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat
        )

        val dayHeader = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        dayNames.forEach { day ->

            val label = TextView(context).apply {
                text = day
                textSize = 14f
                set
