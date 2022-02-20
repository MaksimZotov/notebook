package com.maksimzotov.notebook.domain.usecases.notes

import com.maksimzotov.notebook.di.qualifiers.DispatcherDefault
import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.note.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: Repository,
    @DispatcherDefault private val coroutineDispatcher: CoroutineDispatcher
) {
    fun getNotes(): Flow<List<Note>> = repository.notes.map { notes ->
        withContext(coroutineDispatcher) {
            notes.sortedByDescending { note ->
                note.time
            }
        }
    }
}