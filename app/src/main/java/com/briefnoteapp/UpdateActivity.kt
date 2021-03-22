package com.briefnoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateActivity : AppCompatActivity() {

    lateinit var noteTitles: String
    lateinit var noteDescriptions: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val DB = SQHelper(this)
        val noteTitle = findViewById<EditText>(R.id.add_note_title)
        val noteDescription = findViewById<EditText>(R.id.add_note_description)
        val addNoteButton = findViewById<Button>(R.id.add_note_here_btn)

        noteTitles = getIntent().getStringExtra("title").toString()
        noteDescriptions = getIntent().getStringExtra("desc").toString()

        noteTitle.setText(noteTitles)
        noteDescription.setText(noteDescriptions)


        addNoteButton.setOnClickListener {

            DB.updateNote(noteTitle.toString(), noteDescription.toString())

            //DB.updateNote(noteTitle.text.toString(), noteDescription.text.toString())

            //DB.updateNote(noteTitles, noteTitle.text.toString(), noteDescription.text.toString())

            Toast.makeText(this@UpdateActivity, "Updated successfully", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this@UpdateActivity, MainActivity::class.java))

            finish()
        }

    }
}
