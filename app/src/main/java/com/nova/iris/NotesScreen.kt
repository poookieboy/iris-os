package com.nova.iris

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class NotesScreen(
    private val context: Context,
    private val onBack: () -> Unit
) {

    private val prefs =
        context.getSharedPreferences("iris_notes", Context.MODE_PRIVATE)

    private val lavender = Color.rgb(105, 88, 178)
    private val dark = Color.rgb(32, 31, 48)
    private val soft = Color.rgb(244, 242, 251)

    fun create(): View {
        return buildList()
    }

    private fun buildList(): View {

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(22, 22, 22, 18)
        }

        val header = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
        }

        val back = TextView(context).apply {
            text = "‹"
            textSize = 42f
            setTextColor(dark)
            setPadding(0, 0, 22, 0)

            setOnClickListener {
                onBack()
            }
        }

        val title = TextView(context).apply {
            text = "Notes"
            textSize = 29f
            setTextColor(dark)
            typeface = Typeface.DEFAULT_BOLD
        }

        val add = TextView(context).apply {
            text = "+"
            textSize = 30f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER

            background = rounded(
                lavender,
                30f
            )

            setOnClickListener {
                showEditor(null)
            }
        }

        header.addView(back)

        header.addView(
            title,
            LinearLayout.LayoutParams(
                0,
                70,
                1f
            )
        )

        header.addView(
            add,
            LinearLayout.LayoutParams(
                58,
                58
            )
        )

        root.addView(header)

        val search = EditText(context).apply {
            hint = "Search notes"
            textSize = 16f
            setTextColor(dark)
            setHintTextColor(Color.GRAY)
            setSingleLine(true)
            setPadding(18, 0, 18, 0)

            background = rounded(
                Color.rgb(242, 240, 249),
                22f
            )
        }

        root.addView(
            search,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                58
            ).apply {
                topMargin = 14
                bottomMargin = 14
            }
        )

        val scroll = ScrollView(context)

        val list = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        renderNotes(list, "")

        scroll.addView(list)

        root.addView(
            scroll,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        search.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    renderNotes(
                        list,
                        s?.toString() ?: ""
                    )
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {
                }
            }
        )

        return root
    }

    private fun renderNotes(
        list: LinearLayout,
        query: String
    ) {

        list.removeAllViews()

        val notes = loadNotes()
        val q = query.trim().lowercase()

        var visibleCount = 0

        for (i in 0 until notes.length()) {

            val note = notes.getJSONObject(i)

            val title =
                note.optString("title", "Untitled")

            val body =
                note.optString("body", "")

            val searchable =
                "$title $body".lowercase()

            if (
                q.isEmpty() ||
                searchable.contains(q)
            ) {

                list.addView(
                    noteCard(note)
                )

                visibleCount++
            }
        }

        if (visibleCount == 0) {

            val empty = TextView(context).apply {

                text =
                    if (q.isEmpty()) {
                        "No notes yet\n\nTap + to create your first note."
                    } else {
                        "No matching notes"
                    }

                textSize = 18f
                setTextColor(Color.GRAY)
                gravity = Gravity.CENTER

                setPadding(
                    20,
                    90,
                    20,
                    90
                )
            }

            list.addView(empty)
        }
    }

    private fun noteCard(
        note: JSONObject
    ): View {

        val id =
            note.optString("id")

        val card = LinearLayout(context).apply {

            orientation = LinearLayout.VERTICAL

            setPadding(
                18,
                16,
                18,
                16
            )

            background = rounded(
                soft,
                22f
            )

            setOnClickListener {
                showEditor(id)
            }
        }

        val title = TextView(context).apply {

            text =
                note.optString(
                    "title",
                    "Untitled"
                )

            textSize = 19f
            setTextColor(dark)
            typeface = Typeface.DEFAULT_BOLD
        }

        val body = TextView(context).apply {

            text =
                note.optString(
                    "body",
                    ""
                )

            textSize = 14f
            setTextColor(Color.DKGRAY)

            maxLines = 2

            setPadding(
                0,
                6,
                0,
                0
            )
        }

        card.addView(title)
        card.addView(body)

        return card.apply {

            layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 12
                }
        }
    }

    private fun showEditor(
        existingId: String?
    ) {

        val existing =
            existingId?.let {
                findNote(it)
            }

        val root = LinearLayout(context).apply {

            orientation = LinearLayout.VERTICAL

            setBackgroundColor(
                Color.WHITE
            )

            setPadding(
                22,
                22,
                22,
                18
            )
        }

        val header = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
        }

        val back = TextView(context).apply {

            text = "‹"
            textSize = 42f
            setTextColor(dark)

            setPadding(
                0,
                0,
                22,
                0
            )

            setOnClickListener {

                (context as? MainActivity)
                    ?.setContentView(
                        buildList()
                    )
            }
        }

        val heading = TextView(context).apply {

            text =
                if (existing == null) {
                    "New Note"
                } else {
                    "Edit Note"
                }

            textSize = 25f
            setTextColor(dark)
            typeface = Typeface.DEFAULT_BOLD
        }

        val titleInput = EditText(context).apply {

            hint = "Title"

            setText(
                existing?.optString(
                    "title",
                    ""
                ) ?: ""
            )

            textSize = 23f
            setTextColor(dark)
            setHintTextColor(Color.GRAY)

            setSingleLine(true)

            background = null

            typeface =
                Typeface.DEFAULT_BOLD
        }

        val bodyInput = EditText(context).apply {

            hint = "Start writing..."

            setText(
                existing?.optString(
                    "body",
                    ""
                ) ?: ""
            )

            textSize = 17f

            setTextColor(dark)
            setHintTextColor(Color.GRAY)

            gravity =
                Gravity.TOP

            background = null

            setPadding(
                0,
                10,
                0,
                10
            )
        }

        val save = TextView(context).apply {

            text = "Save"
            textSize = 16f
            setTextColor(lavender)

            typeface =
                Typeface.DEFAULT_BOLD

            gravity =
                Gravity.CENTER

            setOnClickListener {

                saveNote(
                    existingId,
                    titleInput.text
                        .toString()
                        .trim()
                        .ifEmpty {
                            "Untitled"
                        },
                    bodyInput.text
                        .toString()
                        .trim()
                )

                (context as? MainActivity)
                    ?.setContentView(
                        buildList()
                    )
            }
        }

        header.addView(back)

        header.addView(
            heading,
            LinearLayout.LayoutParams(
                0,
                70,
                1f
            )
        )

        header.addView(
            save,
            LinearLayout.LayoutParams(
                72,
                58
            )
        )

        root.addView(header)

        root.addView(
            titleInput,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                62
            )
        )

        root.addView(
            bodyInput,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        if (existing != null) {

            val delete =
                TextView(context).apply {

                    text = "Delete note"
                    textSize = 16f

                    setTextColor(
                        Color.rgb(
                            190,
                            55,
                            70
                        )
                    )

                    gravity =
                        Gravity.CENTER

                    setOnClickListener {

                        deleteNote(
                            existingId
                        )

                        (context as? MainActivity)
                            ?.setContentView(
                                buildList()
                            )
                    }
                }

            root.addView(
                delete,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    56
                )
            )
        }

        (context as? MainActivity)
            ?.setContentView(root)

        titleInput.requestFocus()

        (
            context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as? InputMethodManager
        )?.showSoftInput(
            titleInput,
            InputMethodManager.SHOW_IMPLICIT
        )
    }

    private fun loadNotes(): JSONArray {

        return try {

            JSONArray(
                prefs.getString(
                    "notes",
                    "[]"
                ) ?: "[]"
            )

        } catch (_: Exception) {

            JSONArray()
        }
    }

    private fun saveNote(
        id: String?,
        title: String,
        body: String
    ) {

        val notes =
            loadNotes()

        val noteId =
            id ?: System.currentTimeMillis()
                .toString()

        if (id == null) {

            notes.put(
                JSONObject().apply {

                    put(
                        "id",
                        noteId
                    )

                    put(
                        "title",
                        title
                    )

                    put(
                        "body",
                        body
                    )
                }
            )

        } else {

            for (
                i in 0 until notes.length()
            ) {

                val note =
                    notes.getJSONObject(i)

                if (
                    note.optString(
                        "id"
                    ) == id
                ) {

                    note.put(
                        "title",
                        title
                    )

                    note.put(
                        "body",
                        body
                    )

                    break
                }
            }
        }

        prefs.edit()
            .putString(
                "notes",
                notes.toString()
            )
            .apply()
    }

    private fun findNote(
        id: String
    ): JSONObject? {

        val notes =
            loadNotes()

        for (
            i in 0 until notes.length()
        ) {

            val note =
                notes.getJSONObject(i)

            if (
                note.optString(
                    "id"
                ) == id
            ) {
                return note
            }
        }

        return null
    }

    private fun deleteNote(
        id: String?
    ) {

        if (id == null) return

        val old =
            loadNotes()

        val fresh =
            JSONArray()

        for (
            i in 0 until old.length()
        ) {

            val note =
                old.getJSONObject(i)

            if (
                note.optString(
                    "id"
                ) != id
            ) {
                fresh.put(note)
            }
        }

        prefs.edit()
            .putString(
                "notes",
                fresh.toString()
            )
            .apply()
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
