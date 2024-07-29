package com.example.e_book_libruary_app.presentation.bookshelf

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.data.mapper.toBookInfoFromEntity
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.Routes
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookshelfScreenViewModel @Inject constructor (
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(BookshelfScreenState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val bookshelfName = savedStateHandle.get<String>("bookshelfName") ?: return@launch
            state = state.copy(
                bookshelfName = bookshelfName
            )
            bookRepository
                .getBooksOfBookshelf(bookshelfName)
                .collect{ result ->
                    state = state.copy(
                        books = result.firstOrNull()?.books?.toBookInfoFromEntity() ?: emptyList()
                    )
                }
        }
    }

    fun onEvent(event: BookshelfScreenEvent) {
        when(event) {
            is BookshelfScreenEvent.OnBackArrowClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is BookshelfScreenEvent.OnMoreIconClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isContextMenuShown = true
                    )
                }
            }
            is BookshelfScreenEvent.OnBookClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.BOOK_SCREEN + "?bookId=${event.book.bookId}"))
            }
            is BookshelfScreenEvent.OnDismissContextMenu -> {
                viewModelScope.launch {
                    state = state.copy(
                        isContextMenuShown = false
                    )
                }
            }
            is BookshelfScreenEvent.OnDismissDialog -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = false,
                    )
                    onEvent(BookshelfScreenEvent.OnDismissContextMenu)
                }
            }
            is BookshelfScreenEvent.OnDeleteBookshelfClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = true,
                        typeOfDialog = "bookshelf"
                    )
                }
            }
            is BookshelfScreenEvent.OnRemoveBookClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = true,
                        typeOfDialog = "book",
                        deletingBook = event.book
                    )
                }
            }
            is BookshelfScreenEvent.OnDialogDeleteBookshelfClick -> {
                viewModelScope.launch {
                    onEvent(BookshelfScreenEvent.OnDismissDialog)
                    sendUiEvent(UiEvent.Navigate(Routes.BOOKSHELVES_SCREEN))
                }
                CoroutineScope(Dispatchers.IO).launch {
                    deleteBookshelf()
                }

            }
            is BookshelfScreenEvent.OnDialogRemoveBookClick -> {
                CoroutineScope(Dispatchers.IO).launch {
                    removeBookFromBookshelf(event.bookId)
                    onEvent(BookshelfScreenEvent.OnDismissDialog)
                }
            }
        }
    }

    private suspend fun deleteBookshelf() {
        bookRepository
            .deleteBookshelf(state.bookshelfName)
    }

    private suspend fun removeBookFromBookshelf(bookId: String) {
        bookRepository
            .deleteBookFromBookshelf(bookId = bookId, bookshelfName = state.bookshelfName)
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}