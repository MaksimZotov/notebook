package com.maksimzotov.notebook.presenter.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    open class Event()

    class ShowShortToastEvent(val text: String): Event()
    class ShowLongToastEvent(val text: String): Event()
    class NavigateEvent(val action: NavDirections): Event()
    class PopBackstackEvent(): Event()

    private val showShortToastChannel = Channel<ShowShortToastEvent>()
    private val showLongToastChannel = Channel<ShowLongToastEvent>()
    private val navigateChannel = Channel<NavigateEvent>()
    private val popBackStackChannel = Channel<PopBackstackEvent>()

    val showShortToastFlow = showShortToastChannel.receiveAsFlow()
    val showLongToastFlow = showLongToastChannel.receiveAsFlow()
    val navigateFlow = navigateChannel.receiveAsFlow()
    val popBackStackFlow = popBackStackChannel.receiveAsFlow()

    fun showShortToast(text: String) = viewModelScope.launch {
        showShortToastChannel.send(ShowShortToastEvent(text))
    }

    fun showLongToast(text: String) = viewModelScope.launch {
        showLongToastChannel.send(ShowLongToastEvent(text))
    }

    fun navigate(action: NavDirections) = viewModelScope.launch {
        navigateChannel.send(NavigateEvent(action))
    }

    fun popBackStack() = viewModelScope.launch {
        popBackStackChannel.send(PopBackstackEvent())
    }

    private val _snackBarIsActive = MutableStateFlow(true)
    val snackBarIsActive = _snackBarIsActive.asStateFlow()

    protected fun <T> Flow<T>.stateIn(initialValue: T): StateFlow<T> =
        stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue)
}

