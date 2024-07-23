package com.example.e_book_libruary_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookshelfEntity(
    val bookshelfName: String,
    //val books: List<BookEntity>,
    @PrimaryKey val bookshelfId: Int? = null
)
