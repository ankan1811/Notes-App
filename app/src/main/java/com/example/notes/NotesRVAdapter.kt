package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context ,private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()


    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)//text and deleteButton ids are present in item_note.xml
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note , parent , false))//context is passed to the adapter at the top
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = allNotes[position] //We set it into our holder
        holder.textView.text = currentNote.text//CurrentNote is our note.There is text item inside Note.kt

    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter { //Pass this as a listener in the adapter
    fun onItemClicked(note: Note)
}