package com.maksimzotov.notebook.domain.entities.note

import java.util.*

class NoteWithAlarm(
    _id: Int = -1,
    title: String,
    text: String,
    time: Date,
    val timeToAlarm: Date
): Note(_id, title, text, time)