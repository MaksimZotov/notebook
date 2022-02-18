package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.domain.entities.response.Status
import com.maksimzotov.notebook.domain.usecases.about.GetItemsAboutUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.*

class AboutViewModel(
    getItemsAboutUseCase: GetItemsAboutUseCase
): BaseViewModel() {

    val itemsAbout: StateFlow<List<ItemAbout>> = getItemsAboutUseCase.getItemsAbout()
        .filter { response ->
            response.status == Status.SUCCESS
        }
        .map { response ->
            response.data ?: emptyList()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}