package com.example.e_book_libruary_app.presentation.book_card

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.tools.ToggleableInfo

data class BookCardScreenState(
    val book: BookInfo? = null,
    val isContextMenuVisible: Boolean = false,
    val isDialogShown: Boolean = false,
    val bookshelfCheckboxes: List<ToggleableInfo> = emptyList(),
    val isContentLoading: Boolean = false,
)
