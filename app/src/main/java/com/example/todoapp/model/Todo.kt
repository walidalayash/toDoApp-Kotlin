package com.example.todoapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey

data class Todo(
    var id: Int =0,
    var title : String,
)


