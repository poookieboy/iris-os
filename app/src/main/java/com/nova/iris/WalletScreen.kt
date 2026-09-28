package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class WalletScreen(
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
            text = "Wallet"
            textSize = 28f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }

        header.addView(back)
        header.addView(title)

        root.addView(header)

        val balanceTitle = TextView(context).apply {
            text = "Available Balance"
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(0, 50, 0, 10)
        }

        root.addView(balanceTitle)

        val balance = TextView(context).apply {
            text = "KSh 0.00"
            textSize = 42f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        root.addView(
            balance,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                100
            )
        )

        val actions = LinearLayout(context).apply {
            gravity = Gravity.CENTER
            setPadding(0, 30, 0, 30)
        }

        val send = createAction(
            "Send",
            "Transfer money"
        )

        val receive = createAction(
            "Receive",
            "Get money"
        )

        val cards = createAction(
            "Cards",
            "Manage cards"
        )

        actions.addView(
            send,
            LinearLayout.LayoutParams(0, 110, 1f)
        )

        actions.addView(
            receive,
            LinearLayout.LayoutParams(0, 110, 1f)
        )

        actions.addView(
            cards,
            LinearLayout.LayoutParams(0, 110, 1f)
        )

        root.addView(actions)

        val transactionsTitle = TextView(context).apply {
            text = "Recent Transactions"
            textSize = 20f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 20, 0, 20)
        }

        root.addView(transactionsTitle)

        val transactions = TextView(context).apply {
            text = "No transactions yet"
            textSize = 17f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        root.addView(
            transactions,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val security = TextView(context).apply {
            text = "Wallet protected by Iris Security"
            textSize = 14f
            setTextColor(Color.GRAY)
            gravity = Gravity.CENTER
            setPadding(20, 20, 20, 20)
        }

        root.addView(security)

        return root
    }

    private fun createAction(
        titleText: String,
        subtitleText: String
    ): View {

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(8, 8, 8, 8)
            setBackgroundColor(Color.LTGRAY)
        }

        val title = TextView(context).apply {
            text = titleText
            textSize = 17f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val subtitle = TextView(context).apply {
            text = subtitleText
            textSize = 12f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(subtitle)

        return layout
    }
}
