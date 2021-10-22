package com.example.android.thedoggiesaurus.network

import com.squareup.moshi.Json

/*
*   // https://dog.ceo/api/breeds/image/random
* JSON
* {
  "message": "https://images.dog.ceo/breeds/hound-ibizan/n02091244_3042.jpg",
  "status": "success"
}*/

class DogPhoto(
    @Json(name = "message") val messageUrl: String?,
    @Json(name = "status") val statusResponse: String?
)