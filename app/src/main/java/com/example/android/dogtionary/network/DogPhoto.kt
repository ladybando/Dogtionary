package com.example.android.dogtionary.network

import com.squareup.moshi.Json

/*
*   // https://dog.ceo/api/breeds/image/random
* JSON
* {
  "message": "https://images.dog.ceo/breeds/hound-ibizan/n02091244_3042.jpg",
  "status": "success"
}*/

class DogPhoto(
    @Json(name = "message") val imageUrl: String?,
    @Json(name = "status") val statusResponse: String?
)