package com.zedan.todo.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zedan.todo.dataBase.NoteDao

class MainViewModel(private val data : NoteDao) : ViewModel(){
    // To navigating to Add Screen
    private val _navigateToAdd = MutableLiveData<Boolean?>()
    val navigateToAdd : LiveData<Boolean?>
        get() = _navigateToAdd

    //when Click To + Button navigate to AddScreen
    fun navToAdd() { _navigateToAdd.value = true}

    //when navigate to AddScreen has Occur reset the Value to null
    fun doneNavigate(){ _navigateToAdd.value = null }

    //Get All Data from Database
    val notes = data.getAllNotes()

}