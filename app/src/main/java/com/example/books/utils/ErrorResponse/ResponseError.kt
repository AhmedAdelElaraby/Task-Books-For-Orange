package com.example.books.utils.ErrorResponse

data class ResponseError(
    val error: Errors?
)

data class Errors(
    val code: Int?,
    val errors: List<ErrorX>?,
    val message: String
)

data class ErrorX(
    val domain: String?,
    val message: String?,
    val reason: String?
)