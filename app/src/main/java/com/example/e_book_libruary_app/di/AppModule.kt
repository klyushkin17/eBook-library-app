package com.example.e_book_libruary_app.di

import android.app.Application
import androidx.room.Room
import com.example.e_book_libruary_app.data.local.BookshelfDao
import com.example.e_book_libruary_app.data.local.BookshelfDatabase
import com.example.e_book_libruary_app.data.remote.BookApi
import com.example.e_book_libruary_app.data.repository.BookRepositoryImpl
import com.example.e_book_libruary_app.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookApi(): BookApi {
        return Retrofit.Builder()
            .baseUrl(BookApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideBookshelfDatabase(app: Application): BookshelfDatabase {
        return Room.databaseBuilder(
            app,
            BookshelfDatabase::class.java,
            "bookshelf.db"
        ).createFromAsset("database/bookshelves.db").build()
    }
}