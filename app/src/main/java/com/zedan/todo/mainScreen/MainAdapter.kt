package com.zedan.todo.mainScreen
/*
 * class MainAdapter has adapter for recycleView In fragment_main.xml
 * subclass from ListAdapter for more information about using ListAdapter ->
 * https://proandroiddev.com/android-data-binding-listadapter-9e72ce50e8c7
 *
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zedan.todo.dataBase.Note
import com.zedan.todo.databinding.ItemInRecBinding

class MainAdapter : ListAdapter<Note, MainAdapter.ViewHolder>(NotesDiffCalls()){

    class ViewHolder private constructor(private val binding: ItemInRecBinding)
        : RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ItemInRecBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(note: Note){
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

}

class NotesDiffCalls : DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}