package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class IrisAssistantScreen(
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
            text = "Iris"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val greeting = TextView(context).apply {
            text = "How can I help?"
            textSize = 32f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        root.addView(
            greeting,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val response = TextView(context).apply {
            text = "Iris is ready."
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            response,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        val inputArea = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val input = EditText(context).apply {
            hint = "Ask Iris..."
            textSize = 17f
            setSingleLine(true)
            setTextColor(Color.BLACK)
            setHintTextColor(Color.GRAY)
        }

        val send = TextView(context).apply {
            text = "Send"
            textSize = 16f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)

            setOnClickListener {

                val message = input.text.toString().trim()

                if (message.isNotEmpty()) {
                    response.text = "Iris received: $message"
                    input.text.clear()
                }
            }
        }

        inputArea.addView(
            input,
            LinearLayout.LayoutParams(
                0,
                65,
                1f
            )
        )

        inputArea.addView(
            send,
            LinearLayout.LayoutParams(
                100,
                65
            )
        )

        root.addView(inputArea)

        val voice = TextView(context).apply {
            text = "◉  Voice"
            textSize = 17f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(20, 20, 20, 20)
        }

        root.addView(
            voice,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }
}
