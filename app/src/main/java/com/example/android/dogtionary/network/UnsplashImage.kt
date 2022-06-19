package com.example.android.dogtionary.network

data class UnsplashImage (
    val urls: NestedUnsplashImage,
    val height: Int,
    val id: String,
    val width: Int
)