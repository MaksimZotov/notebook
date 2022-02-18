package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.usecases.settings.SetBottomNavigationUseCase
import com.maksimzotov.notebook.domain.usecases.settings.SetDarkThemeUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val setDarkThemeUseCase: SetDarkThemeUseCase,
    private val setBottomNavigationUseCase: SetBottomNavigationUseCase
): BaseViewModel() {

    fun setDarkTheme(isAble: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        setDarkThemeUseCase.setDarkTheme(isAble)
    }

    fun setBottomNavigation(isAble: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        setBottomNavigationUseCase.setBottomNavigation(isAble)
    }
}