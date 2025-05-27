package com.artemissoftware.njordshop.core.network.dto


import com.squareup.moshi.Json

data class MetaDto(
    @field:Json(name = "barcode")
    val barcode: String = "",
    @field:Json(name = "createdAt")
    val createdAt: String = "",
    @field:Json(name = "qrCode")
    val qrCode: String = "",
    @field:Json(name = "updatedAt")
    val updatedAt: String = ""
)