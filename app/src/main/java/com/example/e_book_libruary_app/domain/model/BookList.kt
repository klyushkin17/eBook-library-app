package com.example.e_book_libruary_app.domain.model

import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto

data class BookList(
    val bookList: List<BookInfoDto>
)
