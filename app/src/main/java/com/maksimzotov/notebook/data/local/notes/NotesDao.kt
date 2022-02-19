package com.maksimzotov.notebook.data.local.notes

import androidx.room.*
import com.maksimzotov.notebook.data.local.notes.dto.NoteDto
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM ${NotesConstants.DATABASE_NAME} ORDER BY _id ASC")
    fun readAll(): Flow<List<NoteDto>>

    @Query("SELECT * FROM ${NotesConstants.DATABASE_NAME} WHERE _id = :id")
    fun read(id: Int): Flow<NoteDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: NoteDto)

    @Update
    fun update(note: NoteDto)

    @Delete
    fun remove(note: NoteDto)
}