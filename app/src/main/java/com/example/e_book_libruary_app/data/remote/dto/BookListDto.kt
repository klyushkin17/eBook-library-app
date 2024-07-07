package com.example.e_book_libruary_app.data.remote.dto

import com.squareup.moshi.Json

data class BookListDto(
    @field:Json(name = "items") val bookList: List<BookInfoDto>?,
    @field:Json(name = "totalItems") val countItems: Int
)
