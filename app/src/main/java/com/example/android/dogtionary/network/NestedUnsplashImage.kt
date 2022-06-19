package com.example.android.dogtionary.network

import com.squareup.moshi.Json

data class NestedUnsplashImage (
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)