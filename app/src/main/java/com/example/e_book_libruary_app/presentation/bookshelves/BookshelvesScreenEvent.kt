package com.example.e_book_libruary_app.presentation.bookshelves

import com.example.e_book_libruary_app.domain.model.Bookshelf
import com.example.e_book_libruary_app.presentation.book_card.BookCardScreenEvent

sealed class BookshelvesScreenEvent {
    object OnBackIconClick: BookshelvesScreenEvent()
    data class OnBookshelfClick(val bookshelf: Bookshelf): BookshelvesScreenEvent()
    object OnAddBookshelfButtonClick: BookshelvesScreenEvent()
    object OnDismissDialog: BookshelvesScreenEvent()
    data class OnCreateBookshelfButtonClick(val bookshelfName: String): BookshelvesScreenEvent()
    data class OnDialogTextFieldValueChange (val textFieldValue: String): BookshelvesScreenEvent()
}