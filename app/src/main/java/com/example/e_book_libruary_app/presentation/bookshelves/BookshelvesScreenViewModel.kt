package com.example.e_book_libruary_app.presentation.bookshelves

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_book_libruary_app.data.local.entities.BookshelfEntity
import com.example.e_book_libruary_app.data.mapper.toBookInfoFromEntity
import com.example.e_book_libruary_app.data.mapper.toBookshelf
import com.example.e_book_libruary_app.domain.model.Bookshelf
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.Routes
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookshelvesScreenViewModel @Inject constructor(
    private val bookRepository: BookRepository,
): ViewModel() {

    var state by mutableStateOf(BookshelvesScreenState())

    init {
        viewModelScope.launch {
            getBookshelves()
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: BookshelvesScreenEvent) {
        when(event) {
            is BookshelvesScreenEvent.OnBackIconClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is BookshelvesScreenEvent.OnBookshelfClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.BOOKSHELF_SCREEN + "?bookshelfName=${event.bookshelf.bookshelfName}"))
            }
            is BookshelvesScreenEvent.OnAddBookshelfButtonClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = true
                    )
                }
            }
            is BookshelvesScreenEvent.OnDismissDialog -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = false
                    )
                }
            }
            is BookshelvesScreenEvent.OnCreateBookshelfButtonClick -> {
                addNewBookshelf(event.bookshelfName)
            }
            is BookshelvesScreenEvent.OnDialogTextFieldValueChange -> {
                viewModelScope.launch {
                    var isBookshelfNameIsAlreadyExistsTemp = false
                    if (
                        state.bookshelves.any { bookshelf ->
                            bookshelf.bookshelfName == event.textFieldValue
                        }
                    ) {
                        isBookshelfNameIsAlreadyExistsTemp = true
                    }

                    state = state.copy(
                        dialogTextFieldValue = event.textFieldValue,
                        isBookshelfNameIsAlreadyExists = isBookshelfNameIsAlreadyExistsTemp
                    )
                }
            }
        }
    }

    private fun addNewBookshelf(bookshelfName: String) {
        if (bookshelfName != "") {
            CoroutineScope(Dispatchers.IO).launch {
                bookRepository
                    .insertBookshelf(
                        BookshelfEntity(
                            bookshelfName = bookshelfName
                        )
                    )
                state = state.copy(
                    dialogTextFieldValue = "",
                    isDialogShown = false
                )
            }
        }
    }

    private suspend fun getBookshelves() {
        bookRepository
            .getBookshelves()
            .collect{ bookshelves ->
                state = state.copy(
                    bookshelves = bookshelves.toBookshelf()
                )
            }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}



