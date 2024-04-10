package com.example.catalist

object BreedsRepository {

    private val breedsApi : BreedsApi = retrofit.create(BreedsApi ::class.java)

    suspend fun fetchAllBreeds(): List<BreedsApiModel>{
        val breeds = breedsApi.getAllBreeds()

        return breeds
    }
}