package com.maksimzotov.notebook.domain.usecases.settings

import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.response.Response
import com.maksimzotov.notebook.domain.entities.response.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDarkThemeUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getDarkTheme(): Flow<Boolean> = repository.darkTheme
        .filter { response ->
            response.status == Status.SUCCESS
        }.map { response ->
            response.data ?: false
        }
}