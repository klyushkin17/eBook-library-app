package com.example.e_book_libruary_app.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.e_book_libruary_app.domain.model.BookInfo

@Composable
fun BookElement(
    book: BookInfo
){

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .width(190.dp)
            ) {
                Image(
                    painter = rememberImagePainter(book.imageUrl),
                    contentDescription = "book_poster"
                )
                Row {
                    Image(
                        imageVector = TODO("Star Icon"),
                        contentDescription = "rating_star"
                    )
                    Text(text = book.rating.toString())
                }
            }

            Text(text = book.authors.toString())
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = book.title)
        }
    }
}