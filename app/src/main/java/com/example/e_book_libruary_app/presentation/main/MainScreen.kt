package com.example.e_book_libruary_app.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigator
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.sign_in.UserData
import com.example.e_book_libruary_app.ui.theme.darkGreen
import com.example.e_book_libruary_app.ui.theme.green
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.white
import com.example.e_book_libruary_app.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    Scaffold (
        containerColor = white,
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                modifier = Modifier
                    .shadow(elevation = 5.dp,  shape = RoundedCornerShape(12.dp), ambientColor = Color.Black, spotColor = Color.Black)
                    .height(50.dp)
                    .padding(top = 6.dp)
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(12.dp)),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        AsyncImage(
                            model = userData?.profilePictureUrl ?: "",
                            contentDescription = "user_avatar",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                    }

                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(MainEvent.OnSearchIconClick)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search_icon"
                        )

                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = darkGreen,
                    actionIconContentColor = white
                )
            )
        }
    ){ values ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(values )
                .padding(horizontal = 3.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 11.dp)
            ){
                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(1) {
                        BookList(books = state.newBooks, title = "New", viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        BookList(books = state.programmingBooks, title = "Code", viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        BookList(books = state.fantasyBooks, title = "Fantasy", viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        BookList(books = state.artBooks, title = "Art", viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                        BookList(books = state.biographyBooks, title = "Biography", viewModel)
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

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
            .background(color = green, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp)
            .padding(horizontal = 15.dp)
    ){
        Column {
            Text(
                text = title,
                fontSize = 19.sp,
                fontFamily = harunoUmiFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black
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