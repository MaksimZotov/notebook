package com.maksimzotov.notebook.domain

import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.response.Response
import kotlinx.coroutines.flow.Flow

interface Repository {
    val notes: Flow<Response<List<Note>>>
    val itemsAbout: Flow<Response<List<ItemAbout>>>
    val darkTheme: Flow<Response<Boolean>>
    val bottomNavigation: Flow<Response<Boolean>>

    suspend fun addNote(note: Note)
    suspend fun removeNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun setDarkTheme(isAble: Boolean)
    suspend fun setBottomNavigation(isAble: Boolean)
}