package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class NovaStoreScreen(
    private val context: Context,
    private val onBack: () -> Unit
) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(20, 20, 20, 20)
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
            text = "Nova Store"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val search = TextView(context).apply {
            text = "Search apps and games"
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

        val categories = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        categories.addView(
            createCategory("Featured"),
            LinearLayout.LayoutParams(0, 55, 1f)
        )

        categories.addView(
            createCategory("Apps"),
            LinearLayout.LayoutParams(0, 55, 1f)
        )

        categories.addView(
            createCategory("Games"),
            LinearLayout.LayoutParams(0, 55, 1f)
        )

        root.addView(categories)

        val featuredTitle = TextView(context).apply {
            text = "Featured"
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 30, 0, 15)
        }

        root.addView(featuredTitle)

        val apps = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        apps.addView(
            createApp(
                "Insightly",
                "Learning and revision"
            )
        )

        apps.addView(
            createApp(
                "Timely",
                "Focus and productivity"
            )
        )

        apps.addView(
            createApp(
                "Nova Notes",
                "Notes for your Nova"
            )
        )

        root.addView(
            apps,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val myApps = TextView(context).apply {
            text = "My Apps"
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.LTGRAY)
        }

        root.addView(
            myApps,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        return root
    }

    private fun createCategory(
        name: String
    ): TextView {

        return TextView(context).apply {
            text = name
            textSize = 14f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }
    }

    private fun createApp(
        name: String,
        description: String
    ): View {

        val row = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            setPadding(16, 14, 16, 14)
        }

        val info = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        val title = TextView(context).apply {
            text = name
            textSize = 18f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        val subtitle = TextView(context).apply {
            text = description
            textSize = 14f
            setTextColor(Color.GRAY)
        }

        info.addView(title)
        info.addView(subtitle)

        val install = TextView(context).apply {
            text = "GET"
            textSize = 15f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(18, 12, 18, 12)
            setBackgroundColor(Color.LTGRAY)
        }

        row.addView(
            info,
            LinearLayout.LayoutParams(
                0,
                70,
                1f
            )
        )

        row.addView(
            install,
            LinearLayout.LayoutParams(
                90,
                55
            )
        )

        return row
    }
}
