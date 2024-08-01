package com.example.e_book_libruary_app.domain.model

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class BookInfo(
    val bookId: String,
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val description: String?,
    val pageCount: Int,
    val mainCategory: String?,
    val rating: Float?,
    val imageUrl: String?,
    val categories: List<String>?,
    val publishedDate: LocalDateTime?,
    val saleability: String?,
    val isEbook: Boolean?,
    val price: Float?,
    val currencyCode: String?
)
