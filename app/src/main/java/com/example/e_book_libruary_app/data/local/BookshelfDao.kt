package com.example.e_book_libruary_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.e_book_libruary_app.data.local.entities.BookEntity
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity
import com.example.e_book_libruary_app.data.local.entities.relations.BookshelfBookCrossRef
import com.example.e_book_libruary_app.data.local.entities.relations.BookshelfWithBook
import kotlinx.coroutines.flow.Flow

@Dao
interface BookshelfDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBookshelf(bookshelf: BookshelfEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookshelfBookCrossRef(crossRef: BookshelfBookCrossRef)

    @Transaction
    @Query("DELETE FROM bookshelfbookcrossref WHERE bookshelfName = :bookshelfName AND bookId = :bookId")
    fun deleteBookshelfBookCrossRef(bookId: String, bookshelfName: String) : Void

    @Transaction
    @Query("DELETE FROM bookshelfbookcrossref WHERE bookshelfName = :bookshelfName")
    fun deleteBookshelfBookCrossRefByBookshelf(bookshelfName: String) : Void

    @Transaction
    @Query("DELETE FROM bookshelfentity WHERE bookshelfName = :bookshelfName")
    fun deleteBookshelf(bookshelfName: String) : Void

    @Transaction
    @Query("SELECT * FROM bookshelfentity")
    fun getBookshelves(): Flow<List<BookshelfEntity>>

    @Transaction
    @Query("SELECT * FROM bookentity")
    fun getBooks(): Flow<List<BookEntity>>

    @Transaction
    @Query("SELECT * FROM bookshelfentity WHERE bookshelfName = :bookshelfName")
    fun getBooksOfBookshelf(bookshelfName: String): Flow<List<BookshelfWithBook>>
}