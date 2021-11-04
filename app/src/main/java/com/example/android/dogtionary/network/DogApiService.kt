package com.example.android.dogtionary.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogApiService {
    //https://dog.ceo/api/breeds/image/random
    @GET("breeds/image/random/25")
    suspend fun getRandomPhoto(): List<DogPhoto>?

    //https://dog.ceo/api/breed/hound/images/random
    @GET("breed/{breed}/images/random")
    suspend fun getPhotoByBreed(@Path("breed") breed: String): DogPhoto

}

object DogPhotoApi {
    val retrofitService: DogApiService by lazy {
        retrofit.create((DogApiService::class.java))
    }
}
