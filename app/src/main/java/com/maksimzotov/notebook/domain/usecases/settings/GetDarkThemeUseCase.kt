package com.maksimzotov.notebook.domain.usecases.settings

import com.maksimzotov.notebook.domain.Repository
import com.maksimzotov.notebook.domain.entities.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkThemeUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getDarkTheme(): Flow<Response<Boolean>> = repository.darkTheme
}