package com.example.catalist.uimodel

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
    val social_needs : Int?,
    val hypoallergenic : Int?,
    val wikipedia_url : String? = null,
    val image_url : String? = null,
    val reference_image_id : String? =null
)