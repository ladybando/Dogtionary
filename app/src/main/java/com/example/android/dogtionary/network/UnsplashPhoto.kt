package com.example.android.dogtionary.network

import com.squareup.moshi.Json

data class UnsplashPhoto (
    @Json(name = "urls") val photoUrl: String
)
