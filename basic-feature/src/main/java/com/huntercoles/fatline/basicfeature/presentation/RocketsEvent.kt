package com.huntercoles.fatline.basicfeature.presentation

sealed class RocketsEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : RocketsEvent()
}
