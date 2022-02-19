package com.maksimzotov.notebook.presenter.viewmodel.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.usecases.settings.GetBottomNavigationUseCase
import com.maksimzotov.notebook.domain.usecases.settings.GetDarkThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getDarkThemeUseCase: GetDarkThemeUseCase,
    getBottomNavigationUseCase: GetBottomNavigationUseCase
): ViewModel() {

    val darkTheme = getDarkThemeUseCase.getDarkTheme()
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val bottomNavigation = getBottomNavigationUseCase.getBottomNavigation()
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)
}