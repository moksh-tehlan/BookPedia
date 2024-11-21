package com.moksh.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class BookListViewModel : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()
            .onStart {  }
            .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            BookListState()
        )

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnSearchQueryChange -> {
                _state.update{
                    it.copy(searchQuery = action.query)
                }
            }
            is BookListAction.OnBookClick -> {

            }
            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedIndex = action.index)
                }
            }
        }
    }
}
