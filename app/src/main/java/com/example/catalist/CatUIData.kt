package com.example.catalist

data class CatUIData (
    val id : String,
    val name : String,
    val description: String,
    val origin: String,
    val life_span : String,
    val temperament : String,
    val trait : List<String>,
    val url : String
)