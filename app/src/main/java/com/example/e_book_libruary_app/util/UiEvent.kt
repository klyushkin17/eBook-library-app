package com.example.e_book_libruary_app.util

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
}