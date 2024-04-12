package com.example.catalist.details

import com.example.catalist.uimodel.CatUIData


interface BreedDetailsContract {

    data class BreedDetailsState(
        val breedId : String,
        val breed : CatUIData? = null,
        val error : Boolean = false,
        val loading : Boolean = false
    )
}