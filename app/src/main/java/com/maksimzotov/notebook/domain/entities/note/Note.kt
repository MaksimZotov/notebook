package com.maksimzotov.notebook.domain.entities.note

import java.util.*

open class Note(
    val _id: Int,
    val title: String,
    val text: String,
    val time: Date
)