package com.maksimzotov.notebook.data.local.notes.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.maksimzotov.notebook.data.local.notes.NotesConstants
import com.maksimzotov.notebook.data.local.notes.converters.DateConverter
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithAlarm
import java.util.*

@Entity(tableName = NotesConstants.DATABASE_NAME)
@TypeConverters(DateConverter::class)
data class NoteDto(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val title: String,
    val text: String,
    val time: Date,
    val timeToAlarm: Date? = null
) {
    fun mapToNote(): Note =
        if (timeToAlarm == null)
            Note(_id, title, text, time)
        else
            NoteWithAlarm(_id, title, text, time, timeToAlarm)
}

fun Note.mapToNoteDto(): NoteDto =
    if (this is NoteWithAlarm)
        NoteDto(
            title = title,
            text = text,
            time = time,
            timeToAlarm = timeToAlarm
        )
    else
        NoteDto(
            title = title,
            text = text,
            time = time
        )


