package com.example.e_book_libruary_app.presentation.book_card

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.tools.ToggleableInfo

data class BookCardScreenState(
    val book: BookInfo = BookInfo(
        bookId = "default_bookId",
        title = "NoName",
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
        isPdfAvailable = null,
        isAcsmAvailable = null,
        acsmLink = null,
        pdfLink = null,
    ),
    val isContextMenuVisible: Boolean = false,
    val isDialogShown: Boolean = false,
    val bookshelfCheckboxes: List<ToggleableInfo> = emptyList(),
    val isContentLoading: Boolean = false,
)
