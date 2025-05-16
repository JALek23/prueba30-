package com.example.taskmasterv10

data class Task(
    var title: String,
    val priority: String,
    var done: Boolean,
    val rating: Int
)
