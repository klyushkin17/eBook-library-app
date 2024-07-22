package com.example.e_book_libruary_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookshelfDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookshelf()

    @Query("delete from bookshelfentity where :bookshelfId == bookshelfId")
    suspend fun deleteBookshelf(
        bookshelfId: Int
    )

    @Query("update bookshelfentity set books = :books where bookshelfId == :bookshelfId")
    suspend fun updateBookDataInBookshelf(
        bookshelfId: Int,
        books: List<BookEntity>,
    )

    @Query("update bookshelfentity set bookshelfName = :bookshelfName where bookshelfId == :bookshelfId")
    suspend fun updateBookshelfName(
        bookshelfName: String,
        bookshelfId: Int,
    )

    @Query("select * from bookshelfentity")
    suspend fun getBookshelves(): List<BookshelfEntity>
}