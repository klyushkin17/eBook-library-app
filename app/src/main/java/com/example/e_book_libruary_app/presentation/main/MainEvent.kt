package com.example.e_book_libruary_app.presentation.main

import com.example.e_book_libruary_app.domain.model.BookInfo

sealed class MainEvent {
    data class OnBookClick(val book: BookInfo): MainEvent()
    object OnProfileIconClick: MainEvent()
    object OnSearchIconClick: MainEvent()
}