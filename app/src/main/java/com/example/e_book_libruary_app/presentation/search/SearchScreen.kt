package com.example.e_book_libruary_app.presentation.search

import android.annotation.SuppressLint
import android.provider.CloudMediaProvider
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.presentation.main.BookElement
import com.example.e_book_libruary_app.presentation.main.MainEvent
import com.example.e_book_libruary_app.presentation.main.MainViewModel
import com.example.e_book_libruary_app.presentationeee.search.SearchScreenViewModel
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.darkGray
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.ui.theme.searchTextColor
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor
import com.example.e_book_libruary_app.ui.theme.white
import com.example.e_book_libruary_app.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
){
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }

    Scaffold(
        containerColor = white,
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )
                    .height(50.dp)
                    .padding(top = 6.dp)
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(12.dp)),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(SearchScreenEvent.OnBackArrowClick)
                                },
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "search_icon",
                            tint = white
                        )
                    }

                },
                actions = {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 70.dp, end = 30.dp),
                        contentAlignment = Alignment.Center
                    ){
                        BasicTextField(
                            value = state.searchQuery,
                            onValueChange = { query ->
                                viewModel.onEvent(SearchScreenEvent.OnSearchQueryChange(query))
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = searchTextColor,
                                fontFamily = harunoUmiFontFamily,
                                fontWeight = FontWeight.Normal,
                            ),
                            cursorBrush = Brush.verticalGradient(listOf(white, white)),
                            singleLine = true,
                            decorationBox = {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent),
                                    Arrangement.Center
                                ) {
                                    BasicTextField(
                                        value = state.searchQuery,
                                        onValueChange = { query ->
                                            viewModel.onEvent(SearchScreenEvent.OnSearchQueryChange(query))
                                        },
                                        textStyle = TextStyle(
                                            fontSize = 14.sp,
                                            color = searchTextColor,
                                            fontFamily = harunoUmiFontFamily,
                                            fontWeight = FontWeight.Normal,
                                        ),
                                        cursorBrush = Brush.verticalGradient(listOf(white, white)),
                                        singleLine = true,
                                    )
                                    Spacer(modifier = Modifier.height(1.dp))
                                    Divider(color = white)

                                }
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = scaffoldBackgroundColor,
                    actionIconContentColor = white
                )
            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundColor)
                    .padding(horizontal = 16.dp)
            ) {
                items(1) {
                    Spacer(modifier = Modifier.height(70.dp))
                }
                if (state.books.isNotEmpty()) {
                    items(state.books) {book ->
                        ExtendedBookElement(
                            book = book,
                            modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(SearchScreenEvent.OnBookClick(book))
                                }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                else {
                    items(1) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Oups, nothing found...",
                                fontSize = 14.sp,
                                color = searchTextColor,
                                fontFamily = harunoUmiFontFamily,
                                fontWeight = FontWeight.Normal,
                            )
                        }

                    }
                }
                items(1) {
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
    }
}