package com.example.catalist.list

import com.example.catalist.list.model.CatUIData

interface BreedListContract {

    data class BreedListState(
        val breeds : List<CatUIData> = emptyList(),
        val loading : Boolean = false
    )

}