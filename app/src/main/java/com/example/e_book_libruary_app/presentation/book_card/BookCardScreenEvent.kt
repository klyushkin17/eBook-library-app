package com.example.e_book_libruary_app.presentation.book_card

import com.example.e_book_libruary_app.presentation.bookshelves.BookshelvesScreenEvent

sealed class BookCardScreenEvent {
    object OnBackIconClick: BookCardScreenEvent()
    object OnMoreIconClick: BookCardScreenEvent()
    object OnDismissContextMenu: BookCardScreenEvent()
    object OnAddToClick: BookCardScreenEvent()
    object OnDismissDialogClick: BookCardScreenEvent()
    data class OnCheckboxClick(val index: Int, val isChecked: Boolean): BookCardScreenEvent()
    object OnAddBookToBookshelvesClick: BookCardScreenEvent()
}