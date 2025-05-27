package com.artemissoftware.njordshop.core.network.dto


import com.squareup.moshi.Json

data class ReviewDto(
    @field:Json(name = "comment")
    val comment: String = "",
    @field:Json(name = "date")
    val date: String = "",
    @field:Json(name = "rating")
    val rating: Int = 0,
    @field:Json(name = "reviewerEmail")
    val reviewerEmail: String = "",
    @field:Json(name = "reviewerName")
    val reviewerName: String = ""
)