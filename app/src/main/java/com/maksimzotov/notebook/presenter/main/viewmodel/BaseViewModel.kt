package com.maksimzotov.notebook.presenter.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel: ViewModel() {
    open class Event()

    class ShowShortToastEvent(val text: String): Event()
    class ShowLongToastEvent(val text: String): Event()
    class PopBackstackEvent(): Event()

    private val showShortToastChannel = Channel<ShowShortToastEvent>(Channel.BUFFERED)
    private val showLongToastChannel = Channel<ShowLongToastEvent>(Channel.BUFFERED)
    private val popBackStackChannel = Channel<PopBackstackEvent>(Channel.BUFFERED)

    val showShortToastFlow = showShortToastChannel.receiveAsFlow()
    val showLongToastFlow = showLongToastChannel.receiveAsFlow()
    val popBackStackFlow = popBackStackChannel.receiveAsFlow()

    fun showShortToast(text: String) = viewModelScope.launch(Dispatchers.Main) {
        showShortToastChannel.send(ShowShortToastEvent(text))
    }

    fun showLongToast(text: String) = viewModelScope.launch(Dispatchers.Main) {
        showLongToastChannel.send(ShowLongToastEvent(text))
    }

    fun popBackStack() = viewModelScope.launch(Dispatchers.Main) {
        popBackStackChannel.send(PopBackstackEvent())
    }

    private val _snackBarIsActive = MutableStateFlow(true)
    val snackBarIsActive = _snackBarIsActive.asStateFlow()
}

