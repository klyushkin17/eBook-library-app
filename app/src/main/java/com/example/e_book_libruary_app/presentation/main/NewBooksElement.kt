package com.example.e_book_libruary_app.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.pagerColor
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor

@Composable
fun NewBooksElement(
    modifier: Modifier = Modifier,
    book: BookInfo
){
    Card(
        colors = CardDefaults.cardColors(pagerColor),
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = modifier
            .width(320.dp)
            .padding(10.dp)
            .fillMaxHeight()

    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
            ) {
                if (book.imageUrl != null) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(book.imageUrl.replace("http://", "https://"))
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "book_poster",
                        loading = {
                            CircularProgressIndicator()
                        },
                    )
                }
                else {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.default_book_poster)
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "book_poster",
                        modifier = Modifier
                            .fillMaxSize(),
                        loading = {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                strokeCap = StrokeCap.Round
                            )
                        },
                    )
                }

            }
            Spacer(modifier = Modifier.width(11.dp))
            Column(
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(
                        text = book.title,
                        fontSize = 13.sp,
                        color = Color.White,
                        fontFamily = harunoUmiFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    if (book.authors != null) {
                        Text(
                            text = book.authors.joinToString(separator = ", "),
                            fontSize = 11.sp,
                            color = secondaryTextColor,
                            maxLines = 1,
                            fontFamily = harunoUmiFontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
                if (book.description != null) {
                    Text(
                        text = book.description
                            .replace("<p>", "")
                            .replace("</p>", "")
                            .replace("<b>", "")
                            .replace("</b>", "")
                            .replace("<i>", "")
                            .replace("</i>", "")
                            .replace("<br>", ""),
                        fontSize = 10.sp,
                        color = Color.White,
                        maxLines = 6,
                        fontFamily = harunoUmiFontFamily,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}