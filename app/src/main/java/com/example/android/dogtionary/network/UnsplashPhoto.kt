package com.example.android.dogtionary.network

import com.squareup.moshi.Json

data class UnsplashPhoto (
    @Json(name = "url") val photoUrl: NestedUnsplash?
)
