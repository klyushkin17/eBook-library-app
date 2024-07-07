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
        getNewestBooks()
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

    private fun getNewestBooks() {
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
    }
}