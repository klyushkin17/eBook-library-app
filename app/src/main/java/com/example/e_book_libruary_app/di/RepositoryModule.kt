package com.example.e_book_libruary_app.di

import com.example.e_book_libruary_app.data.repository.BookRepositoryImpl
import com.example.e_book_libruary_app.domain.repository.BookRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository
}