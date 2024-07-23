package com.example.e_book_libruary_app.data.local.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["bookshelfName", "bookId"])
data class BookshelfBookCrossRef(
    val bookshelfName: String,
    val bookId: String,
)
