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
    class PopBackstackEvent(): Event()
    class NavigateEvent(val action: NavDirections): Event()

    private val showShortToastChannel = Channel<ShowShortToastEvent>()
    private val showLongToastChannel = Channel<ShowLongToastEvent>()
    private val popBackStackChannel = Channel<PopBackstackEvent>()
    private val navigateChannel = Channel<NavigateEvent>()

    val showShortToastFlow = showShortToastChannel.receiveAsFlow()
    val showLongToastFlow = showLongToastChannel.receiveAsFlow()
    val popBackStackFlow = popBackStackChannel.receiveAsFlow()
    val navigateFlow = navigateChannel.receiveAsFlow()

    fun showShortToast(text: String) = viewModelScope.launch {
        showShortToastChannel.send(ShowShortToastEvent(text))
    }

    fun showLongToast(text: String) = viewModelScope.launch {
        showLongToastChannel.send(ShowLongToastEvent(text))
    }

    fun popBackStack() = viewModelScope.launch {
        popBackStackChannel.send(PopBackstackEvent())
    }

    protected fun navigate(action: NavDirections) = viewModelScope.launch {
        navigateChannel.send(NavigateEvent(action))
    }

    protected fun <T> Flow<T>.stateIn(initialValue: T): StateFlow<T> =
        stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue)
}

