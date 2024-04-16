package com.mycare.core.common.base

sealed interface ViewEvent {
    data class Effect(val effect: SideEffect) : ViewEvent
    data class Navigate(val target: NavTarget) : ViewEvent
}
