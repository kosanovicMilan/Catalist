package com.example.catalist.list

import com.example.catalist.uimodel.CatUIData

interface BreedListContract {

    data class BreedListState(
        val breeds : List<CatUIData> = emptyList(),
        val loading : Boolean = false
    )

}