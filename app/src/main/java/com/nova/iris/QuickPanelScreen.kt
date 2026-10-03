package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView

class QuickPanelScreen(
    private val context: Context,
    private val onClose: () -> Unit
) {

    private var wifiOn = true
    private var bluetoothOn = true
    private var mobileOn = true
    private var airplaneOn = false
    private var flashlightOn = false
    private var dndOn = false

    fun create(): View {

        val root = LinearLayout(context).apply {

            orientation = LinearLayout.VERTICAL

            setPadding(
                20,
                18,
                20,
                20
            )

            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(
                    Color.rgb(18, 22, 48),
                    Color.rgb(54, 48, 105),
                    Color.rgb(105, 78, 160)
                )
            )
        }

        // Top bar
        val top = LinearLayout(context).apply {

            gravity = Gravity.CENTER_VERTICAL
        }

        val title = TextView(context).apply {

            text = "Iris"

            textSize = 28f

            setTextColor(
                Color.WHITE
            )

            typeface =
                Typeface.DEFAULT_BOLD
        }

        top.addView(
            title,
            LinearLayout.LayoutParams(
                0,
                60,
                1f
            )
        )

        val close = TextView(context).apply {

            text = "×"

            textSize = 30f

            setTextColor(
                Color.WHITE
            )

            gravity = Gravity.CENTER

            setOnClickListener {
                onClose()
            }
        }

        top.addView(
            close,
            LinearLayout.LayoutParams(
                55,
                55
            )
        )

        root.addView(top)

        // Time and date
        val info = LinearLayout(context).apply {

            orientation =
                LinearLayout.VERTICAL

            setPadding(
                4,
                5,
                4,
                18
            )
        }

        val time = TextView(context).apply {

            text = getCurrentTime()

            textSize = 46f

            setTextColor(
                Color.WHITE
            )

            typeface = Typeface.create(
                "sans-serif-light",
                Typeface.NORMAL
            )
        }

        info.addView(time)

        val date = TextView(context).apply {

            text = getCurrentDate()

            textSize = 14f

            setTextColor(
                Color.rgb(
                    218,
                    220,
                    240
                )
            )
        }

        info.addView(date)

        root.addView(info)

        // Quick controls
        val controls = LinearLayout(context).apply {

            orientation =
                LinearLayout.VERTICAL
        }

        addControlRow(
            controls,
            createToggle(
                "Wi-Fi",
                "Connected",
                wifiOn
            ) {
                wifiOn = !wifiOn
            },
            createToggle(
                "Bluetooth",
                if (bluetoothOn)
                    "On"
                else
                    "Off",
                bluetoothOn
            ) {
                bluetoothOn = !bluetoothOn
            }
        )

        addControlRow(
            controls,
            createToggle(
                "Mobile Data",
                if (mobileOn)
                    "4G"
                else
                    "Off",
                mobileOn
            ) {
                mobileOn = !mobileOn
            },
            createToggle(
                "Airplane",
                if (airplaneOn)
                    "On"
                else
                    "Off",
                airplaneOn
            ) {
                airplaneOn = !airplaneOn
            }
        )

        addControlRow(
            controls,
            createToggle(
                "Flashlight",
                if (flashlightOn)
                    "On"
                else
                    "Off",
                flashlightOn
            ) {
                flashlightOn = !flashlightOn
            },
            createToggle(
                "Do Not Disturb",
                if (dndOn)
                    "On"
                else
                    "Off",
                dndOn
            ) {
                dndOn = !dndOn
            }
        )

        root.addView(
            controls,
            LinearLayout.LayoutParams(
                -1,
                0,
                1f
            )
        )

        // Brightness
        root.addView(
            sliderSection(
                "Brightness",
                75
            )
        )

        // Volume
        root.addView(
            sliderSection(
                "Volume",
                55
            )
        )

        // Notification section
        val notificationTitle =
            TextView(context).apply {

                text = "Notifications"

                textSize = 19f

                setTextColor(
                    Color.WHITE
                )

                typeface =
                    Typeface.DEFAULT_BOLD

                setPadding(
                    4,
                    16,
                    4,
                    8
                )
            }

        root.addView(
            notificationTitle
        )

        root.addView(
            notificationCard(
                "Iris OS",
                "Nova is ready."
            )
        )

        root.addView(
            notificationCard(
                "System",
                "Battery 100% • 4G connected"
            )
        )

        return root
    }

    private fun createToggle(
        name: String,
        status: String,
        enabled: Boolean,
        action: () -> Unit
    ): View {

        val card = LinearLayout(context).apply {

            orientation =
                LinearLayout.VERTICAL

            gravity =
                Gravity.CENTER

            setPadding(
                10,
                10,
                10,
                10
            )

            background = rounded(
                if (enabled) {
                    Color.rgb(
                        105,
                        92,
                        205
                    )
                } else {
                    Color.argb(
                        45,
                        255,
                        255,
                        255
                    )
                },
                24f
            )

            setOnClickListener {

                action()

                // Rebuild the panel so the state is visible.
                (context as? MainActivity)
                    ?.showQuickPanel()
            }
        }

        val icon = TextView(context).apply {

            text = when (name) {
                "Wi-Fi" -> "⌁"
                "Bluetooth" -> "ᛒ"
                "Mobile Data" -> "▥"
                "Airplane" -> "✈"
                "Flashlight" -> "☼"
                else -> "◐"
            }

            textSize = 24f

            setTextColor(
                Color.WHITE
            )

            gravity =
                Gravity.CENTER
        }

        card.addView(
            icon,
            LinearLayout.LayoutParams(
                -1,
                38
            )
        )

        val label = TextView(context).apply {

            text = name

            textSize = 12f

            setTextColor(
                Color.WHITE
            )

            typeface =
                Typeface.DEFAULT_BOLD

            gravity =
                Gravity.CENTER
        }

        card.addView(label)

        val small = TextView(context).apply {

            text = status

            textSize = 9f

            setTextColor(
                Color.rgb(
                    220,
                    221,
                    240
                )
            )

            gravity =
                Gravity.CENTER
        }

        card.addView(small)

        return card
    }

    private fun addControlRow(
        parent: LinearLayout,
        left: View,
        right: View
    ) {

        val row = LinearLayout(context).apply {

            orientation =
                LinearLayout.HORIZONTAL

            setPadding(
                0,
                4,
                0,
                4
            )
        }

        row.addView(
            left,
            LinearLayout.LayoutParams(
                0,
                108,
                1f
            ).apply {
                rightMargin = 5
            }
        )

        row.addView(
            right,
            LinearLayout.LayoutParams(
                0,
                108,
                1f
            ).apply {
                leftMargin = 5
            }
        )

        parent.addView(row)
    }

    private fun sliderSection(
        label: String,
        initial: Int
    ): View {

        val container =
            LinearLayout(context).apply {

                orientation =
                    LinearLayout.VERTICAL

                setPadding(
                    4,
                    5,
                    4,
                    5
                )
            }

        val title =
            TextView(context).apply {

                text = label

                textSize = 13f

                setTextColor(
                    Color.WHITE
                )

                typeface =
                    Typeface.DEFAULT_BOLD
            }

        container.addView(title)

        val slider =
            SeekBar(context).apply {

                progress = initial

                max = 100
            }

        container.addView(
            slider,
            LinearLayout.LayoutParams(
                -1,
                42
            )
        )

        return container
    }

    private fun notificationCard(
        titleText: String,
        message: String
    ): View {

        val card =
            LinearLayout(context).apply {

                orientation =
                    LinearLayout.VERTICAL

                setPadding(
                    18,
                    13,
                    18,
                    13
                )

                background = rounded(
                    Color.argb(
                        42,
                        255,
                        255,
                        255
                    ),
                    22f
                )
            }

        val title =
            TextView(context).apply {

                text = titleText

                textSize = 13f

                setTextColor(
                    Color.WHITE
                )

                typeface =
                    Typeface.DEFAULT_BOLD
            }

        card.addView(title)

        val body =
            TextView(context).apply {

                text = message

                textSize = 12f

                setTextColor(
                    Color.rgb(
                        218,
                        220,
                        240
                    )
                )

                setPadding(
                    0,
                    4,
                    0,
                    0
                )
            }

        card.addView(body)

        val params =
            LinearLayout.LayoutParams(
                -1,
                65
            )

        params.bottomMargin = 7

        return card.apply {
            layoutParams = params
        }
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

    private fun getCurrentTime(): String {

        return java.text.SimpleDateFormat(
            "HH:mm",
            java.util.Locale.getDefault()
        ).format(
            java.util.Date()
        )
    }

    private fun getCurrentDate(): String {

        return java.text.SimpleDateFormat(
            "EEEE, d MMMM",
            java.util.Locale.getDefault()
        ).format(
            java.util.Date()
        )
    }
}
