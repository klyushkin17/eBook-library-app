package com.example.e_book_libruary_app.presentation.main

sealed class MainEvent {
    data class OnBookClick(val bookId: String): MainEvent()
    object OnProfileIconClick: MainEvent()
    object OnSearchIconClick: MainEvent()
}