package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LockScreen(
    private val context: Context,
    private val onUnlock: () -> Unit
) {

    private var downY = 0f

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 28, 24, 28)

            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(
                    Color.rgb(12, 17, 40),
                    Color.rgb(39, 39, 91),
                    Color.rgb(91, 70, 145)
                )
            )

            setOnTouchListener { _, event ->

                when (event.action) {

                    MotionEvent.ACTION_DOWN -> {
                        downY = event.y
                        true
                    }

                    MotionEvent.ACTION_UP -> {

                        val distance =
                            downY - event.y

                        if (distance > 140) {
                            onUnlock()
                        }

                        true
                    }

                    else -> true
                }
            }
        }

        // Top spacing
        root.addView(
            View(context),
            LinearLayout.LayoutParams(
                1,
                70
            )
        )

        // Nova logo
        val logo = TextView(context).apply {
            text = "✿"
            textSize = 62f
            setTextColor(
                Color.rgb(
                    187,
                    163,
                    255
                )
            )
            gravity = Gravity.CENTER
        }

        root.addView(
            logo,
            LinearLayout.LayoutParams(
                -1,
                90
            )
        )

        // Time
        val time = TextView(context).apply {
            text = getTime()
            textSize = 64f
            setTextColor(Color.WHITE)

            typeface = Typeface.create(
                "sans-serif-light",
                Typeface.NORMAL
            )

            gravity = Gravity.CENTER
        }

        root.addView(
            time,
            LinearLayout.LayoutParams(
                -1,
                90
            )
        )

        // Date
        val date = TextView(context).apply {
            text = getDate()
            textSize = 16f
            setTextColor(
                Color.rgb(
                    225,
                    226,
                    245
                )
            )
            gravity = Gravity.CENTER
        }

        root.addView(date)

        // Flexible space
        root.addView(
            View(context),
            LinearLayout.LayoutParams(
                1,
                0,
                1f
            )
        )

        // Nova greeting
        val greeting = TextView(context).apply {
            text = "Welcome to Nova"
            textSize = 21f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        root.addView(
            greeting,
            LinearLayout.LayoutParams(
                -1,
                40
            )
        )

        val instruction = TextView(context).apply {
            text = "Swipe up to unlock"
            textSize = 14f
            setTextColor(
                Color.rgb(
                    215,
                    216,
                    240
                )
            )
            gravity = Gravity.CENTER
        }

        root.addView(
            instruction,
            LinearLayout.LayoutParams(
                -1,
                35
            )
        )

        // Unlock button
        val unlock = TextView(context).apply {
            text = "↑"
            textSize = 27f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER

            background = rounded(
                Color.argb(
                    55,
                    255,
                    255,
                    255
                ),
                32f
            )

            setOnClickListener {
                onUnlock()
            }
        }

        root.addView(
            unlock,
            LinearLayout.LayoutParams(
                64,
                64
            )
        )

        // Bottom system text
        val system = TextView(context).apply {
            text = "Iris OS  •  Nova"
            textSize = 11f
            setTextColor(
                Color.rgb(
                    190,
                    191,
                    220
                )
            )
            gravity = Gravity.CENTER
            setPadding(0, 18, 0, 0)
        }

        root.addView(
            system,
            LinearLayout.LayoutParams(
                -1,
                40
            )
        )

        return root
    }

    private fun getTime(): String {

        return SimpleDateFormat(
            "HH:mm",
            Locale.getDefault()
        ).format(
            Date()
        )
    }

    private fun getDate(): String {

        return SimpleDateFormat(
            "EEEE, d MMMM",
            Locale.getDefault()
        ).format(
            Date()
        )
    }

    private fun rounded(
        color: Int,
        radius: Float
    ): GradientDrawable {

        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius
        }
    }
}
