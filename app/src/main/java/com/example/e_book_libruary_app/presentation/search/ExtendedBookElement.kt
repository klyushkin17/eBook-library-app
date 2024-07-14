package com.example.e_book_libruary_app.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.domain.model.BookInfo

@Composable
fun ExtendedBookElement(
    book: BookInfo,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.imageUrl.replace("http://", "https://"))
                    .crossfade(true)
                    .build(),
                contentDescription = "book_poster",
                loading = {
                    CircularProgressIndicator()
                },
                modifier = Modifier
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = book.authors.joinToString(separator = ", "),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = book.title,
                    maxLines = 2
                )
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.star_icon),
                        contentDescription = "rating_star"
                    )
                    Text(text = book.rating.toString())
                }
            }
        }
    }
}