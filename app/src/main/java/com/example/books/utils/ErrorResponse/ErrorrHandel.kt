package com.mediaheist.dizzly.ErrorResponse

import com.example.books.utils.ErrorResponse.ResponseError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException

fun convertErrorBody(throwable: HttpException): ResponseError?{

    return try {
        val error=throwable.response()?.errorBody()?.charStream()
        val gson=Gson()
        val type = object : TypeToken<ResponseError>() {}.type
        var errorResponse: ResponseError? =gson.fromJson(error,type)
        errorResponse
    } catch (exception :Exception){

        null
    }

}