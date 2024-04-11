package com.example.catalist.apiCall

import com.example.catalist.apiCall.model.BreedsApiModel
import retrofit2.http.GET

interface BreedsApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedsApiModel>
}