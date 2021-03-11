package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//In this file we create the entity
@Entity(tableName = "notes_table") //If we want to convert this class to an entity.
//So a table (name:notes_table) of this class will be formed inside SQLite database. 
class Note(@ColumnInfo(name = "text")val text: String) { 
    //Since we want columns here so use @ColumnInfo So different columns(Name of column=text) of text:String will be there
    @PrimaryKey(autoGenerate = true) var id = 0 // To generate id .Id will keep on auto generating.
}