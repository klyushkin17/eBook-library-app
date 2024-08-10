package com.example.e_book_libruary_app.presentation.book_reader

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookReaderScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(BookReaderScreenState())

    init {
        viewModelScope.launch {
            val bookAddress = savedStateHandle.get<String>("bookAddress") ?: return@launch
            Log.d("CheckUri", bookAddress)
            state = state.copy(
                bookAddress = bookAddress
            )
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent() {

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}