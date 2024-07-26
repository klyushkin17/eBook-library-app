package com.example.e_book_libruary_app.presentation.bookshelf

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_book_libruary_app.domain.model.Bookshelf
import com.example.e_book_libruary_app.presentation.main.MainEvent
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.frameColor
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.util.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: BookshelfScreenViewModel = hiltViewModel()
){
    val state = viewModel.state

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
                            text = state.bookshelfName,
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
                                        viewModel.onEvent(BookshelfScreenEvent.OnBackArrowClick)
                                    },
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back_icon",
                                tint = Color.White
                            )
                        }
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            TODO()
                        }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "more_icon",
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
            }
        }

    }
}

