package com.example.e_book_libruary_app.presentation.book_card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book_libruary_app.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookCardScreenViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    var state by mutableStateOf(BookCardScreenState())

    init {
        viewModelScope.launch {
            val bookId = savedStateHandle.get<String>("bookId") ?: return@launch

        }
    }
}