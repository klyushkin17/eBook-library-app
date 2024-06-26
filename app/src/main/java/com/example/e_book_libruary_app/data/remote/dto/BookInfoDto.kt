package com.example.e_book_libruary_app.data.remote.dto

import com.squareup.moshi.Json
import retrofit2.http.Url

data class BookInfoDto(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "authors") val authors: List<String>,
    @field:Json(name = "publisher") val publisher: String?,
    @field:Json(name = "thumbnail") val imageUrl: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "pageCount") val pageCount: Int,
    @field:Json(name = "mainCategory") val mainCategory: String?,
    @field:Json(name = "averageRating") val rating: Float?
)
