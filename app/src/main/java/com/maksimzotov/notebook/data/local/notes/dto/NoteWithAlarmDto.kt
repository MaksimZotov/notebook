package com.maksimzotov.notebook.data.local.notes.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maksimzotov.notebook.data.local.notes.NotesConstants
import com.maksimzotov.notebook.domain.entities.note.NoteWithAlarm
import java.util.*

@Entity(tableName = NotesConstants.DATABASE_NAME)
class NoteWithAlarmDto(
    @PrimaryKey(autoGenerate = true) override var _id: Int = 0,
    title: String,
    text: String,
    time: Date,
    private val timeToNotify: Date
): NoteDto(_id, title, text, time) {
    fun mapToNoteWithAlarm(): NoteWithAlarm = NoteWithAlarm(_id, title, text, time, timeToNotify)
}

fun NoteWithAlarm.mapToNoteWithAlarmDto(): NoteWithAlarmDto = NoteWithAlarmDto(
    title = title,
    text = text,
    time = time,
    timeToNotify = timeToAlarm
)