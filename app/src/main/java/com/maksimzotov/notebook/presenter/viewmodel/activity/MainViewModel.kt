package com.maksimzotov.notebook.presenter.viewmodel.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.response.Status
import com.maksimzotov.notebook.domain.usecases.settings.GetBottomNavigationUseCase
import com.maksimzotov.notebook.domain.usecases.settings.GetDarkThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    getDarkThemeUseCase: GetDarkThemeUseCase,
    getBottomNavigationUseCase: GetBottomNavigationUseCase
): ViewModel() {

    val darkTheme = getDarkThemeUseCase.getDarkTheme()
        .filter { response ->
            response.status == Status.SUCCESS
        }
        .map { response ->
            response.data ?: false
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    val bottomNavigation = getBottomNavigationUseCase.getBottomNavigation()
        .filter { response ->
            response.status == Status.SUCCESS
        }
        .map { response ->
            response.data ?: false
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
}