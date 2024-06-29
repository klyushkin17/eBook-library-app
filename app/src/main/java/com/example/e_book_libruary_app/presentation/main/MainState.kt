package com.example.e_book_libruary_app.presentation.main

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.sign_in.UserData

data class MainState (
    val newBooks: List<BookInfo> = emptyList(),
    val programmingBooks: List<BookInfo> = emptyList(),
    val artBooks: List<BookInfo> = emptyList(),
    val biographyBooks: List<BookInfo> = emptyList(),
    val fantasyBooks: List<BookInfo> = emptyList(),
)