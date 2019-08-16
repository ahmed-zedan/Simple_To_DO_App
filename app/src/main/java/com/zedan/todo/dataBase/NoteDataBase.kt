package com.zedan.todo.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase(){

    abstract val noteDao : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDataBase? = null

        fun getInstance(context: Context) : NoteDataBase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDataBase::class.java,
                        "notes_to_do_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}