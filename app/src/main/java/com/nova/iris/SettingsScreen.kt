package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class SettingsScreen(
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
            text = "Settings"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val profile = createSection(
            "Iris ID",
            "Nova account and identity"
        )

        val network = createSection(
            "Network & Internet",
            "Wi-Fi, mobile data and connections"
        )

        val display = createSection(
            "Display",
            "Brightness, appearance and screen"
        )

        val sound = createSection(
            "Sound & Haptics",
            "Volume, vibration and sounds"
        )

        val notifications = createSection(
            "Notifications",
            "Alerts and notification controls"
        )

        val apps = createSection(
            "Apps",
            "Installed applications and permissions"
        )

        val privacy = createSection(
            "Privacy",
            "Location, camera, microphone and data"
        )

        val security = createSection(
            "Security",
            "Screen lock, biometrics and device protection"
        )

        val wallet = createSection(
            "Wallet",
            "Payment and wallet settings"
        )

        val battery = createSection(
            "Battery",
            "Battery usage and power controls"
        )

        val storage = createSection(
            "Storage",
            "Device storage and files"
        )

        val system = createSection(
            "System",
            "Updates, language and device information"
        )

        val settingsList = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        settingsList.addView(profile)
        settingsList.addView(network)
        settingsList.addView(display)
        settingsList.addView(sound)
        settingsList.addView(notifications)
        settingsList.addView(apps)
        settingsList.addView(privacy)
        settingsList.addView(security)
        settingsList.addView(wallet)
        settingsList.addView(battery)
        settingsList.addView(storage)
        settingsList.addView(system)

        root.addView(
            settingsList,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        return root
    }

    private fun createSection(
        titleText: String,
        subtitleText: String
    ): View {

        val row = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 16, 20, 16)
        }

        val title = TextView(context).apply {
            text = titleText
            textSize = 18f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        val subtitle = TextView(context).apply {
            text = subtitleText
            textSize = 13f
            setTextColor(Color.GRAY)
        }

        row.addView(title)
        row.addView(subtitle)

        row.setOnClickListener {
            // Settings sections will become functional here.
        }

        return row
    }
}
