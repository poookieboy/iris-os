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
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
        )

        val dayHeader = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        dayNames.forEach { day ->

            val label = TextView(context).apply {
                text = day
                textSize = 14f
                setTextColor(Color.GRAY)
                gravity = Gravity.CENTER
            }

            dayHeader.addView(
                label,
                LinearLayout.LayoutParams(0, 50, 1f)
            )
        }

        days.addView(dayHeader)

        val firstDay = calendar.clone() as Calendar
        firstDay.set(Calendar.DAY_OF_MONTH, 1)

        val firstWeekday =
            firstDay.get(Calendar.DAY_OF_WEEK) - 1

        val daysInMonth =
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        var day = 1

        while (day <= daysInMonth) {

            val row = LinearLayout(context).apply {
                gravity = Gravity.CENTER
            }

            for (column in 0 until 7) {

                val cell = TextView(context).apply {
                    textSize = 17f
                    setTextColor(Color.BLACK)
                    gravity = Gravity.CENTER
                }

                if ((day == 1 && column < firstWeekday) ||
                    day > daysInMonth
                ) {
                    cell.text = ""
                } else {
                    cell.text = day.toString()
                    day++
                }

                row.addView(
                    cell,
                    LinearLayout.LayoutParams(0, 60, 1f)
                )
            }

            days.addView(row)
        }

        root.addView(days)

        val today = TextView(context).apply {
            text = "Today"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 30, 20, 20)
        }

        root.addView(
            today,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        val events = TextView(context).apply {
            text = "No events scheduled"
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            events,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val addEvent = TextView(context).apply {
            text = "+  Add Event"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            addEvent,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }
}
