package com.example.e_book_libruary_app.presentation.bookshelves

import com.example.e_book_libruary_app.domain.model.Bookshelf

data class BookshelvesScreenState(
    val bookshelves: List<Bookshelf> = emptyList()
)