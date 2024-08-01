package com.example.e_book_libruary_app.domain.repository

import com.example.e_book_libruary_app.data.local.entities.BookEntity
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity
import com.example.e_book_libruary_app.data.local.entities.relations.BookshelfWithBook
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.model.BookList
import com.example.e_book_libruary_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun getBooksByQuery(
        query: String
    ): Flow<Resource<BookList>>

    suspend fun getBookInfo(
        bookId: String
    ): Flow<Resource<BookInfo>>

    suspend fun getNewestBooks(): Flow<Resource<BookList>>

    suspend fun getBooksByCategory(
        query: String
    ): Flow<Resource<BookList>>

    suspend fun insertBookshelf(bookshelf: BookshelfEntity)

    suspend fun addBookToBookshelf(book: BookEntity, bookshelfName: String)

    suspend fun deleteBookFromBookshelf(bookshelfName: String, bookId: String)

    suspend fun deleteBookshelf(bookshelfName: String)

    suspend fun getBookshelves(): Flow<List<BookshelfEntity>>

    suspend fun getBooks(): Flow<List<BookEntity>>

    suspend fun getBooksOfBookshelf(bookshelfName: String): Flow<List<BookshelfWithBook>>
}