package com.example.e_book_libruary_app.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.e_book_libruary_app.data.local.entities.BookEntity
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity

data class BookshelfWithBook(
    @Embedded val bookshelf: BookshelfEntity,
    @Relation(
        parentColumn = "bookshelfName",
        entityColumn = "bookId",
        associateBy = Junction(BookshelfBookCrossRef::class)
    )
    val books: List<BookEntity>
)
