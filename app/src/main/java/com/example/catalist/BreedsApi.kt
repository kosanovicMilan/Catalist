package com.example.catalist

import retrofit2.http.GET

interface BreedsApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedsApiModel>
}