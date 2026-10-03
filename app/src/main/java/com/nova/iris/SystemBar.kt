package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SystemBar(
    private val context: Context
) {

    private var startY = 0f

    fun create(): View {

        val bar = LinearLayout(context).apply {

            orientation =
                LinearLayout.HORIZONTAL

            gravity =
                Gravity.CENTER_VERTICAL

            setPadding(
                24,
                12,
                24,
                12
            )

            setBackgroundColor(
                Color.WHITE
            )

            setOnTouchListener { _, event ->

                when (event.action) {

                    MotionEvent.ACTION_DOWN -> {

                        startY = event.rawY

                        true
                    }

                    MotionEvent.ACTION_UP -> {

                        val distance =
                            event.rawY - startY

                        // Swipe downward
                        if (distance > 80) {

                            (context as? MainActivity)
                                ?.showQuickPanel()

                            true
                        }

                        // Simple tap
                        else {

                            (context as? MainActivity)
                                ?.showQuickPanel()

                            true
                        }
                    }

                    else -> true
                }
            }
        }

        // TIME
        val time =
            TextView(context).apply {

                text =
                    getCurrentTime()

                textSize = 14f

                setTextColor(
                    Color.BLACK
                )

                typeface =
                    Typeface.DEFAULT_BOLD
            }

        // Flexible space
        val spacer =
            View(context).apply {

                layoutParams =
                    LinearLayout.LayoutParams(
                        0,
                        1,
                        1f
                    )
            }

        // SIGNAL
        val signal =
            TextView(context).apply {

                text = "▮▮▮"

                textSize = 13f

                setTextColor(
                    Color.BLACK
                )

                gravity =
                    Gravity.CENTER
            }

        // WIFI
        val wifi =
            TextView(context).apply {

                text = "◉"

                textSize = 16f

                setTextColor(
                    Color.BLACK
                )

                gravity =
                    Gravity.CENTER

                setPadding(
                    12,
                    0,
                    12,
                    0
                )
            }

        // BATTERY
        val battery =
            TextView(context).apply {

                text = "▰ 100%"

                textSize = 13f

                setTextColor(
                    Color.BLACK
                )

                gravity =
                    Gravity.CENTER
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
        ).format(
            Date()
        )
    }
}
