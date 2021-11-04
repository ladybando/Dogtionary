package com.example.android.dogtionary.network

import com.squareup.moshi.Json

/*
*   // https://dog.ceo/api/breeds/image/random
* JSON
* {
  "message": "https://images.dog.ceo/breeds/hound-ibizan/n02091244_3042.jpg",
  "status": "success"
}
*
*{
*  "message": [
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10715.jpg",
  * }
        * ]*/

class DogPhoto(
    @Json(name = "message") val imageUrl: List<DogPhotoMessage>,
    @Json(name = "status") val statusResponse: String?
)