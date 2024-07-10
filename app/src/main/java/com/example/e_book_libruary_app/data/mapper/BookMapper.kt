package com.example.e_book_libruary_app.data.mapper

import androidx.compose.runtime.saveable.mapSaver
import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto
import com.example.e_book_libruary_app.data.remote.dto.BookListDto
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.model.BookList

fun BookInfoDto.toBookInfo(): BookInfo {
    return BookInfo(
        id = id,
        title = volumeInfo.title,
        authors = volumeInfo.authors ?: emptyList(),
        publisher = volumeInfo.publisher ?: "",
        imageUrl = volumeInfo.imageLinks?.imageUrl ?: "",
        description = volumeInfo.description ?: "",
        pageCount = volumeInfo.pageCount,
        mainCategory = volumeInfo.mainCategory ?: "",
        rating = volumeInfo.rating ?: 0.0f
    )
}

fun BookListDto.toBookList(): BookList {
    return BookList(
        bookList = bookList ?: emptyList()
    )
}

fun List<BookInfoDto>.toBookInfo(): List<BookInfo> {
    return this.map {
        BookInfo(
            id = it.id,
            title = it.volumeInfo.title,
            authors = it.volumeInfo.authors ?: emptyList(),
            publisher = it.volumeInfo.publisher ?: "",
            imageUrl = it.volumeInfo.imageLinks?.imageUrl ?: "",
            description = it.volumeInfo.description ?: "",
            pageCount = it.volumeInfo.pageCount,
            mainCategory = it.volumeInfo.mainCategory ?: "",
            rating = it.volumeInfo.rating ?: 0.0f
        )
    }
}