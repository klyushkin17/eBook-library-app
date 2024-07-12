package com.example.e_book_libruary_app.presentation.book_card

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_book_libruary_app.util.UiEvent

@Composable
fun BookCardScreen(
    viewModel: BookCardScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
){

}