package com.example.e_book_libruary_app.presentation.book_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.presentation.tools.shimmerEffect
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor

@Composable
fun ShimmerLoadingBookCardScreen() {

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color.Black, Color.Transparent),
                            radius = 2500f
                        )
                    )
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(82.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(modifier = Modifier
                                    .height(14.dp)
                                    .width(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .shimmerEffect()
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Box(modifier = Modifier
                                    .height(12.dp)
                                    .width(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .shimmerEffect()
                                )
                            }
                            Box(modifier = Modifier
                                .height(262.dp)
                                .width(209.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect()
                            )
                            Box(
                                modifier = Modifier
                                    .height(14.dp)
                                    .width(45.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .shimmerEffect()
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 85.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier
                                .height(14.dp)
                                .width(110.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect()
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Box(modifier = Modifier
                                .height(19.dp)
                                .width(195.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect()
                            )
                        }
                        Spacer(modifier = Modifier.height(37.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Box(modifier = Modifier
                                .height(18.dp)
                                .width(195.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect()
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect()
                            )
                        }
                    }
                }
            }
        }
    }
}