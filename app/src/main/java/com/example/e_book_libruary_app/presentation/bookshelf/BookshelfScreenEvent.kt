package com.example.e_book_libruary_app.presentation.bookshelf

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.model.Bookshelf

sealed class BookshelfScreenEvent {
    object OnBackArrowClick: BookshelfScreenEvent()
    data class OnBookClick(val book: BookInfo): BookshelfScreenEvent()
    object OnMoreIconClick: BookshelfScreenEvent()
    object OnDismissContextMenu: BookshelfScreenEvent()
    object OnDeleteBookshelfClick: BookshelfScreenEvent()
    object OnDismissDialog: BookshelfScreenEvent()
    data class OnRemoveBookClick(val bookTitle: String): BookshelfScreenEvent()
}