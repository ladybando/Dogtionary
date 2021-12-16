package com.example.android.dogtionary.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://source.unsplash.com/"
/*potential HTTP fix for JSON error https://mobikul.com/handle-html-response-from-http-request-using-retrofit/
https://stackoverflow.com/questions/31496666/get-html-response-with-retrofit/31499867
*/

private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UnsplashApiService{
    //endpoint photos/random
    @GET("random")
    suspend fun getRandomImage(): String
}

object UnsplashApi{
    val retrofitService: UnsplashApiService by lazy {
        retrofit.create(UnsplashApiService::class.java)
    }
}