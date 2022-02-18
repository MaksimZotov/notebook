package com.maksimzotov.notebook.data.local.notes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithAlarm

@Database(
    entities = [
        Note::class,
        NoteWithAlarm::class
    ],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun mainDao(): NotesDao
}