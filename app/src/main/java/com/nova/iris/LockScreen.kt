package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
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

            setPadding(
                24,
                28,
                24,
                28
            )

            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(
                    Color.rgb(5, 8, 18),
                    Color.rgb(12, 17, 38),
                    Color.rgb(25, 24, 55)
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
                65
            )
        )

        // NOVA WORDMARK
        val logo = ImageView(context).apply {

            setImageResource(
                R.drawable.nova_wordmark
            )

            scaleType =
                ImageView.ScaleType.CENTER_INSIDE

            adjustViewBounds = true

            setPadding(
                10,
                10,
                10,
                10
            )
        }

        root.addView(
            logo,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                150
            )
        )

        // IRIS OS
        val iris = TextView(context).apply {

            text = "I R I S   O S"

            textSize = 11f

            setTextColor(
                Color.rgb(
                    185,
                    190,
                    210
                )
            )

            gravity = Gravity.CENTER

            letterSpacing = 0.25f

            setPadding(
                0,
                2,
                0,
                30
            )
        }

        root.addView(
            iris,
            LinearLayout.LayoutParams(
                -1,
                45
            )
        )

        // TIME
        val time = TextView(context).apply {

            text = getTime()

            textSize = 64f

            setTextColor(
                Color.WHITE
            )

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

        // DATE
        val date = TextView(context).apply {

            text = getDate()

            textSize = 16f

            setTextColor(
                Color.rgb(
                    215,
                    218,
                    235
                )
            )

            gravity = Gravity.CENTER
        }

        root.addView(
            date,
            LinearLayout.LayoutParams(
                -1,
                40
            )
        )

        // Flexible space
        root.addView(
            View(context),
            LinearLayout.LayoutParams(
                1,
                0,
                1f
            )
        )

        // Welcome
        val greeting = TextView(context).apply {

            text = "Welcome to Nova"

            textSize = 21f

            setTextColor(
                Color.WHITE
            )

            typeface =
                Typeface.DEFAULT_BOLD

            gravity = Gravity.CENTER
        }

        root.addView(
            greeting,
            LinearLayout.LayoutParams(
                -1,
                42
            )
        )

        // Instruction
        val instruction = TextView(context).apply {

            text = "Swipe up to unlock"

            textSize = 14f

            setTextColor(
                Color.rgb(
                    190,
                    194,
                    220
                )
            )

            gravity = Gravity.CENTER
        }

        root.addView(
            instruction,
            LinearLayout.LayoutParams(
                -1,
                34
            )
        )

        // Unlock button
        val unlock = TextView(context).apply {

            text = "↑"

            textSize = 28f

            setTextColor(
                Color.WHITE
            )

            gravity = Gravity.CENTER

            background = rounded(
                Color.argb(
                    45,
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

        // Bottom branding
        val system = TextView(context).apply {

            text = "Powered by Iris OS"

            textSize = 11f

            setTextColor(
                Color.rgb(
                    150,
                    155,
                    185
                )
            )

            gravity = Gravity.CENTER

            setPadding(
                0,
                18,
                0,
                0
            )
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
