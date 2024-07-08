package com.example.e_book_libruary_app.data.repository

import android.util.Log
import coil.network.HttpException
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.data.mapper.toBookList
import com.example.e_book_libruary_app.data.remote.BookApi
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.model.BookList
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
): BookRepository {
    override suspend fun getBooksByQuery(
        query: String
    ): Flow<Resource<List<BookInfo>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = api.getSearchedBooks(query = "$query+inauthor:$query")
                Resource.Success(data = result.toBookInfo())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getBookInfo(
        bookId: String
    ): Resource<BookInfo> {
        return try {
            val result = api.getBookInfo(volumeId = bookId)
            Resource.Success(data = result.toBookInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load data")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load data")
        }
    }

    override suspend fun getNewestBooks(): Flow<Resource<BookList>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = api.getNewestBooks()
                Log.d("ServerResponse", result.toString().substring(350, 3000))
                emit(Resource.Success(data = result.toBookList()))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getBooksByCategory(
        query: String
    ): Flow<Resource<List<BookInfo>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = api.getBooksByCategory(query = "subject:$query")
                Resource.Success(data = result.toBookInfo())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
            }
            emit(Resource.Loading(false))
        }
    }
}