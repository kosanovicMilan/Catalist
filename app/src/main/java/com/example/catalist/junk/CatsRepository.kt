package com.example.catalist.junk

object CatsRepository {
    private var mutableCats = SampleCats.toMutableList();

    fun allCats() : List<CatData> {
        return mutableCats;
    }

    fun getCatById(id : String) : CatData? {
        return mutableCats.find { it.id == id }
    }
}