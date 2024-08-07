package com.example.e_book_libruary_app.presentation.main

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.Resource
import com.example.e_book_libruary_app.util.Routes
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    var state by mutableStateOf(MainState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getBooks()
    }

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnSearchIconClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.SEARCH_SCREEN))
            }
            is MainEvent.OnSignOutClick -> {
                viewModelScope.launch {
                    event.googleAuthUiClient.signOut()
                    sendUiEvent(UiEvent.Navigate(Routes.SIGN_IN_SCREEN))
                }
            }
            is MainEvent.OnBookClick -> {
                Log.d("CheckBookId", event.book.bookId)
                sendUiEvent(UiEvent.Navigate(Routes.BOOK_SCREEN + "?bookId=${event.book.bookId}"))
            }
            is MainEvent.OnUserAvatarClick -> {
                state = state.copy(
                    isContextMenuVisible = true
                )
            }
            is MainEvent.OnDismissContextMenu -> {
                state = state.copy(
                    isContextMenuVisible = false
                )
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
                        is Resource.Loading -> {
                            val newContentLoadingInfo = state.contentLoadingInfo.toMutableList()
                            newContentLoadingInfo[0] = result.isLoading
                            state = state.copy(
                                contentLoadingInfo = newContentLoadingInfo
                            )
                        }
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
                        is Resource.Loading -> {
                            val newContentLoadingInfo = state.contentLoadingInfo.toMutableList()
                            newContentLoadingInfo[1] = result.isLoading
                            state = state.copy(
                                contentLoadingInfo = newContentLoadingInfo
                            )
                        }
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
                        is Resource.Loading -> {
                            val newContentLoadingInfo = state.contentLoadingInfo.toMutableList()
                            newContentLoadingInfo[2] = result.isLoading
                            state = state.copy(
                                contentLoadingInfo = newContentLoadingInfo
                            )
                        }
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
                        is Resource.Loading -> {
                            val newContentLoadingInfo = state.contentLoadingInfo.toMutableList()
                            newContentLoadingInfo[3] = result.isLoading
                            state = state.copy(
                                contentLoadingInfo = newContentLoadingInfo
                            )
                        }
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
                        is Resource.Loading -> {
                            val newContentLoadingInfo = state.contentLoadingInfo.toMutableList()
                            newContentLoadingInfo[4] = result.isLoading
                            state = state.copy(
                                contentLoadingInfo = newContentLoadingInfo
                            )
                        }
                    }
                }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}