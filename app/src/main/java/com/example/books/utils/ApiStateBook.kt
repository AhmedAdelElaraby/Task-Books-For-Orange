package com.example.books.utils

import com.example.books.utils.ErrorResponse.ResponseError
import com.example.domain.entity.network.books.ResponseBooks
import com.example.domain.entity.network.details.BookDetailsResponse


sealed class ApiStateBook {
    object Loading : ApiStateBook()
    class  Success(val data: BookDetailsResponse) : ApiStateBook()
     class Failure(val e: ResponseError?) : ApiStateBook()
   object Empty : ApiStateBook()

}
