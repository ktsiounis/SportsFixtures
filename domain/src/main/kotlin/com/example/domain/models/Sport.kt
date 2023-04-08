package com.example.domain.models

import java.io.Serializable

data class Sport(
    val name: String,
    val id: String,
    val icon: Int,
    val events: List<Event>
) : Serializable
