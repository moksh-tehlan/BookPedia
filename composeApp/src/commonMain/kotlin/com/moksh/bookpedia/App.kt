package com.moksh.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moksh.bookpedia.book.presentation.book_list.BookListScreen
import com.moksh.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreen(
            viewModel = remember { BookListViewModel() },
            onBookClick = {}
        )
    }
}