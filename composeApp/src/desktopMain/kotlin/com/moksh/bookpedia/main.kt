package com.moksh.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.moksh.bookpedia.app.App
import com.moksh.bookpedia.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ){
            App()
        }
    }
}