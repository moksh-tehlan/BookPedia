package com.moksh.bookpedia.book.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moksh.bookpedia.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SelectedBookViewModel : ViewModel() {
    private val _selectBookState = MutableStateFlow<Book?>(null)
    val selectBookState = _selectBookState.asStateFlow()
            .onStart {  }
            .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    fun selectBook(book: Book?) {
        _selectBookState.value = book
    }

}