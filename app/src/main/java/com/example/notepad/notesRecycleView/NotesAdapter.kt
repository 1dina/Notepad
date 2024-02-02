package com.example.notepad.notesRecycleView

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.MainActivity
import com.example.notepad.R
import com.example.notepad.noteDatabase.NotesModel
import kotlinx.coroutines.currentCoroutineContext

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    lateinit var notesList : List<NotesModel>
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var text = itemView.findViewById<TextView>(R.id.cardText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_card_view,parent,false))

    }


    fun setList(list: List<NotesModel>){
        notesList = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
       return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.setText(notesList.get(position).text)
        Log.i("Lets",notesList.get(position).fontBold.toString())
        if(notesList.get(position).fontBold)
        holder.text.setTypeface(null,Typeface.BOLD)
        if(notesList.get(position).fontItalic)
            holder.text.setTypeface(null,Typeface.ITALIC)
    }

}