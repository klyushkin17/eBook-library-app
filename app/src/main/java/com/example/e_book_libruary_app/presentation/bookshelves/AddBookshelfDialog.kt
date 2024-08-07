package com.example.e_book_libruary_app.presentation.bookshelves

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.disabledButtonColor
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import kotlin.concurrent.fixedRateTimer

@Composable
fun AddBookshelfDialog(
    viewModel: BookshelvesScreenViewModel = hiltViewModel()
) {
    val maxBookshelfCharacters = 20
    val state = viewModel.state

    Dialog(
        onDismissRequest = {
            viewModel.onEvent(BookshelvesScreenEvent.OnDismissDialog)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
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
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 16.dp)
                    .padding(top = 20.dp)
            ) {
                TextField(
                    value = viewModel.state.dialogTextFieldValue,
                    onValueChange = { value ->
                        if (value.length <= maxBookshelfCharacters){
                            viewModel.onEvent(BookshelvesScreenEvent.OnDialogTextFieldValueChange(value))
                        }
                    },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = "Bookshelf name",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    supportingText = {
                        if (state.isBookshelfNameIsAlreadyExists){
                            Text(
                                text = "Bookshelf with this name is already exists",
                                fontSize = 10.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    },
                    isError = state.isBookshelfNameIsAlreadyExists,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium,
                    ),
                    colors = TextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = backgroundColor,
                        unfocusedIndicatorColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        unfocusedPrefixColor = Color.White,
                        unfocusedSuffixColor = Color.White,
                        unfocusedContainerColor = backgroundColor,
                        unfocusedPlaceholderColor = Color.White,
                        unfocusedLeadingIconColor = Color.White,
                        unfocusedTrailingIconColor = Color.White,
                        unfocusedSupportingTextColor = Color.Red,
                        focusedLabelColor = Color.White,
                        focusedPrefixColor = Color.White,
                        focusedSuffixColor = Color.White,
                        focusedPlaceholderColor = Color.White,
                        focusedTrailingIconColor = Color.White,
                        focusedLeadingIconColor = Color.White,
                        focusedSupportingTextColor = Color.Red,
                        disabledTextColor = Color.White,
                        disabledPrefixColor = Color.White,
                        disabledSuffixColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledContainerColor = backgroundColor,
                        disabledIndicatorColor = Color.White,
                        disabledPlaceholderColor = Color.White,
                        disabledLeadingIconColor = Color.White,
                        disabledTrailingIconColor = Color.White,
                        disabledSupportingTextColor = Color.Red,
                        errorPlaceholderColor = Color.White,
                        errorLeadingIconColor = Color.White,
                        errorCursorColor = Color.White,
                        errorPrefixColor = Color.White,
                        errorSuffixColor = Color.White,
                        errorContainerColor = backgroundColor,
                        errorIndicatorColor = Color.Red,
                        errorTextColor = Color.Red,
                        errorLabelColor = Color.White,
                        errorTrailingIconColor = Color.White,
                        errorSupportingTextColor = Color.Red,
                        textSelectionColors = TextSelectionColors(
                            handleColor = Color.White,
                            backgroundColor = Color.White
                        )
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material.Button(
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = scaffoldBackgroundColor,
                            disabledBackgroundColor = disabledButtonColor
                        ),
                        onClick = {
                            viewModel.onEvent(BookshelvesScreenEvent.OnCreateBookshelfButtonClick(viewModel.state.dialogTextFieldValue))
                        },
                        enabled = !state.isBookshelfNameIsAlreadyExists,
                        modifier = Modifier
                            .height(36.dp)
                            .width(96.dp)
                    ) {
                        Text(
                            text = "Create",
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