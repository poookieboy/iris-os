package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class CalculatorScreen(
    private val context: Context,
    private val onBack: () -> Unit
) {

    private val expression = StringBuilder()

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
            text = "Calculator"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val display = TextView(context).apply {
            text = "0"
            textSize = 42f
            setTextColor(Color.BLACK)
            gravity = Gravity.END or Gravity.CENTER_VERTICAL
            setPadding(12, 20, 12, 20)
        }

        root.addView(
            display,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val buttons = arrayOf(
            arrayOf("7", "8", "9", "÷"),
            arrayOf("4", "5", "6", "×"),
            arrayOf("1", "2", "3", "−"),
            arrayOf("0", ".", "=", "+"),
            arrayOf("C")
        )

        buttons.forEach { row ->

            val rowLayout = LinearLayout(context).apply {
                gravity = Gravity.CENTER
            }

            row.forEach { value ->

                val button = TextView(context).apply {
                    text = value
                    textSize = 22f
                    setTextColor(Color.BLACK)
                    gravity = Gravity.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                    setPadding(10, 10, 10, 10)
                    setBackgroundColor(Color.LTGRAY)

                    setOnClickListener {

                        when (value) {

                            "C" -> {
                                expression.clear()
                                display.text = "0"
                            }

                            "=" -> {
                                display.text = calculate(expression.toString())
                                expression.clear()
                                expression.append(display.text)
                            }

                            else -> {
                                expression.append(value)
                                display.text = expression.toString()
                            }
                        }
                    }
                }

                rowLayout.addView(
                    button,
                    LinearLayout.LayoutParams(
                        0,
                        64,
                        1f
                    ).apply {
                        setMargins(5, 5, 5, 5)
                    }
                )
            }

            root.addView(rowLayout)
        }

        return root
    }

    private fun calculate(value: String): String {

        return try {

            val clean = value
                .replace("×", "*")
                .replace("÷", "/")
                .replace("−", "-")

            val result = simpleCalculate(clean)

            if (result % 1.0 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }

        } catch (e: Exception) {
            "Error"
        }
    }

    private fun simpleCalculate(value: String): Double {

        val operators = listOf("+", "-", "*", "/")

        for (operator in operators) {

            val index = value.indexOf(operator, 1)

            if (index > 0) {

                val left = value.substring(0, index).toDouble()
                val right = value.substring(index + 1).toDouble()

                return when (operator) {
                    "+" -> left + right
                    "-" -> left - right
                    "*" -> left * right
                    "/" -> left / right
                    else -> 0.0
                }
            }
        }

        return value.toDouble()
    }
}
