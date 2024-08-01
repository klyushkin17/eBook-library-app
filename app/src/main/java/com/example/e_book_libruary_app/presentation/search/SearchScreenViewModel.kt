package com.example.e_book_libruary_app.presentationeee.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_book_libruary_app.data.mapper.toBookEntity
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.presentation.book_card.BookCardScreenEvent
import com.example.e_book_libruary_app.presentation.search.SearchScreenEvent
import com.example.e_book_libruary_app.presentation.search.SearchScreenState
import com.example.e_book_libruary_app.presentation.tools.ToggleableInfo
import com.example.e_book_libruary_app.util.Resource
import com.example.e_book_libruary_app.util.Routes
import com.example.e_book_libruary_app.util.UiEvent
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.Route
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    var state by mutableStateOf(SearchScreenState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var searchJob: Job? = null

    fun onEvent(event: SearchScreenEvent) {
        when(event) {
            is SearchScreenEvent.OnBackArrowClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is SearchScreenEvent.OnBookClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.BOOK_SCREEN + "?bookId=${event.book.bookId}"))
            }
            is SearchScreenEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    getBooksByQuery()
                }
            }
            is SearchScreenEvent.OnHeartIconClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = true,
                        addingBook = event.book
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
            is SearchScreenEvent.OnDismissDialog -> {
                viewModelScope.launch {
                    state = state.copy(
                        isDialogShown = false
                    )
                }
            }
            is SearchScreenEvent.OnCheckboxClick -> {
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
            is SearchScreenEvent.OnAddClick -> {
                CoroutineScope(Dispatchers.IO).launch {
                    state.bookshelfCheckboxes.onEach { bookshelfInfo ->
                        if (bookshelfInfo.isChecked) {
                            insertBookIntoBookshelf(state.addingBook, bookshelfInfo.text)
                        }
                    }
                    onEvent(SearchScreenEvent.OnDismissDialog)
                }
            }
        }
    }

    private suspend fun insertBookIntoBookshelf(book: BookInfo, bookshelfName: String) {
        bookRepository
            .addBookToBookshelf(book.toBookEntity(), bookshelfName)
    }

    private fun getBooksByQuery(
        query: String = state.searchQuery.lowercase()
    ){
        viewModelScope.launch {
            bookRepository
                .getBooksByQuery(query = query)
                .collect{ result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    books = books.bookList.toBookInfo()
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

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}



