package com.example.e_book_libruary_app.presentation.bookshelves

import androidx.compose.runtime.Immutable
import com.example.e_book_libruary_app.domain.model.Bookshelf

@Immutable
data class BookshelvesScreenState(
    val bookshelves: List<Bookshelf> = emptyList(),
    val isDialogShown: Boolean = false,
    val dialogTextFieldValue: String = "",
    val isBookshelfNameIsAlreadyExists: Boolean = false,
)