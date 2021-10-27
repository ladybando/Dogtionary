package com.example.android.thedoggiesaurus.network

import com.squareup.moshi.Json

class BreedPhotos (
    @Json(name = "breed") val breed: String?
)