package com.example.notes //Entry point in our db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false) //arrayOf(entity::class) Note is entity
//Everytime we are updating the schema in our database we have to update our version.
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao //From the database we will get the dao(Notedao).This function will return the dao

    companion object { //Database will bring all the data to the repository

        //We will convert the database to singleton we do not want multiple instances of it
        //  as it will do many heavy tasks  (like before). Use companion object
        @Volatile
        private var INSTANCE: NoteDatabase? = null //We create an instance of class NoteDatabase assign it to null

        fun getDatabase(context: Context): NoteDatabase { //It will return the instance
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) { //synchronized lock so that other threads cannot access it
                val instance = Room.databaseBuilder( //For creating an instance we use Room.databaseBuilder 
                    context.applicationContext,//Room database is an abstract class so we cannot directly create an object.
                    NoteDatabase::class.java,
                    "note_database" //name of the database
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}