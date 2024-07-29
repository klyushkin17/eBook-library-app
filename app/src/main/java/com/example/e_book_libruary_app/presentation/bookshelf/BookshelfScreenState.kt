package com.example.e_book_libruary_app.presentation.bookshelf

import com.example.e_book_libruary_app.domain.model.BookInfo

data class BookshelfScreenState(
    val books: List<BookInfo> = emptyList(),
    val bookshelfName: String = "",
    val isContextMenuShown: Boolean = false,
    val isDialogShown: Boolean = false,
    val typeOfDialog: String = "",
    val deletingBook: BookInfo = BookInfo(
        bookId = "null",
        title = "null",
        authors = emptyList(),
        publisher = "null",
        imageUrl = "null",
        description = "null",
        pageCount = 0,
        mainCategory = "null",
        rating = 0.0f
    )
)