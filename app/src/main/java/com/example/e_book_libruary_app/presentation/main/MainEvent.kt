package com.example.e_book_libruary_app.presentation.main

import android.content.Context
import com.example.e_book_libruary_app.domain.model.BookInfo
import com.example.e_book_libruary_app.presentation.sign_in.GoogleAuthUiClient

sealed class MainEvent {
    data class OnBookClick(val book: BookInfo): MainEvent()
    data class OnSignOutClick(val googleAuthUiClient: GoogleAuthUiClient, val context: Context): MainEvent()
    object OnSearchIconClick: MainEvent()
    object OnUserAvatarClick: MainEvent()
    object OnDismissContextMenu: MainEvent()
}