package com.example.e_book_libruary_app.presentation.search

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.main.MainEvent

sealed class SearchScreenEvent {
    object OnBackArrowClick: SearchScreenEvent()
    data class OnBookClick(val book: BookInfo): SearchScreenEvent()
    data class OnSearchQueryChange(val query: String): SearchScreenEvent()
}