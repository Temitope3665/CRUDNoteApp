package com.briefnoteapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQHelper (context: Context) : SQLiteOpenHelper(context, DB_name, null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TB_name(ID INTEGER PRIMARY KEY AUTOINCREMENT, N_title TEXT, N_description TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TB_name")
    }


    // function to add data
    fun ADD_DATA(text_title: String, text_desc: String) {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(title, text_title)
        values.put(desc, text_desc)

        DB.insert(TB_name, null, values)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int {
        val DB = this.writableDatabase
        val count = DB!!.delete(TB_name, selection, selectionArgs)
        return count
    }

    /*
    // Update
    fun update (text_title: String, text_desc: String) {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(title, text_title)
        values.put(desc, text_desc)
        DB.update(TB_name, values, "id = ?", null)
        DB.close()
    }

     */

    /*
    fun update(note: Notes) : Int {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(id, note.showID)
        values.put(title, note.showTitle)
        values.put(desc, note.showDescription)

        val approve = DB.update(TB_name, values, "id = ?", arrayOf(note.showID))
        DB.close()
        return approve
    }
     */

    fun updateNote(text_title: String?, text_desc: String?) {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(title, text_title)
        values.put(desc, text_desc)
        DB.update(TB_name, values, "id = ?", null)
        //DB.update(TB_name, values, "id = ?", arrayOf())
    }


    /*
    fun updateNote(values: ContentValues, selection: String, selectionargs: Array<String>): Int {
        val DB = this.writableDatabase
        val uodateNote = DB!!.update(TB_name, values, selection, selectionargs)
        return uodateNote
    }
     */

    /*
    fun deleteData() {
        val db = this.writableDatabase
        db.delete(TB_name, id + "=?", arrayOf(1.toString()))
        db.close()
    }

     */


    // Here, we fetch the data with cursor i.e select * from table name
    val fetch_data: Cursor get() {
        val DB = this.writableDatabase
        val data = DB.rawQuery("select * from " + TB_name, null)
        return data
    }

    companion object {
        val DB_name = "notes.db "
        val TB_name = "Notes "
        val id = "ID"
        val title = "N_title"
        val desc = "N_description"
    }

}