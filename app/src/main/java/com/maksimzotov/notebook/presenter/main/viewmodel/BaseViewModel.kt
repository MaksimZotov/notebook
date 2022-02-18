package com.maksimzotov.notebook.presenter.main.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel: ViewModel() {
    open class Event()

    class ShowShortToastEvent(val text: String): Event()
    class ShowLongToastEvent(val text: String): Event()
    class NavigateEvent(val action: Int): Event()
    class PopBackstackEvent(): Event()

    private val showShortToastChannel = Channel<ShowShortToastEvent>(Channel.BUFFERED)
    private val showLongToastChannel = Channel<ShowLongToastEvent>(Channel.BUFFERED)
    private val navigateChannel = Channel<NavigateEvent>(Channel.BUFFERED)
    private val popBackStackChannel = Channel<PopBackstackEvent>(Channel.BUFFERED)

    val showShortToast = showShortToastChannel.receiveAsFlow()
    val showLongToast = showLongToastChannel.receiveAsFlow()
    val navigate = navigateChannel.receiveAsFlow()
    val popBackStack = popBackStackChannel.receiveAsFlow()

    private val _snackBarIsActive = MutableStateFlow(true)
    val snackBarIsActive = _snackBarIsActive.asStateFlow()
}

