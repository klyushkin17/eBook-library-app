package com.example.e_book_libruary_app.presentation.bookshelf

import com.example.e_book_libruary_app.domain.model.BookInfo

sealed class BookshelfScreenEvent {
    object OnBackArrowClick: BookshelfScreenEvent()
    data class OnBookClick(val book: BookInfo): BookshelfScreenEvent()
    object OnMoreClick: BookshelfScreenEvent()
}