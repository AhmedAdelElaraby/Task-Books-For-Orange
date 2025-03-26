package com.example.books.utils

import com.example.books.utils.ErrorResponse.ResponseError
import com.example.domain.entity.network.books.ResponseBooks


sealed class ApiState {
    object Loading : ApiState()
    class  Success(val data: ResponseBooks) : ApiState()
     class Failure(val e: ResponseError?) : ApiState()
   object Empty : ApiState()

}
