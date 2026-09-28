package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class HomeScreen(
    private val context: Context
) {

    fun create(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(20, 20, 20, 20)
        }

        val systemBar = SystemBar(context).create()

        root.addView(
            systemBar,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
            )
        )

        val greeting = TextView(context).apply {
            text = "Iris OS"
            textSize = 30f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setPadding(0, 25, 0, 5)
        }

        root.addView(greeting)

        val subtitle = TextView(context).apply {
            text = "Welcome to Nova"
            textSize = 16f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 20)
        }

        root.addView(subtitle)

        val apps = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        apps.addView(
            createRow(
                "Phone",
                "Contacts",
                "Messages"
            )
        )

        apps.addView(
            createRow(
                "Camera",
                "Gallery",
                "Files"
            )
        )

        apps.addView(
            createRow(
                "Calculator",
                "Calendar",
                "Clock"
            )
        )

        apps.addView(
            createRow(
                "Weather",
                "Maps",
                "Music"
            )
        )

        apps.addView(
            createRow(
                "Mail",
                "Recorder",
                "Timer"
            )
        )

        apps.addView(
            createRow(
                "Reminders",
                "Wallet",
                "Nova Store"
            )
        )

        apps.addView(
            createRow(
                "Iris",
                "Settings",
                "Security"
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

        return root
    }

    private fun createRow(
        first: String,
        second: String,
        third: String
    ): View {

        val row = LinearLayout(context).apply {
            gravity = Gravity.CENTER
        }

        row.addView(
            createAppButton(first),
            LinearLayout.LayoutParams(0, 80, 1f)
        )

        row.addView(
            createAppButton(second),
            LinearLayout.LayoutParams(0, 80, 1f)
        )

        row.addView(
            createAppButton(third),
            LinearLayout.LayoutParams(0, 80, 1f)
        )

        return row
    }

    private fun createAppButton(
        name: String
    ): TextView {

        return TextView(context).apply {

            text = name
            textSize = 15f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setPadding(6, 6, 6, 6)
            setBackgroundColor(Color.LTGRAY)

            setOnClickListener {

                when (name) {

                    "Phone" -> openScreen(
                        PhoneScreen(context) {
                            showHome()
                        }
                    )

                    "Contacts" -> openScreen(
                        ContactsScreen(context) {
                            showHome()
                        }
                    )

                    "Messages" -> openScreen(
                        MessagesScreen(context) {
                            showHome()
                        }
                    )

                    "Camera" -> openScreen(
                        CameraScreen(context) {
                            showHome()
                        }
                    )

                    "Gallery" -> openScreen(
                        GalleryScreen(context) {
                            showHome()
                        }
                    )

                    "Files" -> openScreen(
                        FilesScreen(context) {
                            showHome()
                        }
                    )

                    "Calculator" -> openScreen(
                        CalculatorScreen(context) {
                            showHome()
                        }
                    )

                    "Calendar" -> openScreen(
                        CalendarScreen(context) {
                            showHome()
                        }
                    )

                    "Clock" -> openScreen(
                        ClockScreen(context) {
                            showHome()
                        }
                    )

                    "Weather" -> openScreen(
                        WeatherScreen(context) {
                            showHome()
                        }
                    )

                    "Maps" -> openScreen(
                        MapsScreen(context) {
                            showHome()
                        }
                    )

                    "Music" -> openScreen(
                        MusicScreen(context) {
                            showHome()
                        }
                    )

                    "Mail" -> openScreen(
                        MailScreen(context) {
                            showHome()
                        }
                    )

                    "Recorder" -> openScreen(
                        RecorderScreen(context) {
                            showHome()
                        }
                    )

                    "Timer" -> openScreen(
                        TimerScreen(context) {
                            showHome()
                        }
                    )

                    "Reminders" -> openScreen(
                        RemindersScreen(context) {
                            showHome()
                        }
                    )

                    "Wallet" -> openScreen(
                        WalletScreen(context) {
                            showHome()
                        }
                    )

                    "Nova Store" -> openScreen(
                        NovaStoreScreen(context) {
                            showHome()
                        }
                    )

                    "Iris" -> openScreen(
                        IrisAssistantScreen(context) {
                            showHome()
                        }
                    )

                    "Settings" -> openScreen(
                        SettingsScreen(context) {
                            showHome()
                        }
                    )
                }
            }
        }
    }

    private fun openScreen(screen: Any) {

        val view = when (screen) {

            is PhoneScreen -> screen.create()
            is ContactsScreen -> screen.create()
            is MessagesScreen -> screen.create()
            is CameraScreen -> screen.create()
            is GalleryScreen -> screen.create()
            is FilesScreen -> screen.create()
            is CalculatorScreen -> screen.create()
            is CalendarScreen -> screen.create()
            is ClockScreen -> screen.create()
            is WeatherScreen -> screen.create()
            is MapsScreen -> screen.create()
            is MusicScreen -> screen.create()
            is MailScreen -> screen.create()
            is RecorderScreen -> screen.create()
            is TimerScreen -> screen.create()
            is RemindersScreen -> screen.create()
            is WalletScreen -> screen.create()
            is NovaStoreScreen -> screen.create()
            is IrisAssistantScreen -> screen.create()
            is SettingsScreen -> screen.create()

            else -> return
        }

        (context as? MainActivity)?.setContentView(view)
    }

    private fun showHome() {

        (context as? MainActivity)?.setContentView(
            create()
        )
    }
}
