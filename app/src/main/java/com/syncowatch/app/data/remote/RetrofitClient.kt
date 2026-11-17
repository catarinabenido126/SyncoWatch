package com.syncowatch.app.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Endereço provisório.
    // Substituir pelo endereço da API definitiva.
    private const val BASE_URL = "http://127.0.0.1:5001/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService =
        retrofit.create(ApiService::class.java)
}