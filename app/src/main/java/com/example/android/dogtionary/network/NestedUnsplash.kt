package com.example.android.dogtionary.network

import com.squareup.moshi.Json

data class NestedUnsplash (
    @Json(name = "urls") val url: String
)
