package com.example.e_book_libruary_app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val bookId: String,
    val title: String,
    //val authors: List<String>?,
    val publisher: String?,
    val description: String?,
    val pageCount: Int,
    val mainCategory: String?,
    val averageRating: Float?,
    val imageUrl: String?,
)
