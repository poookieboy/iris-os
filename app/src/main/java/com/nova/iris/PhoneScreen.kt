package com.nova.iris

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class PhoneScreen(
    private val context: Context,
    private val onBack: () -> Unit
) {

    private val number = StringBuilder()

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
            setOnClickListener { onBack() }
        }

        val title = TextView(context).apply {
            text = "Phone"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val display = TextView(context).apply {
            text = ""
            textSize = 34f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(8, 36, 8, 24)
        }

        root.addView(
            display,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val dialPad = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        val keys = arrayOf(
            arrayOf("1", "2", "3"),
            arrayOf("4", "5", "6"),
            arrayOf("7", "8", "9"),
            arrayOf("*", "0", "#")
        )

        keys.forEach { row ->

            val rowLayout = LinearLayout(context).apply {
                gravity = Gravity.CENTER
            }

            row.forEach { key ->

                val button = Button(context).apply {
                    text = key
                    textSize = 22f

                    setOnClickListener {
                        number.append(key)
                        display.text = number.toString()
                    }
                }

                rowLayout.addView(
                    button,
                    LinearLayout.LayoutParams(
                        0,
                        64,
                        1f
                    ).apply {
                        setMargins(6, 6, 6, 6)
                    }
                )
            }

            dialPad.addView(rowLayout)
        }

        root.addView(dialPad)

        val actions = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        val delete = Button(context).apply {
            text = "⌫"
            textSize = 20f

            setOnClickListener {
                if (number.isNotEmpty()) {
                    number.deleteCharAt(number.length - 1)
                    display.text = number.toString()
                }
            }
        }

        val call = Button(context).apply {
            text = "Call"
            textSize = 18f
            typeface = Typeface.DEFAULT_BOLD

            setOnClickListener {

                val value = number.toString()

                if (value.isNotBlank()) {

                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:$value")
                    )

                    context.startActivity(intent)
                }
            }
        }

        actions.addView(
            delete,
            LinearLayout.LayoutParams(0, 60, 1f)
        )

        actions.addView(
            call,
            LinearLayout.LayoutParams(0, 60, 1f)
        )

        root.addView(actions)

        return root
    }
}
