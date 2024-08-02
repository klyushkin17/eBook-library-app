package com.example.e_book_libruary_app.presentation.main

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.ui.theme.darkGray
import com.example.e_book_libruary_app.ui.theme.darkGreen
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor
import org.jetbrains.annotations.Async

@Composable
fun BookElement(
    modifier: Modifier = Modifier,
    book: BookInfo
){
    Column(
        modifier = modifier
            .height(173.dp)
            .width(100.dp)
    ) {
        Card(
            modifier = Modifier
                .height(130.dp)
                .width(100.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 3.dp
        ){
            Box(modifier = Modifier.fillMaxSize()) {
                if (book.imageUrl != null) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(book.imageUrl.replace("http://", "https://"))
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "book_poster",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        loading = {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                strokeCap = StrokeCap.Round
                            )
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
                            .fillMaxSize()
                            .align(Alignment.Center),
                        loading = {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                strokeCap = StrokeCap.Round
                            )
                        },
                    )
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 250f
                        )
                    )
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.BottomStart
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .height(13.dp),
                            painter = painterResource(id = R.drawable.star_icon),
                            contentDescription = "rating_star",
                            contentScale = ContentScale.FillHeight
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        if (book.rating != null) {
                            Text(
                                text = book.rating.toString(),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontFamily = harunoUmiFontFamily
                            )
                        }
                        else {
                            Text(
                                text = "—",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontFamily = harunoUmiFontFamily
                            )
                        }

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (book.authors != null) {
                Text(
                    text = book.authors.joinToString(separator = ", "),
                    fontSize = 9.sp,
                    color = secondaryTextColor,
                    maxLines = 1,
                    fontFamily = harunoUmiFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = book.title,
                fontSize = 11.sp,
                color = Color.White,
                fontFamily = harunoUmiFontFamily,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
        }
    }
}