package com.example.e_book_libruary_app.presentation.bookshelf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.data.mapper.toBookInfoFromEntity
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
                        books = result.first().book.toBookInfoFromEntity()
                    )
                }
        }
    }

    fun onEvent(event: BookshelfScreenEvent) {
        when(event) {
            is BookshelfScreenEvent.OnBackArrowClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is BookshelfScreenEvent.OnMoreClick -> {

            }
            is BookshelfScreenEvent.OnBookClick -> {

            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}