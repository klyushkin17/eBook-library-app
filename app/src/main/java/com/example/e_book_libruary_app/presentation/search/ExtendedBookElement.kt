package com.example.e_book_libruary_app.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.ui.theme.extendedBookElementBackgroundColor
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor
import com.example.e_book_libruary_app.ui.theme.white

@Composable
fun ExtendedBookElement(
    book: BookInfo,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = extendedBookElementBackgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 18.dp, vertical = 12.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Card(
                modifier = Modifier
                    .height(130.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 3.dp
            ){
                Box(modifier = Modifier.fillMaxSize()) {
                    if (book.imageUrl != "") {
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
                                    color = white,
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
                                    color = white,
                                    strokeWidth = 2.dp,
                                    strokeCap = StrokeCap.Round
                                )
                            },
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = book.title,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = harunoUmiFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = book.authors.joinToString(separator = ", "),
                        fontSize = 12.sp,
                        color = secondaryTextColor,
                        fontFamily = harunoUmiFontFamily,
                        maxLines = 1
                    )


                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .height(15.dp),
                        painter = painterResource(id = R.drawable.star_icon),
                        contentDescription = "rating_star",
                        contentScale = ContentScale.FillHeight
                    )
                    Text(
                        text = book.rating.toString(),
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = harunoUmiFontFamily,
                    )
                }
            }
        }
    }
}