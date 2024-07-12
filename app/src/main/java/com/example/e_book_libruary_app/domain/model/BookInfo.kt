package com.example.e_book_libruary_app.domain.model

import com.squareup.moshi.Json

data class BookInfo(
    val bookId: String,
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val imageUrl: String,
    val description: String,
    val pageCount: Int,
    val mainCategory: String,
    val rating: Float
)
