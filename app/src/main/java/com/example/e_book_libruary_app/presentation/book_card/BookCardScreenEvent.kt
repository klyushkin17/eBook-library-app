package com.example.e_book_libruary_app.presentation.book_card

sealed class BookCardScreenEvent {
    object OnBackIconClick: BookCardScreenEvent()
    object OnMoreIconClick: BookCardScreenEvent()
    object OnDismissContextMenu: BookCardScreenEvent()
    object OnAddToClick: BookCardScreenEvent()
    object OnDismissDialogClick: BookCardScreenEvent()
}