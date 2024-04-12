package com.example.catalist.repository

import com.example.catalist.apiCall.BreedsApi
import com.example.catalist.apiCall.model.BreedsApiModel
import com.example.catalist.networking.retrofit

object BreedsRepository {

    private val breedsApi : BreedsApi = retrofit.create(BreedsApi ::class.java)

    suspend fun fetchAllBreeds(): List<BreedsApiModel>{
        val breeds = breedsApi.getAllBreeds()


        return breeds
    }

    suspend fun fetchOneBreed(breedId : String) : BreedsApiModel{
        val breed = breedsApi.getBreed(breedId = breedId)

        return breed
    }
}