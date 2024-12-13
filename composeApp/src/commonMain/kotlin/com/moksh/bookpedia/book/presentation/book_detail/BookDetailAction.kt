package com.moksh.bookpedia.book.presentation.book_detail

import com.moksh.bookpedia.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavouriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book?): BookDetailAction
}