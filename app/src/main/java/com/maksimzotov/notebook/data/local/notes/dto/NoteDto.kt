package com.maksimzotov.notebook.data.local.notes.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maksimzotov.notebook.data.local.notes.NotesConstants
import com.maksimzotov.notebook.domain.entities.note.Note
import java.util.*

@Entity(tableName = NotesConstants.DATABASE_NAME)
open class NoteDto(
    @PrimaryKey val _id: Int = 0,
    val title: String,
    val text: String,
    val time: Date,
) {
    fun mapToNote(): Note = Note(_id, title, text, time)
}

fun Note.mapToNoteDto(): NoteDto = NoteDto(_id, title, text, time)