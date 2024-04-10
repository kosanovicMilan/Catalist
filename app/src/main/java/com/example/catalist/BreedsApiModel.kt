package com.example.catalist

import kotlinx.serialization.Serializable

@Serializable
data class BreedsApiModel(
    val id : String,
    val name : String,
    val description: String,
    val origin: String,
    val life_span : String,
    val temperament : String,
    val trait : List<String>,
    val url: String,
)
