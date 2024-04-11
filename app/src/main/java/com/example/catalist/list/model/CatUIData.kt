package com.example.catalist.list.model

data class CatUIData (
    val id : String,
    val weight: String,
    val name : String,
    val description: String,
    val origin: String,
    val life_span : String,
    val temperament : String,
    val grooming : Int?,
    val energy_level : Int?,
    val intelligence : Int?,
    val stranger_friendly : Int?,
    val vocalisation : Int?,
    val hypoallergenic : Int?,
)