package com.example.e_book_libruary_app.presentation.book_card

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.presentation.bookshelves.BookshelvesScreenEvent
import com.example.e_book_libruary_app.presentation.main.MainEvent
import com.example.e_book_libruary_app.presentation.search.SearchScreenEvent
import com.example.e_book_libruary_app.presentation.tools.DropDownItem
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.disabledButtonColor
import com.example.e_book_libruary_app.ui.theme.frameColor
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.montserrat
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.ui.theme.secondaryTextColor
import com.example.e_book_libruary_app.util.UiEvent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCardScreen(
    viewModel: BookCardScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
){
    val state = viewModel.state
    val scrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    val dropDownItem = listOf(
        DropDownItem("Add to...")
    )

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }

        }
    }
    androidx.compose.material3.Scaffold(
        containerColor = frameColor,
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .padding(top = 6.dp)
                    .padding(horizontal = 4.dp),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        androidx.compose.material3.Icon(
                            modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(BookCardScreenEvent.OnBackIconClick)
                                },
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back_icon",
                            tint = Color.White
                        )
                    }

                },

                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            /*TODO*/
                        }) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "more_icon",
                                tint = Color.White,
                                modifier = Modifier
                                    .pointerInput(true) {
                                        detectTapGestures(
                                            onPress = {
                                                viewModel.onEvent(BookCardScreenEvent.OnMoreIconClick)
                                            }
                                        )
                                    }
                            )
                        }
                        DropdownMenu(
                            expanded = state.isContextMenuVisible,
                            onDismissRequest = {
                                viewModel.onEvent(BookCardScreenEvent.OnDismissContextMenu)
                            },
                            modifier = Modifier
                                .background(backgroundColor)
                        ) {
                            dropDownItem.forEach { item ->
                                androidx.compose.material.DropdownMenuItem(
                                    onClick = {
                                        when(dropDownItem.indexOf(item)){
                                            0 -> {
                                                viewModel.onEvent(BookCardScreenEvent.OnAddToClick)
                                            }
                                        }
                                    }
                                ){
                                    androidx.compose.material3.Text(
                                        text = item.text,
                                        color = Color.White,
                                        fontFamily = montserrat,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                },

                colors = smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    actionIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                onClick = {  },
                containerColor = scaffoldBackgroundColor,
                shape = RoundedCornerShape(12.dp)
            ) {
                Log.d("CheckPrice", state.book.saleability ?: "nothing")
                if (state.book.saleability == "FOR_SALE") {
                    if (state.book.price != null && state.book.currencyCode != null) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = state.book.price.toString(),
                                fontSize = 15.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = state.book.currencyCode,
                                fontSize = 10.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }
                    }
                    else {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "BUY",
                                fontSize = 15.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }
                    }
                }
                else {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "NOT FOR SALE",
                            fontSize = 15.sp,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) {
        if (!state.isContentLoading) {
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
                        if (state.book.imageUrl != null) {
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(state.book.imageUrl.replace("http://", "https://"))
                                    .crossfade(true)
                                    .build(),
                                contentScale = ContentScale.Crop,
                                contentDescription = "book_poster",
                                modifier = Modifier
                                    .fillMaxHeight()
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
                            )
                        }
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
                                    .padding(top = 300.dp)
                                    .background(
                                        color = backgroundColor,
                                        shape = RoundedCornerShape(23.dp)
                                    )
                            ) {
                            }
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
                                        Text(
                                            text = state.book.pageCount.toString(),
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            fontFamily = harunoUmiFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "pages",
                                            color = Color.White,
                                            fontSize = 13.sp,
                                            fontFamily = harunoUmiFontFamily,
                                            fontWeight = FontWeight.Normal,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Card(
                                        modifier = Modifier
                                            .height(262.dp)
                                            .width(209.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        elevation = 3.dp
                                    ) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            if (state.book.imageUrl != null) {
                                                SubcomposeAsyncImage(
                                                    model = ImageRequest.Builder(LocalContext.current)
                                                        .data(
                                                            state.book.imageUrl.replace(
                                                                "http://",
                                                                "https://"
                                                            )
                                                        )
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
                                            } else {
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

                                        }
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
                                        Spacer(modifier = Modifier.width(2.dp))
                                        if (state.book.rating != null) {
                                            Text(
                                                text = state.book.rating.toString(),
                                                color = Color.White,
                                                fontFamily = harunoUmiFontFamily,
                                                fontSize = 14.sp,
                                            )
                                        }
                                        else {
                                            Text(
                                                text = "—",
                                                color = Color.White,
                                                fontFamily = harunoUmiFontFamily,
                                                fontSize = 14.sp,
                                            )
                                        }

                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 85.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    if (state.book.authors != null) {
                                        Text(
                                            text = state.book.authors.joinToString(separator = ", "),
                                            fontSize = 15.sp,
                                            color = secondaryTextColor,
                                            fontFamily = harunoUmiFontFamily,
                                            maxLines = 2,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(1.dp))
                                    Text(
                                        text = state.book.title,
                                        fontSize = 20.sp,
                                        color = Color.White,
                                        fontFamily = harunoUmiFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                if (state.book.isPdfAvailable != null &&
                                    state.book.isPdfAvailable &&
                                    state.book.pdfLink != null
                                ) {
                                    Spacer(modifier = Modifier.height(18.dp))
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp)
                                    ) {
                                        Button(
                                            onClick = {
                                                viewModel.onEvent(BookCardScreenEvent.OnReadSampleButtonClick)
                                            },
                                            elevation = androidx.compose.material3.ButtonDefaults.elevatedButtonElevation(
                                                defaultElevation = 5.dp,
                                                pressedElevation = 0.dp
                                            ),
                                            shape = RoundedCornerShape(12.dp),
                                            colors = ButtonColors(
                                                containerColor = scaffoldBackgroundColor,
                                                contentColor = Color.White,
                                                disabledContainerColor = backgroundColor,
                                                disabledContentColor = Color.Transparent,
                                            )
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(vertical = 7.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center,
                                            ) {
                                                Icon(
                                                    painter = rememberImagePainter(data = R.drawable.read_book_icon),
                                                    contentDescription = "read_book_icon",
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(
                                                    text = "Read sample",
                                                    fontSize = 14.sp,
                                                    fontFamily = montserrat,
                                                    fontWeight = FontWeight.Medium,
                                                    color = Color.White,
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(22.dp))
                                }
                                else {
                                    Spacer(modifier = Modifier.height(32.dp))
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    if (state.book.mainCategory != null ||
                                        state.book.categories != null) {
                                        Text(
                                            text = "Categories",
                                            fontSize = 19.sp,
                                            fontFamily = montserrat,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.White,
                                            modifier = Modifier
                                                .padding(start = 16.dp)
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .horizontalScroll(horizontalScrollState)
                                        ) {
                                            Spacer(modifier = Modifier.width(16.dp))
                                            state.book.mainCategory?.let {
                                                Box(modifier = Modifier
                                                    .padding(
                                                        horizontal = 12.dp,
                                                        vertical = 7.dp
                                                    )
                                                    .background(
                                                        color = scaffoldBackgroundColor,
                                                        shape = RoundedCornerShape(12.dp)
                                                    )
                                                ) {
                                                    Text(
                                                        text = state.book.mainCategory,
                                                        fontSize = 10.sp,
                                                        fontFamily = montserrat,
                                                        fontWeight = FontWeight.Medium,
                                                        color = Color.White
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(8.dp))
                                            }
                                            state.book.categories?.let { categories ->
                                                categories.forEachIndexed { index, category ->
                                                    if (index == 0) {
                                                        Box(modifier = Modifier
                                                            .background(
                                                                color = scaffoldBackgroundColor,
                                                                shape = RoundedCornerShape(12.dp)
                                                            )
                                                            .padding(
                                                                horizontal = 12.dp,
                                                                vertical = 7.dp
                                                            )
                                                        ) {
                                                            Text(
                                                                text = category,
                                                                fontSize = 10.sp,
                                                                fontFamily = montserrat,
                                                                fontWeight = FontWeight.Medium,
                                                                color = Color.White
                                                            )
                                                        }
                                                    }
                                                    else {
                                                        Box(modifier = Modifier
                                                            .background(
                                                                color = backgroundColor,
                                                                shape = RoundedCornerShape(12.dp)
                                                            )
                                                            .padding(
                                                                horizontal = 12.dp,
                                                                vertical = 7.dp
                                                            )
                                                        ) {
                                                            Text(
                                                                text = category,
                                                                fontSize = 10.sp,
                                                                fontFamily = montserrat,
                                                                fontWeight = FontWeight.Medium,
                                                                color = Color.White
                                                            )
                                                        }
                                                    }

                                                    Spacer(modifier = Modifier.width(8.dp))
                                                }
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(30.dp))
                                    }
                                    state.book.description?.let {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp)
                                        ) {
                                            Text(
                                                text = "About the book",
                                                fontSize = 19.sp,
                                                fontFamily = montserrat,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.White
                                            )
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Text(
                                                text = state.book.description
                                                    .replace("<p>", "")
                                                    .replace("</p>", "")
                                                    .replace("<b>", "")
                                                    .replace("</b>", "")
                                                    .replace("<i>", "")
                                                    .replace("</i>", "")
                                                    .replace("<ul>", "")
                                                    .replace("</ul>", "")
                                                    .replace("<li>", "")
                                                    .replace("</li>", "")
                                                    .replace("<br>", "")
                                                    .replace("</br>", ""),
                                                fontSize = 15.sp,
                                                fontFamily = harunoUmiFontFamily,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.White
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(30.dp))
                                    }
                                    state.book.publishedDate?.let {
                                        Row(
                                            verticalAlignment = Alignment.Bottom,
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp)
                                        ) {
                                            Text(
                                                text = "Published:",
                                                fontSize = 19.sp,
                                                fontFamily = montserrat,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = state.book.publishedDate.toString().replace('-', '.'),
                                                fontSize = 14.sp,
                                                fontFamily = harunoUmiFontFamily,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.White
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(50.dp))
                                }
                            }
                        }
                    }
                }
                if (state.isDialogShown) {
                    AddBookToBookshelvesDialog()
                }
            }
        }
        else {
            ShimmerLoadingBookCardScreen()
        }
    }
}