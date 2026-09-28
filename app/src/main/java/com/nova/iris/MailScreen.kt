package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MailScreen(
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
            text = "Mail"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)
        root.addView(header)

        val search = TextView(context).apply {
            text = "Search mail"
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

        val folders = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        folders.addView(
            createFolder("Inbox", "No new messages")
        )

        folders.addView(
            createFolder("Sent", "Sent messages")
        )

        folders.addView(
            createFolder("Drafts", "Saved drafts")
        )

        folders.addView(
            createFolder("Trash", "Deleted messages")
        )

        root.addView(
            folders,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val compose = TextView(context).apply {
            text = "+  Compose"
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

    private fun createFolder(
        titleText: String,
        subtitleText: String
    ): View {

        val row = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 18, 20, 18)
        }

        val title = TextView(context).apply {
            text = titleText
            textSize = 19f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        val subtitle = TextView(context).apply {
            text = subtitleText
            textSize = 14f
            setTextColor(Color.GRAY)
        }

        row.addView(title)
        row.addView(subtitle)

        return row
    }
}
