package com.example.e_book_libruary_app.presentation.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.domain.model.BookInfo
import org.jetbrains.annotations.Async

@Composable
fun BookElement(
    book: BookInfo
){

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(5.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Log.d("CheckImage", book.imageUrl)

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.imageUrl.replace("http://", "https://"))
                        .crossfade(true)
                        .build(),
                    contentDescription = "book_poster",
                    contentScale = ContentScale.FillWidth,
                    loading = {
                        CircularProgressIndicator()
                    }
                )

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.star_icon),
                        contentDescription = "rating_star"
                    )
                    Text(text = book.rating.toString())
                }
            }

            Text(text = book.authors.toString())
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = book.title)
            Text(text = book.pageCount.toString())
            Text(text = book.mainCategory)
        }
    }
}