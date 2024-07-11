package com.example.e_book_libruary_app.presentationeee.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.data.mapper.toBookInfo
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.presentation.search.SearchScreenEvent
import com.example.e_book_libruary_app.presentation.search.SearchScreenState
import com.example.e_book_libruary_app.util.Resource
import com.example.e_book_libruary_app.util.Routes
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
                sendUiEvent(UiEvent.Navigate(Routes.MAIN_SCREEN))
            }
            is SearchScreenEvent.OnBookClick -> {

            }
            is SearchScreenEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getBooksByQuery()
                }
            }

        }
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
                        is Resource.Loading -> Unit
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



