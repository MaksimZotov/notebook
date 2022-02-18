package com.maksimzotov.notebook.domain.usecases.settings

import com.maksimzotov.notebook.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBottomNavigationUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getBottomNavigation(): Flow<Boolean> = repository.bottomNavigation
}