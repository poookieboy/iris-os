package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class WeatherScreen(
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
            text = "Weather"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val location = TextView(context).apply {
            text = "Current Location"
            textSize = 18f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(0, 40, 0, 10)
        }

        root.addView(location)

        val temperature = TextView(context).apply {
            text = "--°"
            textSize = 72f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        root.addView(
            temperature,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                150
            )
        )

        val condition = TextView(context).apply {
            text = "Weather data unavailable"
            textSize = 20f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(condition)

        val details = LinearLayout(context).apply {
            gravity = Gravity.CENTER
            setPadding(0, 40, 0, 20)
        }

        val humidity = createDetail(
            "Humidity",
            "--"
        )

        val wind = createDetail(
            "Wind",
            "--"
        )

        val feelsLike = createDetail(
            "Feels Like",
            "--"
        )

        details.addView(
            humidity,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        details.addView(
            wind,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        details.addView(
            feelsLike,
            LinearLayout.LayoutParams(0, 100, 1f)
        )

        root.addView(details)

        val forecast = TextView(context).apply {
            text = "Forecast will appear here"
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            forecast,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val refresh = TextView(context).apply {
            text = "Refresh Weather"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            refresh,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }

    private fun createDetail(
        title: String,
        value: String
    ): View {

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }

        val titleView = TextView(context).apply {
            text = title
            textSize = 14f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        val valueView = TextView(context).apply {
            text = value
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        layout.addView(titleView)
        layout.addView(valueView)

        return layout
    }
}
