package com.maksimzotov.notebook.domain.entities.note

import java.util.*

class NoteWithDeadline(
    _id: Int = -1,
    title: String,
    text: String,
    time: Date,
    val deadline: Date
): Note(_id, title, text, time)