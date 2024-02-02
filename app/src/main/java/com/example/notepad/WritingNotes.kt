package com.example.notepad

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import kotlin.math.log


class WritingNotes : AppCompatActivity() {
    lateinit var text: String
    var size: Float = 0F
    var isBold = false
    var isItalic = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing_notes)
        val fontTypes = findViewById<ConstraintLayout>(R.id.fontTypes)
        val fontSizes = findViewById<ConstraintLayout>(R.id.`fontٍSize`)
        val typeButton = findViewById<ImageView>(R.id.pen)
        val sizeButton = findViewById<ImageView>(R.id.a_color)
        val editSize = findViewById<EditText>(R.id.editFont)
        val boldType: ImageView = findViewById(R.id.bold)
        val italicType: ImageView = findViewById(R.id.italic)
        val underLineType: ImageView = findViewById(R.id.underline)
        val editText = findViewById<EditText>(R.id.editText)
        //editSize.isFocusableInTouchMode = true
        // editSize.requestFocus()
        typeButton.setOnClickListener {
            fontSizes.visibility = View.INVISIBLE
            if (fontTypes.isVisible) {
                fontTypes.visibility = View.INVISIBLE
            } else fontTypes.visibility = View.VISIBLE
        }
        sizeButton.setOnClickListener {
            fontTypes.visibility = View.INVISIBLE
            if (fontSizes.isVisible) {
                fontSizes.visibility = View.INVISIBLE
            } else {
                fontSizes.visibility = View.VISIBLE
            }
        }
        editSize.setOnKeyListener(OnKeyListener { v, keyCode, event -> // If the event is a key-down event on the "enter" button
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                size = editSize.text.toString().toFloat()
                fontSizes.visibility = View.INVISIBLE
                if (size < 8F) Toast.makeText(
                    this,
                    "Please enter number above 8 !",
                    Toast.LENGTH_SHORT
                ).show()
                else if (size > 100F) Toast.makeText(
                    this,
                    "Please enter number below 100 !",
                    Toast.LENGTH_SHORT
                ).show()
                else editText.textSize = size
                return@OnKeyListener true
            }
            false
        })
        val doneButton = findViewById<ImageView>(R.id.doneImage)
        editText.setOnClickListener {
            fontTypes.visibility = View.INVISIBLE
            fontSizes.visibility = View.INVISIBLE
        }
        doneButton.setOnClickListener {
            text = editText.text.toString()
            if (text != "") {
                isBold = editText.typeface.isBold
                isItalic = editText.typeface.isItalic
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("NoteBody", text)
                intent.putExtra("isBold", isBold)
                intent.putExtra("isItalic", isItalic)
                startActivity(intent)
            }
        }
        boldType.setOnClickListener {
            fontTypes.visibility = View.INVISIBLE
            editText.setTypeface(null, Typeface.BOLD)

        }
        italicType.setOnClickListener {
            fontTypes.visibility = View.INVISIBLE
            editText.setTypeface(null, Typeface.ITALIC)


        }
        underLineType.setOnClickListener {
            fontTypes.visibility = View.INVISIBLE
            editText.setTypeface(null, Typeface.NORMAL)

        }

    }


}






