package com.example.e_book_libruary_app.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_book_libruary_app.presentation.book_card.BookCardScreenEvent
import com.example.e_book_libruary_app.presentationeee.search.SearchScreenViewModel
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor

@Composable
fun AddBookToBookshelvesDialogForSearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()

    Dialog(
        onDismissRequest = {
            viewModel.onEvent(SearchScreenEvent.OnDismissDialog)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = CardDefaults.cardColors(backgroundColor)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(23.dp))
                Text(
                    text = "Add to...",
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 7.dp)
                ) {
                    Spacer(modifier = Modifier.height(13.dp))
                    state.bookshelfCheckboxes.forEachIndexed { index, info ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = info.isChecked,
                                onCheckedChange = { isChecked ->
                                    viewModel.onEvent(SearchScreenEvent.OnCheckboxClick(index, isChecked))
                                },
                                colors = CheckboxColors(
                                    checkedCheckmarkColor = Color.White,
                                    uncheckedBoxColor = Color.Transparent,
                                    checkedBoxColor = scaffoldBackgroundColor,
                                    checkedBorderColor = scaffoldBackgroundColor,
                                    uncheckedBorderColor = scaffoldBackgroundColor,
                                    uncheckedCheckmarkColor = Color.Transparent,
                                    disabledUncheckedBoxColor = Color.Transparent,
                                    disabledBorderColor = Color.Gray,
                                    disabledIndeterminateBorderColor = Color.Gray,
                                    disabledCheckedBoxColor = Color.Gray,
                                    disabledUncheckedBorderColor = Color.Gray,
                                    disabledIndeterminateBoxColor = Color.Transparent
                                )
                            )
                            Text(
                                text = info.text,
                                color = Color.White,
                                fontSize = 15.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            if (info.text == "Favorites") {
                                Icon(
                                    modifier = Modifier
                                        .height(14.dp),
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "favorites_icon",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Row(
                    modifier = Modifier
                        .height(36.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material.Button(
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(scaffoldBackgroundColor),
                        onClick = {
                            viewModel.onEvent(SearchScreenEvent.OnAddClick)
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(96.dp)
                    ) {
                        Text(
                            text = "Add",
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}