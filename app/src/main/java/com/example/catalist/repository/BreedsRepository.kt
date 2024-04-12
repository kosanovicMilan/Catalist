package com.example.catalist.repository

import android.util.Log
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
        // OVO RADI
        Log.d("macaClick","Uspesno je dohvatio jednu rasu...")
        Log.d("macaClick","rasa: ${breed.name}")
        return breed
    }
}