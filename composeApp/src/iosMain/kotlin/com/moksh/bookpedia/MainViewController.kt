package com.moksh.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.moksh.bookpedia.app.App
import com.moksh.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}