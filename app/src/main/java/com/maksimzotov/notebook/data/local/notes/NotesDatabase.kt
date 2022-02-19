package com.maksimzotov.notebook.data.local.notes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.notebook.data.local.notes.dto.NoteDto

@Database(
    entities = [
        NoteDto::class
    ],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun mainDao(): NotesDao
}