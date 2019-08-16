package com.zedan.todo.mainScreen


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.zedan.todo.R
import com.zedan.todo.dataBase.NoteDataBase
import com.zedan.todo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //set Binding and Inflate the layout for this fragment
        val binding : FragmentMainBinding = DataBindingUtil.inflate(
                            inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        //Access database
        val data = NoteDataBase.getInstance(application).noteDao

        val viewModelFactory = MainViewModelFactory(data)

        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val adapter = MainAdapter()
        binding.recycleMain.adapter = adapter

        // Update Recycle if new note Added to database
        mainViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                Log.i("MainFragment", "OnCreateView: notes observer : New Item Has appeared in UI .")
            }
        })

        //navigate to AddScreen
        mainViewModel.navigateToAdd.observe(this, Observer {
            if (it == true){
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToAddFragment())
                mainViewModel.doneNavigate()
            }
        })

        binding.mainViewModel = mainViewModel
        return binding.root
    }


}
