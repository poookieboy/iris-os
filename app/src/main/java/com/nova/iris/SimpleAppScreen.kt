package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class SimpleAppScreen(
    private val context: Context,
    private val title: String,
    private val subtitle: String,
    private val icon: String,
    private val onBack: () -> Unit
) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL

            setPadding(
                22,
                20,
                22,
                20
            )

            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(
                    Color.rgb(13, 18, 40),
                    Color.rgb(58, 53, 112),
                    Color.rgb(119, 88, 177)
                )
            )
        }

        val back = TextView(context).apply {

            text = "‹  Home"
            textSize = 18f
            setTextColor(Color.WHITE)

            setPadding(
                0,
                8,
                0,
                20
            )

            setOnClickListener {
                onBack()
            }
        }

        root.addView(back)

        val card = LinearLayout(context).apply {

            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            setPadding(
                28,
                42,
                28,
                42
            )

            background = rounded(
                Color.argb(
                    52,
                    255,
                    255,
                    255
                ),
                34f
            )
        }

        val iconView = TextView(context).apply {

            text = icon
            textSize = 48f
            gravity = Gravity.CENTER

            setTextColor(
                Color.WHITE
            )

            background = rounded(
                Color.argb(
                    72,
                    255,
                    255,
                    255
                ),
                30f
            )
        }

        card.addView(
            iconView,
            LinearLayout.LayoutParams(
                100,
                100
            )
        )

        val heading = TextView(context).apply {

            text = title
            textSize = 30f

            setTextColor(
                Color.WHITE
            )

            typeface =
                Typeface.DEFAULT_BOLD

            gravity =
                Gravity.CENTER

            setPadding(
                0,
                22,
                0,
                8
            )
        }

        card.addView(heading)

        val description = TextView(context).apply {

            text = subtitle
            textSize = 15f

            setTextColor(
                Color.rgb(
                    232,
                    233,
                    250
                )
            )

            gravity =
                Gravity.CENTER
        }

        card.addView(description)

        root.addView(
            card,
            LinearLayout.LayoutParams(
                -1,
                0,
                1f
            )
        )

        return root
    }

    private fun rounded(
        color: Int,
        radius: Float
    ): GradientDrawable {

        return GradientDrawable().apply {

            setColor(color)

            cornerRadius =
                radius
        }
    }
}
