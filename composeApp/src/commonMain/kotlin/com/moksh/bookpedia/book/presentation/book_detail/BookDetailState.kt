package com.moksh.bookpedia.book.presentation.book_detail

import com.moksh.bookpedia.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavourite: Boolean = false,
    val book: Book? = null,

)
