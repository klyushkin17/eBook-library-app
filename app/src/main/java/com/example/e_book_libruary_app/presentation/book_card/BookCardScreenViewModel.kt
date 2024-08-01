package com.example.e_book_libruary_app.presentation.book_card

import android.os.Bundle
import android.util.Log
import androidx.collection.intSetOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.data.local.BookshelfDao
import com.example.e_book_libruary_app.data.mapper.toBookEntity
import com.example.e_book_libruary_app.data.mapper.toBookshelf
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.presentation.tools.ToggleableInfo
import com.example.e_book_libruary_app.util.Resource
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookCardScreenViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    var state by mutableStateOf(BookCardScreenState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        viewModelScope.launch {
            val bookId = savedStateHandle.get<String>("bookId") ?: return@launch
            bookRepository
                .getBookInfo(bookId)
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(
                                    book = result.data
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(
                                isContentLoading = result.isLoading
                            )
                        }
                    }
                }
        }
    }

    fun onEvent(event: BookCardScreenEvent) {
        when(event) {
            is BookCardScreenEvent.OnBackIconClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is BookCardScreenEvent.OnMoreIconClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isContextMenuVisible = true
                    )
                }
            }
            is BookCardScreenEvent.OnDismissContextMenu -> {
                viewModelScope.launch {
                    state = state.copy(
                        isContextMenuVisible = false
                    )
                }
            }
            is BookCardScreenEvent.OnDismissDialogClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = false,
                    )
                    onEvent(BookCardScreenEvent.OnDismissContextMenu)
                }
            }
            is BookCardScreenEvent.OnAddToClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = true
                    )
                }
                viewModelScope.launch {
                    bookRepository
                        .getBookshelves()
                        .collect{ bookshelves ->
                            val bookshelfCheckboxes = mutableListOf<ToggleableInfo>()
                            bookshelves.forEach() { bookshelf ->
                                bookshelfCheckboxes.add(
                                    ToggleableInfo(
                                        isChecked = false,
                                        text = bookshelf.bookshelfName
                                    )
                                )
                            }
                            state = state.copy(
                                bookshelfCheckboxes = bookshelfCheckboxes
                            )
                        }
                }
            }
            is BookCardScreenEvent.OnCheckboxClick -> {
                viewModelScope.launch {
                    val newBookshelfCheckboxes = state.bookshelfCheckboxes.toMutableList()
                    newBookshelfCheckboxes[event.index] = newBookshelfCheckboxes[event.index].copy(
                        isChecked = event.isChecked
                    )
                    state = state.copy(
                        bookshelfCheckboxes = newBookshelfCheckboxes
                    )
                }
            }
            is BookCardScreenEvent.OnAddBookToBookshelvesClick -> {
                CoroutineScope(Dispatchers.IO).launch {
                    state.bookshelfCheckboxes.onEach { bookshelfInfo ->
                        if (bookshelfInfo.isChecked) {
                            insertBookIntoBookshelf(state.book, bookshelfInfo.text)
                        }
                    }
                    onEvent(BookCardScreenEvent.OnDismissDialogClick)
                }
            }
        }
    }

    private suspend fun insertBookIntoBookshelf(book: BookInfo?, bookshelfName: String) {
        bookRepository
            .addBookToBookshelf(book?.toBookEntity() ?:
            BookInfo(
                bookId = "null",
                title = "null",
                authors = emptyList(),
                publisher = "null",
                imageUrl = "null",
                description = "null",
                pageCount = 0,
                mainCategory = "null",
                rating = 0.0f
            ).toBookEntity(), bookshelfName)
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}