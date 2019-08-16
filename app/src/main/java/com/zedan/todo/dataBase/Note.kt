package com.zedan.todo.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id : Long = 0L ,

    @ColumnInfo(name = "_title")
    var title : String = "Title",

    @ColumnInfo(name = "_text_note")
    var text : String ,

    @ColumnInfo(name = "_time_created")
    val timeCreated : Long = System.currentTimeMillis()
)