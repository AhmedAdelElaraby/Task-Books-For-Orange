package com.example.domain.entity.network.books

data class ResponseBooks(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)