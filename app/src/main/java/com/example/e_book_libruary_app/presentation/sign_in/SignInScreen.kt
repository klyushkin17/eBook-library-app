package com.example.e_book_libruary_app.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.e_book_libruary_app.R
import com.example.e_book_libruary_app.ui.theme.backgroundColor
import com.example.e_book_libruary_app.ui.theme.darkGreen
import com.example.e_book_libruary_app.ui.theme.green
import com.example.e_book_libruary_app.ui.theme.harunoUmiFontFamily
import com.example.e_book_libruary_app.ui.theme.lightGreen
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.ui.theme.white
import com.google.android.gms.fido.u2f.api.common.ChannelIdValue.UnsupportedChannelIdValueTypeException

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {// Launches every time, when state.signInError changes
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .height(120.dp),
            painter = rememberImagePainter(data = R.drawable.greeting_image),
            contentDescription = "greeting image",
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(scaffoldBackgroundColor),
            onClick = {
                onSignInClick()
            },
            modifier = Modifier
                .height(44.dp)
                .width(140.dp)
        ) {
            Text(
                text = "Sign in",
                fontFamily = harunoUmiFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = white
            )
        }
    }
}