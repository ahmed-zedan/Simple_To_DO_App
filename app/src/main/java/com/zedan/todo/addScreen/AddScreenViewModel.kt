package com.zedan.todo.addScreen

import android.util.Log
import androidx.lifecycle.*
import com.zedan.todo.dataBase.Note
import com.zedan.todo.dataBase.NoteDao
import kotlinx.coroutines.*

class AddScreenViewModel(private val data : NoteDao) : ViewModel()  {

    private var viewModelJob = Job()

    //Scope to run Corutines has run in background
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _note = MutableLiveData<Note?>()

    fun setNote(note: Note){
        _note.value = note
    }

    // When User Click (save) Button
    fun navToMain(){
        uiScope.launch {
            insert(_note.value!!)
            _navigateToMain.value = true
        }

    }

    // Insert Note to DataBase
    private suspend fun insert(note: Note){
        withContext(Dispatchers.IO){
            Log.i("AddScreenViewModel", "insert: note Inserted is ${note.title + "........" + note.text } .")
            data.addNote(note)
        }
    }

    // for handle navigate between UI's
    // decrease operation work in  UI
    private val _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain : LiveData<Boolean?>
        get() = _navigateToMain

    //when UI changes done rest _navigateToMain to null
    fun doneNavigate(){
        _navigateToMain.value = null
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}