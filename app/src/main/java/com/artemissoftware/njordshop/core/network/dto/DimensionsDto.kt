package com.artemissoftware.njordshop.core.network.dto


import com.squareup.moshi.Json

data class DimensionsDto(
    @field:Json(name = "depth")
    val depth: Double = 0.0,
    @field:Json(name = "height")
    val height: Double = 0.0,
    @field:Json(name = "width")
    val width: Double = 0.0
)