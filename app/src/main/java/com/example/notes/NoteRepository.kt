package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) { //Now this repo will bring all the data to the view Model .Viewmodel does not need to worry

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes() //We get the data from getAllNotes() function in NoteDao.kt

    suspend fun insert(note: Note) {
         noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

}//Now the data can come from anywhere-> API calls, another app or database. All this data will talk to the repository
// at a single place so that our app does not have to talk to data at different places .
//It is single source from where we bring in data.
//Although in this case all data are coming from DAO but in other cases data can come from API also