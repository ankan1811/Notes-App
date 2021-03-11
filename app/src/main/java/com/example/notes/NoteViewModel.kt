package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository //We get the repository from here to be used in this view model
    val allNotes: LiveData<List<Note>> //LiveData is present in view model


    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()//application here is the context Just get the dao
        repository = NoteRepository(dao)
        allNotes = repository.allNotes //allNotes is present in the activity
    }
    //viewModelScope.launch(Dispatchers.IO) is a coroutine...
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){ //IO beacuse in this thread we are doing an input output operation.
        repository.delete(note) //Whtever is written here i.e. inside launch will work in the background thread(In Coroutine scope)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}