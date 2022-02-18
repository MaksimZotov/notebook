package com.maksimzotov.notebook.domain.usecases.settings

import com.maksimzotov.notebook.di.qualifiers.DispatcherIO
import com.maksimzotov.notebook.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetDarkThemeUseCase @Inject constructor(
    private val repository: Repository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun setDarkTheme(isAble: Boolean) = withContext(coroutineDispatcher) {
        repository.setDarkTheme(isAble)
    }
}