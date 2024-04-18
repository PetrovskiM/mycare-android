package com.mycare.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : BaseState, A> : ViewModel() {

    private val _state: MutableStateFlow<S> = MutableStateFlow(setInitialState())
    val state = _state.asStateFlow()

    val currentState: S
        get() = state.value

    private val _events = MutableSharedFlow<ViewEvent>()
    val events = _events.asSharedFlow()

    protected abstract fun setInitialState(): S

    abstract fun onViewAction(viewAction: A)

    protected fun emitViewEvent(event: ViewEvent) {
        launch { _events.emit(event) }
    }

    protected fun launch(
        block: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(context = Dispatchers.Main, block = block)

    protected fun updateState(update: (S) -> S) {
        _state.update(update)
    }

    abstract fun handleError(throwable: Throwable)
}