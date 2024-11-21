package com.moksh.bookpedia.book.presentation.book_list

import com.moksh.bookpedia.book.domain.Book
import com.moksh.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery : String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favouriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedIndex : Int = 0,
    val error: UiText? = null,
)
