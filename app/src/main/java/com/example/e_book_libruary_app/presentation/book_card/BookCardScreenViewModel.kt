package com.example.e_book_libruary_app.presentation.book_card

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.domain.repository.BookRepository
import com.example.e_book_libruary_app.util.Resource
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
            Log.d("CheckBookId",  bookId)
            val bookInfoResult = async { bookRepository.getBookInfo(bookId) }
            when (val result = bookInfoResult.await()){
                is Resource.Success -> {
                    state = state.copy(
                        book = result.data
                    )
                }
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
            }
        }
    }

    fun onEvent(event: BookCardScreenEvent) {
        when(event) {
            is BookCardScreenEvent.OnBackIconClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}