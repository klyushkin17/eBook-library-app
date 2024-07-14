package com.example.e_book_libruary_app.presentation.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigator
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.sign_in.UserData
import com.example.e_book_libruary_app.util.UiEvent

@Composable
fun MainScreen(
    userData: UserData?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
){
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp)
            .padding(horizontal = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp)
                .padding(horizontal = 10.dp)
        ) {
            AsyncImage(
                model = userData?.profilePictureUrl ?: "",
                contentDescription = "user_avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        viewModel.onEvent(MainEvent.OnSearchIconClick)
                    },
                imageVector = Icons.Default.Search,
                contentDescription = "search_icon"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ){
            items(1) {
                BookList(books = state.newBooks, title = "Новинки", viewModel)
                Spacer(modifier = Modifier.height(15.dp))
                BookList(books = state.programmingBooks, title = "Программирование", viewModel)
                Spacer(modifier = Modifier.height(15.dp))
                BookList(books = state.fantasyBooks, title = "Фантастика", viewModel)
                Spacer(modifier = Modifier.height(15.dp))
                BookList(books = state.artBooks, title = "Искусство", viewModel)
                Spacer(modifier = Modifier.height(15.dp))
                BookList(books = state.biographyBooks, title = "Биография", viewModel)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

    }
}

@Composable
fun BookList(
    books: List<BookInfo>,
    title: String,
    viewModel: MainViewModel
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .padding(vertical = 8.dp)
            .padding(horizontal = 10.dp)
    ){
        Column {
            Text(
                text = title,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow (
                modifier = Modifier.fillMaxWidth()
            ){
                items(books) {book ->
                    BookElement(
                        modifier = Modifier
                            .clickable {
                                viewModel.onEvent(MainEvent.OnBookClick(book))
                            },
                        book = book,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}