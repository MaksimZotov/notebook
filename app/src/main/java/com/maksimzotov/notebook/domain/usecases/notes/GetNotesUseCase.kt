package com.maksimzotov.notebook.domain.usecases.notes

import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: Repository,
) {
    fun getNotes(): Flow<Response<List<Note>>> = repository.notes
}