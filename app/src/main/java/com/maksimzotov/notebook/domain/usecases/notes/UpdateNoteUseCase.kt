package com.maksimzotov.notebook.domain.usecases.notes

import com.maksimzotov.notebook.di.qualifiers.DispatcherIO
import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.note.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: Repository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun updateNote(note: Note) = withContext(coroutineDispatcher) {
        repository.updateNote(note)
    }
}