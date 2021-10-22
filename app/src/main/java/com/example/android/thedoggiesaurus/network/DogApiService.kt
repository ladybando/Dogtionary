package com.example.android.thedoggiesaurus.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// https://dog.ceo/api/breeds/image/random
private const val BASE_URL = "https://dog.ceo/api/breeds"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogApiService {
    @GET("/image/random")
    suspend fun getRandomPhoto(): List<DogPhoto>
}

object DogPhotoApi{
    val retrofitService: DogApiService by lazy {
        retrofit.create((DogApiService::class.java))
    }
}