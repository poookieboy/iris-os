package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class HomeScreen(
private val context: Context
) {

private val white = Color.WHITE
private val softWhite = Color.rgb(235, 237, 255)

fun create(): View {

    val root = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(22, 18, 22, 18)

        background = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                Color.rgb(18, 24, 58),
                Color.rgb(54, 54, 116),
                Color.rgb(108, 82, 166)
            )
        )
    }

    // Iris system bar
    root.addView(
        SystemBar(context).create(),
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            58
        )
    )

    // Header
    val header = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        setPadding(0, 22, 0, 18)
    }

    // REAL IRIS LOGO
    val iris = ImageView(context).apply {
        setImageResource(R.drawable.iris_logo)
        scaleType = ImageView.ScaleType.CENTER_INSIDE
        setPadding(8, 8, 8, 8)
    }

    header.addView(
        iris,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            72
        )
    )

    val title = TextView(context).apply {
        text = "Iris"
        textSize = 30f
        setTextColor(white)
        typeface = Typeface.DEFAULT_BOLD
        gravity = Gravity.CENTER
    }

    header.addView(title)

    val subtitle = TextView(context).apply {
        text = "Welcome to Nova"
        textSize = 14f
        setTextColor(softWhite)
        gravity = Gravity.CENTER
        setPadding(0, 4, 0, 0)
    }

    header.addView(subtitle)

    root.addView(
        header,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    )

    // App grid
    val apps = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
    }

    apps.addView(
        createRow(
            app("☎", "Phone"),
            app("♙", "Contacts"),
            app("✉", "Messages")
        )
    )

    apps.addView(
        createRow(
            app("◉", "Camera"),
            app("▣", "Gallery"),
            app("□", "Files")
        )
    )

    apps.addView(
        createRow(
            app("＋", "Calculator"),
            app("▦", "Calendar"),
            app("◷", "Clock")
        )
    )

    apps.addView(
        createRow(
            app("☁", "Weather"),
            app("⌖", "Maps"),
            app("♫", "Music")
        )
    )

    apps.addView(
        createRow(
            app("✉", "Mail"),
            app("●", "Recorder"),
            app("◴", "Timer")
        )
    )

    apps.addView(
        createRow(
            app("✓", "Reminders"),
            app("◇", "Wallet"),
            app("◎", "Nova Store")
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

    // Bottom dock
    val dock = LinearLayout(context).apply {
        gravity = Gravity.CENTER
        setPadding(12, 10, 12, 10)

        background = roundedBackground(
            Color.argb(48, 255, 255, 255),
            34f
        )
    }

    dock.addView(
        createDockButton("Iris") {
            openScreen(
                IrisAssistantScreen(context) {
                    showHome()
                }
            )
        }
    )

    dock.addView(
        createDockButton("Settings") {
            openScreen(
                SettingsScreen(context) {
                    showHome()
                }
            )
        }
    )

    root.addView(
        dock,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            64
        )
    )

    return root
}

private fun createRow(
    first: View,
    second: View,
    third: View
): View {

    val row = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
    }

    row.addView(
        first,
        LinearLayout.LayoutParams(0, 88, 1f)
    )

    row.addView(
        second,
        LinearLayout.LayoutParams(0, 88, 1f)
    )

    row.addView(
        third,
        LinearLayout.LayoutParams(0, 88, 1f)
    )

    return row
}

private fun app(
    symbol: String,
    name: String
): View {

    val container = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        setPadding(5, 5, 5, 5)

        setOnClickListener {
            openApp(name)
        }
    }

    val icon = TextView(context).apply {
        text = symbol
        textSize = 22f
        setTextColor(white)
        gravity = Gravity.CENTER
        typeface = Typeface.DEFAULT_BOLD

        background = roundedBackground(
            Color.argb(58, 255, 255, 255),
            24f
        )
    }

    container.addView(
        icon,
        LinearLayout.LayoutParams(58, 58)
    )

    val label = TextView(context).apply {
        text = name
        textSize = 11f
        setTextColor(softWhite)
        gravity = Gravity.CENTER
        setPadding(0, 5, 0, 0)
    }

    container.addView(
        label,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            24
        )
    )

    return container
}

private fun createDockButton(
    name: String,
    action: () -> Unit
): TextView {

    return TextView(context).apply {
        text = name
        textSize = 13f
        setTextColor(white)
        gravity = Gravity.CENTER
        typeface = Typeface.DEFAULT_BOLD
        setPadding(20, 0, 20, 0)

        setOnClickListener {
            action()
        }
    }
}

private fun roundedBackground(
    color: Int,
    radius: Float
): GradientDrawable {

    return GradientDrawable().apply {
        setColor(color)
        cornerRadius = radius
    }
}

private fun openApp(name: String) {

    when (name) {

        "Phone" -> openScreen(
            PhoneScreen(context) { showHome() }
        )

        "Contacts" -> openScreen(
            ContactsScreen(context) { showHome() }
        )

        "Messages" -> openScreen(
            MessagesScreen(context) { showHome() }
        )

        "Camera" -> openScreen(
            CameraScreen(context) { showHome() }
        )

        "Gallery" -> openScreen(
            GalleryScreen(context) { showHome() }
        )

        "Files" -> openScreen(
            FilesScreen(context) { showHome() }
        )

        "Calculator" -> openScreen(
            CalculatorScreen(context) { showHome() }
        )

        "Calendar" -> openScreen(
            CalendarScreen(context) { showHome() }
        )

        "Clock" -> openScreen(
            ClockScreen(context) { showHome() }
        )

        "Weather" -> openScreen(
            WeatherScreen(context) { showHome() }
        )

        "Maps" -> openScreen(
            MapsScreen(context) { showHome() }
        )

        "Music" -> openScreen(
            MusicScreen(context) { showHome() }
        )

        "Mail" -> openScreen(
            MailScreen(context) { showHome() }
        )

        "Recorder" -> openScreen(
            RecorderScreen(context) { showHome() }
        )

        "Timer" -> openScreen(
            TimerScreen(context) { showHome() }
        )

        "Reminders" -> openScreen(
            RemindersScreen(context) { showHome() }
        )

        "Wallet" -> openScreen(
            WalletScreen(context) { showHome() }
        )

        "Nova Store" -> openScreen(
            NovaStoreScreen(context) { showHome() }
        )
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
