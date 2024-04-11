package com.example.catalist.junk

import androidx.compose.ui.graphics.vector.ImageVector

data class CatData(
    val id: String,
    val raceName: String,
    val lifeSpan : String,
    val details: String,
    val imagePath : String,
    val traitList : List<String>
)