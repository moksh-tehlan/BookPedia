package com.moksh.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.moksh.bookpedia.book.data.database.DatabaseFactory
import com.moksh.bookpedia.book.data.database.FavouriteBookDatabase
import com.moksh.bookpedia.book.data.network.KtorRemoteDataSource
import com.moksh.bookpedia.book.data.network.RemoteBookDataSource
import com.moksh.bookpedia.book.data.repository.DefaultBookRepository
import com.moksh.bookpedia.book.domain.BookRepository
import com.moksh.bookpedia.book.presentation.SelectedBookViewModel
import com.moksh.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.moksh.bookpedia.book.presentation.book_list.BookListViewModel
import com.moksh.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<FavouriteBookDatabase>().dao
    }
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}