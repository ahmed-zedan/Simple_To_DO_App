package com.zedan.todo.addScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zedan.todo.dataBase.NoteDao

class AddScreenViewModelFactory(private val data : NoteDao) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddScreenViewModel::class.java)){
            return AddScreenViewModel(data) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}