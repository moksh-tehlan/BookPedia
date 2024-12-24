package com.moksh.bookpedia.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.moksh.bookpedia.book.presentation.SelectedBookViewModel
import com.moksh.bookpedia.book.presentation.book_detail.BookDetailAction
import com.moksh.bookpedia.book.presentation.book_detail.BookDetailScreen
import com.moksh.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.moksh.bookpedia.book.presentation.book_list.BookListScreen
import com.moksh.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList>(
                    exitTransition = { slideOutHorizontally{initialOffset ->
                        -initialOffset
                    } },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    LaunchedEffect(true) {
                        selectedBookViewModel.selectBook(null)
                    }
                    BookListScreen(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.selectBook(book)
                            navController.navigate(Route.BookDetail(book.id))
                        }
                    )
                }

                composable<Route.BookDetail>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) { entry ->
                    val selectedBookViewModel =
                        entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val viewModel = koinViewModel<BookDetailViewModel>()
                    val selectedBook =
                        selectedBookViewModel.selectBookState.collectAsStateWithLifecycle().value

                    LaunchedEffect(selectedBook) {
                        selectedBook.let {
                            viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                        }
                    }

                    BookDetailScreen(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}