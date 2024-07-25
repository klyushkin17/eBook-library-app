package com.example.e_book_libruary_app.data.mapper

import androidx.compose.runtime.saveable.mapSaver
import com.example.e_book_libruary_app.data.local.entities.BookEntity
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity
import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto
import com.example.e_book_libruary_app.data.remote.dto.BookListDto
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.model.BookList
import com.example.e_book_libruary_app.domain.model.Bookshelf

fun BookInfoDto.toBookInfo(): BookInfo {
    return BookInfo(
        bookId = bookId,
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

fun BookEntity.toBookInfo(): BookInfo {
    return BookInfo(
        bookId = bookId,
        title = title,
        authors = authors ?: emptyList(),
        publisher = publisher ?: "",
        description = description ?: "",
        pageCount = pageCount,
        mainCategory = mainCategory ?: "",
        rating = rating ?: 0.0f,
        imageUrl = imageUrl ?: ""
    )
}

fun BookInfo.toBookEntity(): BookEntity {
    return BookEntity(
        bookId = bookId,
        title = title,
        authors = authors,
        publisher = publisher,
        description = description,
        pageCount = pageCount,
        mainCategory = mainCategory,
        rating = rating,
        imageUrl = imageUrl
    )
}

fun BookshelfEntity.toBookshelf(): Bookshelf {
    return Bookshelf(
        bookshelfName = bookshelfName
    )
}

fun Bookshelf.toBookshelf(): BookshelfEntity {
    return BookshelfEntity(
        bookshelfName = bookshelfName
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
            bookId = it.bookId,
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