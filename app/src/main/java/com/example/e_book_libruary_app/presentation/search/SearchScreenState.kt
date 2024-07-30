package com.example.e_book_libruary_app.presentation.search

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.tools.ToggleableInfo

data class SearchScreenState(
    val books: List<BookInfo> = emptyList(),
    val searchQuery: String = "",
    val isDialogShown: Boolean = false,
    val addingBook: BookInfo = BookInfo(
        bookId = "null",
        title = "null",
        authors = emptyList(),
        publisher = "null",
        imageUrl = "null",
        description = "null",
        pageCount = 0,
        mainCategory = "null",
        rating = 0.0f
    ),
    val bookshelfCheckboxes: List<ToggleableInfo> = emptyList()
)
