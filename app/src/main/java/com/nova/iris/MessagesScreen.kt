package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MessagesScreen(
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
            text = "Messages"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val search = TextView(context).apply {
            text = "Search messages"
            textSize = 17f
            setTextColor(Color.DKGRAY)
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            search,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                65
            ).apply {
                topMargin = 24
            }
        )

        val empty = TextView(context).apply {
            text = "No messages yet"
            textSize = 20f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            empty,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val compose = TextView(context).apply {
            text = "+  New Message"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            compose,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }
}
