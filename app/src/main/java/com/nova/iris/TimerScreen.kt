package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class TimerScreen(
    private val context: Context,
    private val onBack: () -> Unit
) {

    private var timer: CountDownTimer? = null
    private var remainingTime = 5 * 60 * 1000L

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
                timer?.cancel()
                onBack()
            }
        }

        val title = TextView(context).apply {
            text = "Timer"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val display = TextView(context).apply {
            text = formatTime(remainingTime)
            textSize = 64f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        root.addView(
            display,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val controls = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        val start = TextView(context).apply {
            text = "Start"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)

            setOnClickListener {

                timer?.cancel()

                timer = object : CountDownTimer(
                    remainingTime,
                    1000
                ) {

                    override fun onTick(millisUntilFinished: Long) {

                        remainingTime = millisUntilFinished

                        display.text =
                            formatTime(remainingTime)
                    }

                    override fun onFinish() {

                        remainingTime = 0

                        display.text = "00:00"

                        text = "Start"
                    }

                }.start()

                text = "Running"
            }
        }

        val reset = TextView(context).apply {
            text = "Reset"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)

            setOnClickListener {

                timer?.cancel()

                remainingTime = 5 * 60 * 1000L

                display.text =
                    formatTime(remainingTime)

                start.text = "Start"
            }
        }

        controls.addView(
            start,
            LinearLayout.LayoutParams(0, 70, 1f)
        )

        controls.addView(
            reset,
            LinearLayout.LayoutParams(0, 70, 1f)
        )

        root.addView(controls)

        return root
    }

    private fun formatTime(
        milliseconds: Long
    ): String {

        val totalSeconds =
            milliseconds / 1000

        val minutes =
            totalSeconds / 60

        val seconds =
            totalSeconds % 60

        return String.format(
            "%02d:%02d",
            minutes,
            seconds
        )
    }
}
