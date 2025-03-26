package com.example.domain.usecase.books

import com.example.domain.entity.network.books.ResponseBooks
import com.example.domain.entity.network.details.BookDetailsResponse
import com.example.domain.repo.books.BooksRepo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BooksUseCase (private val repo: BooksRepo) {

    suspend fun Books(q:String,max:Int) = flow<ResponseBooks> {
        emit(repo.Books(q,max))
    }.flowOn(Dispatchers.IO)

    suspend fun BooksDetails(id:String) = flow<BookDetailsResponse> {
        emit(repo.getBookDetails(id))
    }.flowOn(Dispatchers.IO)



}