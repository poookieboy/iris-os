package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MusicScreen(
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
            text = "Music"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val search = TextView(context).apply {
            text = "Search music"
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
                bottomMargin = 24
            }
        )

        val empty = TextView(context).apply {
            text = "No music yet"
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

        val player = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(20, 20, 20, 20)
        }

        val track = TextView(context).apply {
            text = "Nothing Playing"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val controls = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        val previous = createControl("⏮")
        val play = createControl("▶")
        val next = createControl("⏭")

        controls.addView(
            previous,
            LinearLayout.LayoutParams(0, 70, 1f)
        )

        controls.addView(
            play,
            LinearLayout.LayoutParams(0, 70, 1f)
        )

        controls.addView(
            next,
            LinearLayout.LayoutParams(0, 70, 1f)
        )

        player.addView(track)
        player.addView(controls)

        root.addView(player)

        return root
    }

    private fun createControl(
        symbol: String
    ): TextView {

        return TextView(context).apply {
            text = symbol
            textSize = 28f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }
    }
}
