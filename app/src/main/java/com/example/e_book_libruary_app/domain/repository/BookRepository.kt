package com.example.e_book_libruary_app.domain.repository

import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun getBooksByQuery(
        query: String
    ): Flow<Resource<List<BookInfo>>>

    suspend fun getBookInfo(
        bookId: String
    ): Resource<BookInfo>

    suspend fun getNewestBooks(): Flow<Resource<List<BookInfo>>>

    suspend fun getBooksByCategory(
        query: String
    ): Flow<Resource<List<BookInfo>>>
}