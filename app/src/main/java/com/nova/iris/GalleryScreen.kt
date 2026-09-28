package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class GalleryScreen(
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
            text = "Gallery"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val tabs = LinearLayout(context).apply {
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 24)
        }

        val photos = TextView(context).apply {
            text = "Photos"
            textSize = 17f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val albums = TextView(context).apply {
            text = "Albums"
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        tabs.addView(
            photos,
            LinearLayout.LayoutParams(0, 60, 1f)
        )

        tabs.addView(
            albums,
            LinearLayout.LayoutParams(0, 60, 1f)
        )

        root.addView(tabs)

        val empty = TextView(context).apply {
            text = "No photos yet"
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

        val importButton = TextView(context).apply {
            text = "+  Import Photos"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            importButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }
}
