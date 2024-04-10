package com.example.catalist

interface BreedListContract {

    data class BreedListState(
        val breeds : List<CatUIData> = emptyList(),
        val loading : Boolean = false
    )

}