package com.maksimzotov.notebook.domain.usecases.notes

import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.note.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: Repository,
) {
    fun getNote(id: Int): Flow<Note?> = repository.getNote(id)
}