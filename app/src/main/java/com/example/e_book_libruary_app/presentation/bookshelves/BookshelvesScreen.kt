package com.example.e_book_libruary_app.presentation.bookshelves

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.presentation.book_card.BookCardScreenEvent
import com.example.e_book_libruary_app.presentation.main.MainEvent
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.frameColor
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookshelvesScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: BookshelvesScreenViewModel = hiltViewModel()
){
    val state = viewModel.state
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }

    Scaffold (
        containerColor = frameColor,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Bookshelves",
                            fontSize = 19.sp,
                            color = Color.White,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium
                        )
                    }

                },
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )
                    .height(62.dp)
                    .border(1.dp, backgroundColor),
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(
                                modifier = Modifier
                                    .clickable {
                                        viewModel.onEvent(BookshelvesScreenEvent.OnBackIconClick)
                                    },
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back_icon",
                                tint = Color.White
                            )
                        }
                    }
                },

                
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = scaffoldBackgroundColor,
                    actionIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 60.dp),
                onClick = { viewModel.onEvent(BookshelvesScreenEvent.OnAddBookshelfButtonClick) },
                containerColor = scaffoldBackgroundColor,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add_icon",
                    tint = Color.White
                )
            }
        }
    ){
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundColor)
                    .padding(horizontal = 9.dp)
            ){
                items(1) {
                    Spacer(modifier = Modifier.height(72.dp))
                }
                items(state.bookshelves) { bookshelf ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(62.dp)
                            .background(
                                color = scaffoldBackgroundColor,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = bookshelf.bookshelfName,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.width(11.dp))
                            if (bookshelf.bookshelfName == "Favorites") {
                                Icon(
                                    modifier = Modifier
                                        .height(15.dp),
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "favorites_icon",
                                    tint = Color.White
                                )
                            }
                        }
                        Icon(
                            modifier = Modifier
                                .height(18.dp),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "forward_icon",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            if (viewModel.state.isDialogShown) {
                AddBookshelfDialog()
            }
        }

    }
}

