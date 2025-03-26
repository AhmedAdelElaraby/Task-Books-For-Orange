package com.example.domain.repo.books

import com.example.domain.entity.network.books.ResponseBooks
import com.example.domain.entity.network.details.BookDetailsResponse


interface BooksRepo {
    suspend fun Books( q: String,maxResults:Int) : ResponseBooks
    suspend fun getBookDetails( id: String) : BookDetailsResponse


}