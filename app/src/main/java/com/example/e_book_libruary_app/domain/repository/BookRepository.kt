package com.example.e_book_libruary_app.domain.repository

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
    ): Resource<BookInfo>

    suspend fun getNewestBooks(): Flow<Resource<BookList>>

    suspend fun getBooksByCategory(
        query: String
    ): Flow<Resource<BookList>>
}