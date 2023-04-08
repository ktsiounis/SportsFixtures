package com.example.domain.models

data class Event(
    val id: String,
    val name: String,
    val startTime: Long,
    val isFavorite: Boolean = false,
)
