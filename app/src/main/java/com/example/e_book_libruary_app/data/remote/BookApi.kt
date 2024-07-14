package com.example.e_book_libruary_app.data.remote

import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto
import com.example.e_book_libruary_app.data.remote.dto.BookListDto
import com.example.e_book_libruary_app.domain.model.BookList
import javax.annotation.processing.Generated
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {

    @GET("volumes")
    suspend fun getSearchedBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") apiKey: String = API_KEY
    ): BookListDto

    @GET("volumes")
    suspend fun getNewestBooks(
        @Query("q") query: String = "a",
        @Query("maxResults") maxResults: Int = 20,
        @Query("orderBy") orderBy: String = "newest",
        @Query("key") apiKey: String = API_KEY
    ): BookListDto

    @GET("volumes")
    suspend fun getBooksByCategory(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") apiKey: String = API_KEY
    ): BookListDto

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(
        @Path("bookId") bookId: String,
        @Query("projection") projection: String = "full",
        @Query("key") apiKey: String = API_KEY
    ): BookInfoDto

    companion object {
        const val API_KEY = "AIzaSyCEsr9Jp8601qHaitM4CcYCXKV-ByawaBI"
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}