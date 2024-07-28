package com.example.e_book_libruary_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.e_book_libruary_app.converters.RoomListConverter
import com.example.e_book_libruary_app.data.local.entities.BookEntity
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity
import com.example.e_book_libruary_app.data.local.entities.relations.BookshelfBookCrossRef

@Database(
    entities = [
        BookshelfEntity::class,
        BookEntity::class,
        BookshelfBookCrossRef::class
    ],
    version = 7
)
@TypeConverters(RoomListConverter::class)
abstract class BookshelfDatabase: RoomDatabase() {

    abstract val bookshelfDao: BookshelfDao
}