package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.domain.usecases.notes.AddNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.GetNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.UpdateNoteUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ItemAboutDetailsViewModel(itemAbout: ItemAbout): BaseViewModel() {

    companion object {
        private const val ASSISTED_ITEM_ABOUT = "ASSISTED_ITEM_ABOUT"
    }

    private val _itemAbout = MutableStateFlow(itemAbout)
    val itemAbout = _itemAbout.asStateFlow()

    class Factory @AssistedInject constructor(
        @Assisted(ASSISTED_ITEM_ABOUT) private val itemAbout: ItemAbout
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ItemAboutDetailsViewModel(itemAbout) as T

        @AssistedFactory
        interface AssistedFactoryForVM {
            fun create(@Assisted(ASSISTED_ITEM_ABOUT) itemAbout: ItemAbout): Factory
        }
    }
}