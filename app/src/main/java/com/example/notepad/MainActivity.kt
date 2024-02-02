package com.example.notepad

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.noteDatabase.NotesDatabase
import com.example.notepad.noteDatabase.NotesModel
import com.example.notepad.notesRecycleView.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var database: NotesDatabase
    private lateinit var recyclerView: RecyclerView
    val adapter = NotesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycle_view)
        database = NotesDatabase.getDatabase(this)
        val extras = intent.extras
        var exampleString = extras?.getString("NoteBody")
        var exampleBold = extras?.getBoolean("isBold")
        var exampleItalic = extras?.getBoolean("isItalic")
        var temp = exampleString
        if (temp != null && exampleBold != null && exampleItalic != null) {
            val notesModel = NotesModel(temp.toString(), exampleBold, exampleItalic)
            database.notesDao.insertNote(notesModel)
        }
        adapter.setList(database.notesDao.getNotes().reversed())

        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerView)
        setUpRv()
        val writeButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        writeButton.setOnClickListener {
            val intent = Intent(this, WritingNotes::class.java)
            startActivity(intent)
        }

    }

    fun setUpRv() {

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.smoothScrollToPosition(0)


    }

    var callback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val deletedItem =
                database.notesDao.getNotes().reversed().get(viewHolder.absoluteAdapterPosition)
            database.notesDao.deleteNotes(deletedItem)
            adapter.setList(database.notesDao.getNotes().reversed())
            adapter.notifyDataSetChanged()
        }
    }
}