package com.example.e_book_libruary_app.presentation.search

import com.example.e_book_libruary_app.domain.model.BookInfo

data class SearchScreenState(
    val books: List<BookInfo> = emptyList(),
    val searchQuery: String = "",
)
