package com.example.e_book_libruary_app.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.tools.shimmerEffect
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.pagerColor
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShimmerLoadingMainScreen(){

    val pagerState = rememberPagerState(initialPage = 1){
        3
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ){
            items(1) {
                Spacer(modifier = Modifier.height(76.dp))
                Box(modifier = Modifier.padding(horizontal = 16.dp)){
                    Box(modifier = Modifier
                        .height(20.dp)
                        .width(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalPager(
                    contentPadding = PaddingValues(horizontal = 26.dp),
                    state = pagerState,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    ){ page ->
                        Card(
                            colors = CardDefaults.cardColors(pagerColor),
                            elevation = CardDefaults.cardElevation(15.dp),
                            modifier = Modifier
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
                                        .clip(RoundedCornerShape(8.dp))
                                        .shimmerEffect()
                                ) {}
                                Spacer(modifier = Modifier.width(11.dp))
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 3.dp)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ){
                                    Column {
                                        Box(modifier = Modifier
                                            .height(12.dp)
                                            .width(70.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .shimmerEffect()
                                        )
                                        Spacer(modifier = Modifier.height(1.dp))
                                        Box(modifier = Modifier
                                            .height(10.dp)
                                            .width(70.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .shimmerEffect()
                                        )
                                    }
                                    Box(modifier = Modifier
                                        .height(80.dp)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .shimmerEffect()
                                    )
                                }
                            }
                        }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(23.dp)
                        )
                        .padding(top = 20.dp)
                ) {
                    ShimmerBookList()
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.padding(horizontal = 16.dp)){ Divider() }
                    Spacer(modifier = Modifier.height(11.dp))
                    ShimmerBookList()
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.padding(horizontal = 16.dp)){ Divider() }
                    Spacer(modifier = Modifier.height(11.dp))
                    ShimmerBookList()
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.padding(horizontal = 16.dp)){ Divider() }
                    Spacer(modifier = Modifier.height(11.dp))
                    ShimmerBookList()
                    Spacer(modifier = Modifier.height(75.dp))
                }

            }
        }
    }
}


@Composable
fun ShimmerBookList(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp)
        ){
            Box(modifier = Modifier
                .height(18.dp)
                .width(65.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow (
            modifier = Modifier.fillMaxSize()
        ){
            items(5) {
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .height(173.dp)
                        .width(100.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .height(130.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect(),
                    ){}
                    Spacer(modifier = Modifier.height(1.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(modifier = Modifier
                            .height(8.dp)
                            .width(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                        Box(modifier = Modifier
                            .height(10.dp)
                            .width(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect()
                        )
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}
