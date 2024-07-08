package com.example.e_book_libruary_app.presentation.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigator
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.presentation.sign_in.UserData

@Composable
fun MainScreen(
    userData: UserData?,
    viewModel: MainViewModel = hiltViewModel()
){
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            AsyncImage(
                model = userData?.profilePictureUrl ?: "",
                contentDescription = "user_avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search_icon"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            Column {
                LazyRow (
                    modifier = Modifier.fillMaxWidth()
                ){
                    items(state.newBooks) {book ->
                        BookElement(book = book)
                    }
                }
            }
        }
    }
}