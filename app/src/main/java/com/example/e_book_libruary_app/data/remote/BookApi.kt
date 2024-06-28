package com.example.e_book_libruary_app.data.remote

import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto
import javax.annotation.processing.Generated
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("volumes")
    suspend fun getSearchedBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") apiKey: String = API_KEY
    ): List<BookInfoDto>

    @GET("volumes")
    suspend fun getNewestBooks(
        @Query("q") query: String = "",
        @Query("maxResults") maxResults: Int = 20,
        @Query("orderBy") orderBy: String = "newest",
        @Query("key") apiKey: String = API_KEY
    ): List<BookInfoDto>

    @GET("volumes")
    suspend fun getBooksByCategory(
        @Query("q") query: String = "",
        @Query("maxResults") maxResults: Int = 20,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") apiKey: String = API_KEY
    ): List<BookInfoDto>

    @GET("volumes")
    suspend fun getBookInfo(
        @Query("volumeId") volumeId: String,
        @Query("key") apiKey: String = API_KEY
    ): BookInfoDto

    companion object {
        const val API_KEY = "AIzaSyCG7f-DA12nypNNbGzX4b-XVyWv8WyUKGY"
        const val BASE_URL = "https://www.googleapis.com/books/v1"
    }
}