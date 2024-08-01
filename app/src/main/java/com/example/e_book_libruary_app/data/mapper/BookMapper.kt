package com.example.e_book_libruary_app.data.mapper

import androidx.compose.runtime.saveable.mapSaver
import com.example.e_book_libruary_app.data.local.entities.BookEntity
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity
import com.example.e_book_libruary_app.data.remote.dto.BookInfoDto
import com.example.e_book_libruary_app.data.remote.dto.BookListDto
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.model.BookList
import com.example.e_book_libruary_app.domain.model.Bookshelf
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun BookInfoDto.toBookInfo(): BookInfo {
    val pattern = "yyyy-MM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(this.volumeInfo.publishedDate, formatter)
    return BookInfo(
        bookId = bookId,
        title = volumeInfo.title,
        authors = volumeInfo.authors,
        publisher = volumeInfo.publisher,
        imageUrl = volumeInfo.imageLinks?.imageUrl,
        description = volumeInfo.description,
        pageCount = volumeInfo.pageCount,
        mainCategory = volumeInfo.mainCategory,
        rating = volumeInfo.rating,
        categories = volumeInfo.categories,
        publishedDate = localDateTime,
        currencyCode = saleInfo.retailPrice?.currencyCode,
        isEbook = saleInfo.isEbook,
        price = saleInfo.retailPrice?.price,
        saleability = saleInfo.saleability
    )
}

fun BookEntity.toBookInfo(): BookInfo {
    val pattern = "yyyy-MM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(this.publishedDate, formatter)

    val convertedIsEbook = this.isEbook?.let { it != 0 }

    return BookInfo(
        bookId = bookId,
        title = title,
        authors = authors,
        publisher = publisher,
        description = description,
        pageCount = pageCount,
        mainCategory = mainCategory,
        rating = rating,
        imageUrl = imageUrl,
        categories = categories,
        publishedDate = localDateTime,
        currencyCode = currencyCode,
        isEbook = convertedIsEbook,
        price = price,
        saleability = saleability
    )
}

fun BookInfo.toBookEntity(): BookEntity {

    val pattern = "yyyy-MM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val stringPublishedDate = this.publishedDate?.format(formatter)

    val convertedIsEbook = this.isEbook?.let { if (it) 1 else 0 }

    return BookEntity(
        bookId = bookId,
        title = title,
        authors = authors,
        publisher = publisher,
        description = description,
        pageCount = pageCount,
        mainCategory = mainCategory,
        rating = rating,
        imageUrl = imageUrl,
        categories = categories,
        publishedDate = stringPublishedDate,
        currencyCode = currencyCode,
        isEbook = convertedIsEbook,
        price = price,
        saleability = saleability
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
        val pattern = "yyyy-MM-dd"
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        val localDateTime = LocalDateTime.parse(it.volumeInfo.publishedDate, formatter)


        BookInfo(
            bookId = it.bookId,
            title = it.volumeInfo.title,
            authors = it.volumeInfo.authors,
            publisher = it.volumeInfo.publisher,
            imageUrl = it.volumeInfo.imageLinks?.imageUrl,
            description = it.volumeInfo.description,
            pageCount = it.volumeInfo.pageCount,
            mainCategory = it.volumeInfo.mainCategory,
            rating = it.volumeInfo.rating,
            categories = it.volumeInfo.categories,
            publishedDate = localDateTime,
            currencyCode = it.saleInfo.retailPrice?.currencyCode,
            isEbook = it.saleInfo.isEbook,
            price = it.saleInfo.retailPrice?.price,
            saleability = it.saleInfo.saleability
        )
    }
}

fun List<BookshelfEntity>.toBookshelf(): List<Bookshelf> {
    return this.map {
        Bookshelf(
            bookshelfName = it.bookshelfName
        )
    }
}

fun List<BookEntity>.toBookInfoFromEntity(): List<BookInfo> {
    return this.map { element ->
        val pattern = "yyyy-MM-dd"
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        val localDateTime = LocalDateTime.parse(element.publishedDate, formatter)

        val convertedIsEbook = element.isEbook?.let { it != 0 }

        BookInfo(
            bookId = element.bookId,
            title = element.title,
            authors = element.authors,
            publisher = element.publisher,
            description = element.description,
            pageCount = element.pageCount,
            mainCategory = element.mainCategory,
            rating = element.rating,
            imageUrl = element.imageUrl,
            categories = element.categories,
            publishedDate = localDateTime,
            currencyCode = element.currencyCode,
            isEbook = convertedIsEbook,
            price = element.price,
            saleability = element.saleability
        )
    }
}