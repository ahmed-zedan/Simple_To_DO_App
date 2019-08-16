package com.zedan.todo.addScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.zedan.todo.R
import com.zedan.todo.dataBase.Note
import com.zedan.todo.dataBase.NoteDataBase
import com.zedan.todo.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Set Binding for that fragment and Inflate the layout for this fragment
        val binding : FragmentAddBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add, container, false)

        val application  = requireNotNull(this.activity).application

        //For access to database
        val data = NoteDataBase.getInstance(application).noteDao

        val viewModelFactory = AddScreenViewModelFactory(data)

        val addViewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddScreenViewModel::class.java)


        //Navigate User to AddScreen UI
        addViewModel.navigateToMain.observe(this, Observer {
            // if User click save button parameter (it) it will be true function handle clicked in addViewModel
            if (it == true){
                //Get data has entered and push it to modelView for save it to local database
                val title : String = binding.addTitle.text.toString()
                val notes : String = binding.addNotes.text.toString()
                addViewModel.setNote(Note(title = title, text = notes))

                //nav to MainScreen
                this.findNavController().navigate(
                        AddFragmentDirections.actionAddFragmentToMainFragment())

                addViewModel.doneNavigate()
            }
        })

        binding.addViewModel = addViewModel

        return binding.root
    }


}
