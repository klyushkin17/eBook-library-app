package com.example.e_book_libruary_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookshelfEntity::class, BookEntity::class],
    version = 1
)
abstract class BookshelfDatabase: RoomDatabase() {

    abstract val dao: BookshelfDao
}