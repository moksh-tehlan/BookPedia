package com.moksh.bookpedia.di

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
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}