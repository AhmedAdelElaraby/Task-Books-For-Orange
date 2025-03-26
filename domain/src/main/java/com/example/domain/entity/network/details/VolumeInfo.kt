package com.example.domain.entity.network.details

data class VolumeInfo(
    val allowAnonLogging: Boolean,
    val authors: List<String>,
    val canonicalVolumeLink: String,
    val contentVersion: String,
    val dimensions: Dimensions,
    val imageLinks: ImageLinks,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummary,
    val previewLink: String,
    val printType: String,
    val printedPageCount: Int,
    val publishedDate: String,
    val publisher: String,
    val readingModes: ReadingModes,
    val title: String
)