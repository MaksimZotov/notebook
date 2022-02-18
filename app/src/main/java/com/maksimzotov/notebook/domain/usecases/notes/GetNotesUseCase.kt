package com.maksimzotov.notebook.domain.usecases.notes

import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.response.Response
import com.maksimzotov.notebook.domain.entities.response.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: Repository,
) {
    fun getNotes(): Flow<List<Note>> = repository.notes
        .filter { response ->
            response.status == Status.SUCCESS
        }.map { response ->
            response.data ?: emptyList()
        }
}