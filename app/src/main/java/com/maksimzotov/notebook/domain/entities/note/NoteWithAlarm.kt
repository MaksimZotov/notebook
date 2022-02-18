package com.maksimzotov.notebook.domain.entities.note

import java.util.*

class NoteWithAlarm(
    _id: Int,
    title: String,
    text: String,
    time: Date,
    val timeToNotify: Date
): Note(_id, title, text, time)