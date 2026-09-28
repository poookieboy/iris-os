package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class RecorderScreen(
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
            text = "Recorder"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val timer = TextView(context).apply {
            text = "00:00"
            textSize = 56f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        root.addView(
            timer,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val status = TextView(context).apply {
            text = "Ready to record"
            textSize = 18f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            status,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                60
            )
        )

        val recordButton = TextView(context).apply {
            text = "●"
            textSize = 72f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER

            setOnClickListener {

                if (status.text == "Recording...") {
                    status.text = "Recording stopped"
                    text = "●"
                } else {
                    status.text = "Recording..."
                    text = "■"
                }
            }
        }

        root.addView(
            recordButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                130
            )
        )

        val recordings = TextView(context).apply {
            text = "No recordings yet"
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            recordings,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        return root
    }
}
