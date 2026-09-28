package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class SettingsScreen(private val context: Context) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(context).apply {
            text = "Settings"
            textSize = 32f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(8, 20, 8, 28)
        }

        root.addView(title)

        addSection(root, "Nova")

        addSetting(root, "Display")
        addSetting(root, "Sound & Haptics")
        addSetting(root, "Wallpaper & Style")
        addSetting(root, "Foldable & Continuity")

        addSection(root, "Connectivity")

        addSetting(root, "Wi-Fi")
        addSetting(root, "Bluetooth")
        addSetting(root, "Mobile Network")
        addSetting(root, "Airplane Mode")

        addSection(root, "Personal")

        addSetting(root, "Notifications")
        addSetting(root, "Apps")
        addSetting(root, "Accounts")
        addSetting(root, "Security & Privacy")

        addSection(root, "System")

        addSetting(root, "Battery")
        addSetting(root, "Storage")
        addSetting(root, "System Update")
        addSetting(root, "About Nova")

        return root
    }

    private fun addSection(
        root: LinearLayout,
        title: String
    ) {

        val section = TextView(context).apply {
            text = title
            textSize = 14f
            setTextColor(Color.DKGRAY)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(8, 24, 8, 8)
        }

        root.addView(section)
    }

    private fun addSetting(
        root: LinearLayout,
        title: String
    ) {

        val item = TextView(context).apply {
            text = title
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER_VERTICAL
            setPadding(16, 20, 16, 20)

            isClickable = true
            isFocusable = true
        }

        root.addView(
            item,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
    }
}
