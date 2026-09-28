package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MapsScreen(
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
            text = "Maps"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val search = TextView(context).apply {
            text = "Search places"
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
                bottomMargin = 20
            }
        )

        val mapArea = LinearLayout(context).apply {
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
        }

        val mapText = TextView(context).apply {
            text = "Iris Maps"
            textSize = 26f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        mapArea.addView(mapText)

        root.addView(
            mapArea,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val location = TextView(context).apply {
            text = "◎  Current Location"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
        }

        root.addView(
            location,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        val directions = TextView(context).apply {
            text = "Directions"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            directions,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }
}
