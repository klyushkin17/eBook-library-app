package com.example.e_book_libruary_app.presentation.search

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.e_book_libruary_app.presentation.main.BookElement
import com.example.e_book_libruary_app.presentation.main.MainViewModel
import com.example.e_book_libruary_app.presentationeee.search.SearchScreenViewModel
import com.example.e_book_libruary_app.ui.theme.darkGray
import com.example.e_book_libruary_app.util.UiEvent

@Composable
fun SearchScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
){
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp)
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = darkGray, shape = RoundedCornerShape(10.dp))
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(vertical = 6.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "search_icon"
                )
                Spacer(modifier = Modifier.width(7.dp))
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = { query ->
                        viewModel.onEvent(SearchScreenEvent.OnSearchQueryChange(query))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...")
                    },
                    maxLines = 1,
                    singleLine = true
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(state.books) {book ->
                    ExtendedBookElement(book = book)
                    Divider()
                }
            }
        }
    }
}