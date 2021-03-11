package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*
//In this file we create the dao
@Dao //To create the interface as dao
interface NoteDao {
    //On conflict means if two same things are inserted in a column then in this case the strategy will be to just ignore it
    @Insert(onConflict = OnConflictStrategy.IGNORE) //@Insert indicates the insert query.So it will insert..Taken from codelab
    suspend fun insert(note: Note) //Function to insert a note

    @Delete
    suspend fun delete(note: Note)//Function to delete a note
    
//Suspend means that the functions of insert and delete can
// be called from the background thread or from some other suspend function(not from a normal function).


    @Query("Select * from notes_table order by id ASC") //notes_table was the name 
    //We want the notes according to the ascending order of the ids.


    fun getAllNotes(): LiveData<List<Note>> //Function to fetch all notes
    //If our table is changed (like insert,delete) and we want our activity to know about it then we need it to be a live data
    //It will return a list of notes
}
//All the functions of insert,delete will work in the background thread so that the main UI thread does not get blocked.
//otherwise exception will be thrown
//Insert and Delete are IO operations very heavy 
//So we will use suspend which is a coroutine.
//With the help of coroutines we can perform background tasks very easily.