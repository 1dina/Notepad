package com.example.notepad.noteDatabase

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NotesModel::class], version = 2)
abstract class NotesDatabase : RoomDatabase(){
    abstract val  notesDao : notesDao
    companion object{
        private  var INSTANCE : NotesDatabase? = null
         fun getDatabase(context: Context) : NotesDatabase{
             val temp = INSTANCE
             if(temp != null) return temp
             synchronized(this){
                 val instance = Room.databaseBuilder(context.applicationContext
                     ,NotesDatabase::class.java,"notesDb")
                     .fallbackToDestructiveMigration()
                     .allowMainThreadQueries()
                     .build()
                 INSTANCE = instance
                 return instance
             }



         }
    }

}