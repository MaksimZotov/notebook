package com.maksimzotov.notebook.domain.usecases.notes

import com.maksimzotov.notebook.di.qualifiers.DispatcherIO
import com.maksimzotov.notebook.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveNoteUseCase @Inject constructor(
    private val repository: Repository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun removeNote(noteId: Int) = withContext(coroutineDispatcher) {
        repository.removeNote(noteId)
    }
}