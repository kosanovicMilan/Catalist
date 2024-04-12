package com.example.catalist.apiCall.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BreedsApiModel(
    val  weight: Weight,
    val id : String,
    val name : String,
    val description: String,
    @SerialName("origin") val origin: String,
    val life_span : String,
    val temperament : String,
    val grooming : Int?,
    val energy_level : Int?,
    val intelligence : Int?,
    val stranger_friendly : Int?,
    val vocalisation : Int?,
    val social_needs : Int?,
    val wikipedia_url : String? = null ,
    val hypoallergenic : Int?,
    val image: Image? = null
)
@Serializable
data class Weight(
    val imperial: String,
    val metric : String
)

@Serializable
data class Image(
    val id: String,
    val url: String,
)


