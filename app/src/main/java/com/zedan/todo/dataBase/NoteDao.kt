package com.zedan.todo.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

/*
 * use suspend fun to make sure function not run on main thread.
 */
@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY _id DESC")
    fun getAllNotes() : LiveData<List<Note>>
}