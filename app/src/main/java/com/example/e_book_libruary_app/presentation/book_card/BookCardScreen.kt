package com.example.e_book_libruary_app.presentation.book_card

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.e_book_libruary_app.util.UiEvent

@Composable
fun BookCardScreen(
    viewModel: BookCardScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
){
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }

        }

    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.book?.imageUrl?.replace("http://", "https://"))
                .crossfade(true)
                .build(),
            contentDescription = "book_poster",
            loading = {
                CircularProgressIndicator()
            },
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}