package com.briefnoteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val DB = SQHelper(applicationContext)
        val noteTitle = findViewById<EditText>(R.id.add_note_title)
        val noteDescription = findViewById<EditText>(R.id.add_note_description)
        val addNoteButton = findViewById<Button>(R.id.add_note_here_btn)


        addNoteButton.setOnClickListener {
            val inputTitle = noteTitle.text.toString().trim()
            val inputDescription = noteDescription.text.toString().trim()

            if (inputTitle.isEmpty()) {
                noteTitle.error = "Field must not be empty"
                Toast.makeText(this@AddNote, "Field must not be empty", Toast.LENGTH_SHORT).show()
            } else if (inputDescription.isEmpty()) {
                noteDescription.error = "Field must not be empty"
                Toast.makeText(this@AddNote, "Field must not be empty", Toast.LENGTH_SHORT).show()
            } else if (inputTitle.isEmpty() && inputDescription.isEmpty()) {
                noteDescription.error = "Both fields must not be empty"
                Toast.makeText(this@AddNote, "Both fields must not be empty", Toast.LENGTH_SHORT).show()
            } else {
                DB.ADD_DATA(inputTitle, inputDescription)
                Toast.makeText(this@AddNote, "Note successfully added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@AddNote, MainActivity::class.java))
                finish()
            }
        }




        /*
        addNoteButton.setOnClickListener {
            //val getTitle = intent.getStringExtra("title").toString()
            //val getDesc = intent.getStringExtra("desc").toString()

            //val getTitle = noteTitle.text.toString().trim()
            //val getDesc = noteDescription.text.toString().trim()
        }
         */



        /*
        val getTitle = getIntent().getStringExtra("title")
        val getDescription = getIntent().getStringExtra("desc")


        DB.update(getTitle.toString(), getDescription.toString())
        Toast.makeText(this@AddNote, "Updated successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@AddNote, MainActivity::class.java))

         */




    }
}
