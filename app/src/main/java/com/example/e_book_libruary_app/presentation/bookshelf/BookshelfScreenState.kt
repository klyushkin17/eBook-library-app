package com.example.e_book_libruary_app.presentation.bookshelf

import com.example.e_book_libruary_app.domain.model.BookInfo

data class BookshelfScreenState(
    val books: List<BookInfo> = emptyList(),
    val bookshelfName: String = "",
    val isContextMenuShown: Boolean = false,
    val isDialogShown: Boolean = false,
    val typeOfDialog: String = "",
    val deletingBook: BookInfo = BookInfo(
        bookId = "default_bookId",
        title = "default_title",
        pageCount = 0,
        authors = null,
        publisher = null,
        description = null,
        mainCategory = null,
        rating = null,
        imageUrl = null,
        categories = null,
        publishedDate = null,
        saleability = null,
        isEbook = null,
        price = null,
        currencyCode = null,
        isAcsmAvailable = null,
        isPdfAvailable = null,
        acsmLink = null,
        pdfLink = null,
    )
)