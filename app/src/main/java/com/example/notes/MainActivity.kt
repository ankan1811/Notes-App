package com.example.notes


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel //We need to create an instance of a view model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)//recyclerView is the id of the recycler view in activity_main.xml
        val adapter = NotesRVAdapter(this,this)//context and listener and NotesRVAdapter.kt is also present
        recyclerView.adapter = adapter
            //This IS THE LIFECYCLE OWNER
        viewModel = ViewModelProvider(this, //We need to bring in the viewModel to the mainActivity using ViewModelProvider class
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)//We need the view Model of NoteViewModel

        viewModel.allNotes.observe(this, Observer {list -> //To get live data from view model
              //This IS THE LIFECYCLE OWNER
              //It is life cycle aware i.ie. the data which it brings( It knows that when the data will be given to activity and when not).
            //It checks if activity is live or not, visible on screen or not, crashed or not (closed or not).
           list?.let{
               //If the list is not null then update the adapter
               adapter.updateList(it) //updateList is in NotesRVAdapter.kt
           }

        })


    }

    override fun onItemClicked(note: Note) {
       viewModel.deleteNote(note) //Just call the delte function from viewModel as activity will only interact with view Model
        Toast.makeText(this,"${note.text} Deleted " , Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()) { //If the text is not empty we will call the insertNote function..
            //We will create an object Note(We pass the noteText above)
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Inserted" ,Toast.LENGTH_LONG).show()
        }
    }
}
