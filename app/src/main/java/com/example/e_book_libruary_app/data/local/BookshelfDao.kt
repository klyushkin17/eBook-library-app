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

@Dao
interface BookshelfDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookshelf(bookshelf: BookshelfEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookshelfBookCrossRef(crossRef: BookshelfBookCrossRef)

    @Transaction
    @Query("SELECT * FROM bookshelfentity WHERE bookshelfName = :bookshelfName")
    fun getBooksOfBookshelf(bookshelfName: String): List<BookshelfWithBook>

    /*@Query("delete from bookshelfentity where :bookshelfId == bookshelfId")
    suspend fun deleteBookshelf(
        bookshelfId: Int
    ): Int*/

    /*@Query("update bookshelfentity set books = :books where bookshelfId == :bookshelfId")
    suspend fun updateBookDataInBookshelf(
        bookshelfId: Int,
        books: List<BookEntity>,
    )*/

    /*@Query("update bookshelfentity set bookshelfName = :bookshelfName where bookshelfId == :bookshelfId")
    suspend fun updateBookshelfName(
        bookshelfName: String,
        bookshelfId: Int,
    )*/

    /*@Query("select * from bookshelfentity")
    suspend fun getBookshelves(): List<BookshelfEntity>*/
}