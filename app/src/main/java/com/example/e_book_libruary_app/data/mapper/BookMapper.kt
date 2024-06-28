package com.example.e_book_libruary_app.data.mapper

import androidx.compose.runtime.saveable.mapSaver
import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto
import com.example.e_book_libruary_app.domain.model.BookInfo

fun BookInfoDto.toBookInfo(): BookInfo {
    return BookInfo(
        title = title,
        authors = authors,
        publisher = publisher ?: "",
        imageUrl = imageUrl ?: "",
        description = description ?: "",
        pageCount = pageCount,
        mainCategory = mainCategory ?: "",
        rating = rating ?: 0f
    )
}

fun List<BookInfoDto>.toBookInfo(): List<BookInfo> {
    return this.map {
        BookInfo(
            title = it.title,
            authors = it.authors,
            publisher = it.publisher ?: "",
            imageUrl = it.imageUrl ?: "",
            description = it.description ?: "",
            pageCount = it.pageCount,
            mainCategory = it.mainCategory ?: "",
            rating = it.rating ?: 0.0f
        )
    }
}