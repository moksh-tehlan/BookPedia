package com.moksh.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.moksh.bookpedia.book.domain.Book
import com.moksh.bookpedia.book.presentation.book_list.components.BookListItem
import com.moksh.bookpedia.book.presentation.book_list.components.BookSearchBar

@Composable
@Preview
private fun BookSearchBarPreview() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color.White)) {
            BookSearchBar(
                searchQuery = "Kotlin",
                onImeSearchAction = { },
                onSearchQueryChange = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun BookListItemPreview() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color.White)) {
            BookListItem(
                book = Book(
                    id = "1",
                    title = "Kotlin",
                    authors = listOf("JetBrains"),
                    imageUrl = "https://images.unsplash.com/photo-1725347212387-3bc3beed5e53?q=80&w=1588&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    averageRating = 4.5,
                    languages = listOf("English", "Hindi"),
                    numEditions = 2,
                    description = null,
                    publishedYear = null,
                    ratingsCount = null,
                    numPages = null,
                ),
                onClick = {}
            )
        }
    }
}
