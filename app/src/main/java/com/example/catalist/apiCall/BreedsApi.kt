package com.example.catalist.apiCall

import com.example.catalist.apiCall.model.BreedsApiModel
import com.example.catalist.apiCall.model.ImageApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreedsApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedsApiModel>

    @GET("breeds/{id}")
    suspend fun getBreed(
        @Path("id") breedId: String
    ) : BreedsApiModel
//live_CEwHxfQ3Wzx8mBzpbIArzAMViR1DNUDsDe13x9TUy6hOXyu3cIhDC7hMAqpFAEvo
    @GET("images/search")
    suspend fun getImage(
        @Query("breed_ids") id : String
    ) : List<ImageApiModel>
}