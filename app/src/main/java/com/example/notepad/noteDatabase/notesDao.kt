package com.example.notepad.noteDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface notesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note : NotesModel)
    @Query("select * from NotesTable")
    fun getNotes():List<NotesModel>
    @Delete
    fun deleteNotes(note : NotesModel)
}