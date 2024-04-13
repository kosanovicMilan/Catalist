package com.example.catalist.search

import com.example.catalist.uimodel.CatUIData

interface CatsSearchContract {

    data class CatsSearchState(
        val breeds : List<CatUIData> = emptyList(),
        val loading : Boolean = false,
        val error : Boolean = false
    )
}