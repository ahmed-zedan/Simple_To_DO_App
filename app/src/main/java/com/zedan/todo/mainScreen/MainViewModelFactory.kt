package com.zedan.todo.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zedan.todo.dataBase.NoteDao

class MainViewModelFactory(private val data : NoteDao):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(data) as T
        }
        throw IllegalArgumentException("unknown ViewModel class.")
    }
}