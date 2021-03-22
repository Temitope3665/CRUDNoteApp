package com.briefnoteapp

import android.content.Intent
import android.database.Cursor
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.show_note_layout.*

class MainActivity : AppCompatActivity(), NoteAdapter.onItemClickListener {

    lateinit var lists: ArrayList<Notes>
    lateinit var DB: SQHelper
    lateinit var data: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addNoteButton = findViewById<ImageView>(R.id.add_note_btn)

        addNoteButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNote::class.java))
        }


        lists = ArrayList<Notes>()
        DB = SQHelper(applicationContext)
        data = DB.fetch_data


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = NoteAdapter(lists, this)

        ShowData()

        recycler_view.layoutManager = GridLayoutManager(applicationContext, 2)


        recycler_view.adapter = adapter

        adapter.notifyDataSetChanged()

    }


    fun ShowData() {
        if (data.count == 0) {
            Toast.makeText(this@MainActivity, "No record found", Toast.LENGTH_SHORT).show()
        }
        while (data.moveToNext()) {
            lists.add(Notes(data.getString(0),
            data.getString(1),
            data.getString(2)))
        }
    }

    override fun onStart() {
        super.onStart()
        ShowData()
    }

    override fun onItemClick(data: Notes, position: Int) {
        /*
        DB = SQHelper(applicationContext)
        //val item = deleteDatabase("id")
        DB.DeleteData("id = ?")
         */

        // Delete Note
        var DB = SQHelper(applicationContext)
        val selectionArgs = arrayOf(data.showID.toString())
        DB.delete("id = ?", selectionArgs)

        finish()
        overridePendingTransition(0, 0)
        startActivity(getIntent())
        overridePendingTransition(0, 0)
        finish()
        //startActivity(getIntent())

        Toast.makeText(applicationContext, "Deleted successfully", Toast.LENGTH_SHORT).show()

    }

    override fun onDescriptionClick(data: Notes, position: Int) {

        val intUpdate = Intent(applicationContext, UpdateActivity::class.java)
        intUpdate.putExtra("id", data.showID)
        intUpdate.putExtra("title", data.showTitle)
        intUpdate.putExtra("desc", data.showDescription)
        startActivity(intUpdate)
        finish()

    }




}
