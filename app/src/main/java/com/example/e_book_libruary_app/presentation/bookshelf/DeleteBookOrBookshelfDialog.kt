package com.example.e_book_libruary_app.presentation.bookshelf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_book_libruary_app.presentation.bookshelves.BookshelvesScreenEvent
import com.example.e_book_libruary_app.presentation.bookshelves.BookshelvesScreenViewModel
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor

@Composable
fun DeleteBookOrBookshelfDialog(
    viewModel: BookshelfScreenViewModel = hiltViewModel(),
){
    val state = viewModel.state

    Dialog(
        onDismissRequest = {
            viewModel.onEvent(BookshelfScreenEvent.OnDismissDialog)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(175.dp)
                .fillMaxWidth(0.8f),
            colors = CardDefaults.cardColors(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 13.dp)
                    .padding(top = 14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Sure?",
                        color = Color.White,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 19.sp
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    if (state.typeOfDialog == "book") {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = buildAnnotatedString {
                                val focusedStyle = SpanStyle(
                                    color = scaffoldBackgroundColor,
                                    textDecoration = TextDecoration.Underline
                                )
                                val standardStyle = SpanStyle(
                                    color = Color.White
                                )
                                pushStyle(standardStyle)
                                append("do you want to remove ")
                                pushStyle(focusedStyle)
                                append(state.deletingBook.title)
                                pop()
                                append(" book?")
                            },
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                    else {
                        Text(
                            text = "do you want to delete the bookshelf?",
                            color = Color.White,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material.Button(
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(scaffoldBackgroundColor),
                        onClick = {
                            if (state.typeOfDialog == "bookshelf") {
                                viewModel.onEvent(BookshelfScreenEvent.OnDialogDeleteBookshelfClick)
                            }
                            else {
                                viewModel.onEvent(BookshelfScreenEvent.OnDialogRemoveBookClick(state.deletingBook.bookId))
                            }
                        },
                        modifier = Modifier
                            .height(36.dp)
                            .width(96.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (state.typeOfDialog == "bookshelf") "Delete" else "Remove",
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}