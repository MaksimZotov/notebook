package com.maksimzotov.notebook.data

import com.maksimzotov.notebook.data.local.SettingsStorage
import com.maksimzotov.notebook.data.local.notes.NotesDao
import com.maksimzotov.notebook.data.local.notes.dto.mapToNoteDtoWithId
import com.maksimzotov.notebook.data.local.notes.dto.mapToNoteDtoWithoutId
import com.maksimzotov.notebook.data.remote.about.AboutApi
import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val settingsStorage: SettingsStorage,
    private val notesDao: NotesDao,
    private val aboutApi: AboutApi
): Repository {

    override val notes = notesDao.readAll().map { notesDto ->
        notesDto.map { noteDto ->
            noteDto.mapToNote()
        }
    }

    override val itemsAbout = flow {
        emit(Response.Loading())
        val responseWrapper = aboutApi.getItemsAbout()
        if (!responseWrapper.isSuccessful) {
            emit(Response.Error())
        } else {
            val wrapper = responseWrapper.body()
            emit(Response.Success(wrapper?.itemsAbout))
        }
    }

    override val darkTheme = settingsStorage.darkTheme

    override val bottomNavigation = settingsStorage.bottomNavigation

    override fun getNote(id: Int): Flow<Note?> = notesDao.read(id).map { noteDto ->
        noteDto?.mapToNote()
    }

    override suspend fun addNote(note: Note) {
        notesDao.add(note.mapToNoteDtoWithoutId())
    }

    override suspend fun removeNote(noteId: Int) {
        notesDao.remove(noteId)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.update(note.mapToNoteDtoWithId())
    }

    override suspend fun setDarkTheme(isAble: Boolean) {
        settingsStorage.setDarkTheme(isAble)
    }

    override suspend fun setBottomNavigation(isAble: Boolean) {
        settingsStorage.setBottomNavigation(isAble)
    }
}