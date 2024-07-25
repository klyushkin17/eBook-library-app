package com.example.e_book_libruary_app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookshelfEntity(
    @PrimaryKey(autoGenerate = false) val bookshelfName: String,
)
