package com.example.e_book_libruary_app.data.remote.dto

import com.squareup.moshi.Json
import retrofit2.http.Url

data class BookInfoDto(
    @field:Json(name = "id") val bookId: String,
    @field:Json(name = "volumeInfo") val volumeInfo: VolumeInfoDto,
    @field:Json(name = "saleInfo") val saleInfo: SaleInfoDto,
    @field:Json(name = "accessInfo") val accessInfo: AccessInfoDto?,
)

data class AccessInfoDto(
    @field:Json(name = "epub") val epub: EpubDto,
    @field:Json(name = "pdf") val pdf: PdfDto,
)

data class EpubDto(
    @field:Json(name = "isAvailable") val isAcsmAvailable: Boolean?,
    @field:Json(name = "acsTokenLink") val acsmLink: String?
)

data class PdfDto(
    @field:Json(name = "isAvailable") val isPdfAvailable: Boolean?,
    @field:Json(name = "acsTokenLink") val pdfLink: String?
)

data class VolumeInfoDto(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "authors") val authors: List<String>?,
    @field:Json(name = "publisher") val publisher: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "pageCount") val pageCount: Int,
    @field:Json(name = "mainCategory") val mainCategory: String?,
    @field:Json(name = "categories") val categories: List<String>?,
    @field:Json(name = "averageRating") val rating: Float?,
    @field:Json(name = "imageLinks") val imageLinks: ImageLinks?,
    @field:Json(name = "publishedDate") val publishedDate: String?,
)

data class ImageLinks(
    @field:Json(name = "thumbnail") val imageUrl: String?,
)

data class SaleInfoDto(
    @field:Json(name = "retailPrice") val retailPrice: RetailPriceDto?,
    @field:Json(name = "saleability") val saleability: String?,
    @field:Json(name = "isEbook") val isEbook: Boolean?,
)

data class RetailPriceDto(
    @field:Json(name = "amount") val price: Float?,
    @field:Json(name = "currencyCode") val currencyCode: String?,
)


