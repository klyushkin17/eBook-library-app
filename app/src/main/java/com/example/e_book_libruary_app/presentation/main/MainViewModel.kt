package com.example.e_book_libruary_app.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    var state by mutableStateOf(MainState())

    init {
        getBooks()
    }

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnSearchIconClick -> {
                TODO() // Navigate
            }
            is MainEvent.OnProfileIconClick -> {
                TODO() // exit option
            }
            is MainEvent.OnBookClick -> {
                TODO() // Navigate
            }
        }
    }

    private fun getBooks() {
        viewModelScope.launch {
            bookRepository
                .getNewestBooks()
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    newBooks = books.bookList.toBookInfo()
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> Unit
                    }
                }

        }
        viewModelScope.launch {
            bookRepository
                .getBooksByCategory("Code")
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                     programmingBooks = books.bookList.toBookInfo()
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> Unit
                    }
                }
        }
        viewModelScope.launch {
            bookRepository
                .getBooksByCategory("Fantasy")
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    fantasyBooks = books.bookList.toBookInfo()
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> Unit
                    }
                }
        }
        viewModelScope.launch {
            bookRepository
                .getBooksByCategory("Art and Literature")
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    artBooks = books.bookList.toBookInfo()
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> Unit
                    }
                }
        }

        viewModelScope.launch {
            bookRepository
                .getBooksByCategory("Biography Autobiography")
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    biographyBooks = books.bookList.toBookInfo()
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> Unit
                    }
                }
        }
    }
}