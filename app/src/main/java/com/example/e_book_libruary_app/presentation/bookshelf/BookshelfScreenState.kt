package com.example.e_book_libruary_app.presentation.bookshelf

import com.example.e_book_libruary_app.domain.model.BookInfo

data class BookshelfScreenState(
    val books: List<BookInfo> = emptyList(),
    val bookshelfName: String = ""
)