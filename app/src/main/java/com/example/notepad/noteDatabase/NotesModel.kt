package com.example.notepad.noteDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
data class NotesModel(
    var text:String, var fontBold: Boolean , var fontItalic:Boolean ,
    @PrimaryKey(autoGenerate = true) var id: Int=0)
