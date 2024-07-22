package com.example.e_book_libruary_app.data.local

import androidx.room.Entity

@Entity
data class BookEntity(
    val bookId: String,
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val description: String?,
    val pageCount: Int,
    val mainCategory: String?,
    val averageRating: Float?,
    val imageUrl: String?
)
