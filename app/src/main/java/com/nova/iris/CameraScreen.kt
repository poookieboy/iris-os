package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class CameraScreen(
    private val context: Context,
    private val onBack: () -> Unit
) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.BLACK)
        }

        val header = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            setPadding(24, 24, 24, 24)
        }

        val back = TextView(context).apply {
            text = "‹"
            textSize = 40f
            setTextColor(Color.WHITE)
            setPadding(0, 0, 24, 0)

            setOnClickListener {
                onBack()
            }
        }

        val title = TextView(context).apply {
            text = "Camera"
            textSize = 28f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val preview = TextView(context).apply {
            text = "Camera Preview"
            textSize = 22f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            preview,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val controls = LinearLayout(context).apply {
            gravity = Gravity.CENTER
            setPadding(24, 24, 24, 36)
        }

        val gallery = TextView(context).apply {
            text = "Gallery"
            textSize = 16f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val capture = TextView(context).apply {
            text = "●"
            textSize = 70f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val switchCamera = TextView(context).apply {
            text = "↻"
            textSize = 36f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        controls.addView(
            gallery,
            LinearLayout.LayoutParams(0, 80, 1f)
        )

        controls.addView(
            capture,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        controls.addView(
            switchCamera,
            LinearLayout.LayoutParams(0, 80, 1f)
        )

        root.addView(controls)

        return root
    }
}
