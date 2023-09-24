package com.example.kotlinpractice4

import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}