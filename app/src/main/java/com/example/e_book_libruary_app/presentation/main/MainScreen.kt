package com.example.e_book_libruary_app.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigator
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.search.SearchScreenEvent
import com.example.e_book_libruary_app.presentation.sign_in.GoogleAuthUiClient
import com.example.e_book_libruary_app.presentation.sign_in.UserData
import com.example.e_book_libruary_app.presentation.tools.DropDownItem
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.darkGreen
import com.example.e_book_libruary_app.ui.theme.frameColor
import com.example.e_book_libruary_app.ui.theme.green
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    userData: UserData?,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
    googleAuthUiClient: GoogleAuthUiClient
){
    val context = LocalContext.current
    val state = viewModel.state
    val pagerState = rememberPagerState(initialPage = 2){
        state.newBooks.size
    }

    val dropDownItem = listOf(
        DropDownItem("Sign out")
    )

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold (
        containerColor = frameColor,
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
                        AsyncImage(
                            model = userData?.profilePictureUrl ?: "",
                            contentDescription = "user_avatar",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .pointerInput(true) {
                                    detectTapGestures(
                                        onPress = {
                                            viewModel.onEvent(MainEvent.OnUserAvatarClick)
                                        }
                                    )
                                }
                        )
                        DropdownMenu(
                            expanded = state.isContextMenuVisible,
                            onDismissRequest = {
                                viewModel.onEvent(MainEvent.OnDismissContextMenu)
                            },
                            modifier = Modifier
                                .background(backgroundColor)
                        ) {
                            dropDownItem.forEach { item ->
                                androidx.compose.material.DropdownMenuItem(
                                    onClick = {
                                        viewModel.onEvent(MainEvent.OnSignOutClick(googleAuthUiClient, context))
                                        viewModel.onEvent(MainEvent.OnDismissContextMenu)
                                    }
                                ){
                                    Text(
                                        text = item.text,
                                        color = Color.White,
                                        fontFamily = montserrat,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
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
                    containerColor = scaffoldBackgroundColor,
                    actionIconContentColor = Color.White
                )
            )
        }
    ){
        if (
            state.contentLoadingInfo.all{ !it } &&
            state.contentLoadingFailed.all { !it }
        ){
            Log.d("CheckUi", "inMain")
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(1) {
                        Spacer(modifier = Modifier.height(70.dp))
                        Box(modifier = Modifier.padding(horizontal = 16.dp)){
                            Text(
                                text = "New",
                                fontSize = 21.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                        HorizontalPager(
                            contentPadding = PaddingValues(horizontal = 26.dp),
                            state = pagerState,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                            ) { page ->
                            NewBooksElement(
                                book = state.newBooks[page],
                                modifier = Modifier
                                    .clickable {
                                        viewModel.onEvent(MainEvent.OnBookClick(state.newBooks[page]))
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = backgroundColor,
                                    shape = RoundedCornerShape(23.dp)
                                )
                                .padding(top = 20.dp)
                        ) {
                            BookList(books = state.programmingBooks, title = "Code", viewModel)
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(modifier = Modifier.padding(horizontal = 16.dp)) { Divider() }
                            Spacer(modifier = Modifier.height(11.dp))
                            BookList(books = state.fantasyBooks, title = "Fantasy", viewModel)
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(modifier = Modifier.padding(horizontal = 16.dp)) { Divider() }
                            Spacer(modifier = Modifier.height(11.dp))
                            BookList(books = state.artBooks, title = "Art", viewModel)
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(modifier = Modifier.padding(horizontal = 16.dp)) { Divider() }
                            Spacer(modifier = Modifier.height(11.dp))
                            BookList(
                                books = state.biographyBooks,
                                title = "Biography",
                                viewModel
                            )
                            Spacer(modifier = Modifier.height(75.dp))
                        }
                    }
                }
            }
        }
        else {
            if (state.contentLoadingInfo.any { it }) {
                ShimmerLoadingMainScreen()
                Log.d("CheckUi", "inLoading")
            }
            else {
                Log.d("CheckUi", "inError")
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Check internet",
                        fontFamily = montserrat,
                        color = scaffoldBackgroundColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "You, stupid",
                        fontFamily = montserrat,
                        color = scaffoldBackgroundColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                    )
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp)
        ){
            Text(
                text = title,
                fontSize = 19.sp,
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow (
            modifier = Modifier.fillMaxSize()
        ){
            items(books) {book ->
                Spacer(modifier = Modifier.width(16.dp))
                BookElement(
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(MainEvent.OnBookClick(book))
                        },
                    book = book,
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}