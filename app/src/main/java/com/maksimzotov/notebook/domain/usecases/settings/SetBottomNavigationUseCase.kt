package com.maksimzotov.notebook.domain.usecases.settings

import com.maksimzotov.notebook.di.qualifiers.DispatcherIO
import com.maksimzotov.notebook.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetBottomNavigationUseCase @Inject constructor(
    private val repository: Repository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun setBottomNavigation(isAble: Boolean) = withContext(coroutineDispatcher) {
        repository.setBottomNavigation(isAble)
    }
}