package com.example.domain.models

data class Event(
    val id: String,
    val sportId: String,
    val name: String,
    val startTime: Long,
    var isFavorite: Boolean = false,
)
