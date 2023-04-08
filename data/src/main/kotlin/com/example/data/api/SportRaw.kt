package com.example.data.api

import com.google.gson.annotations.SerializedName

data class SportRaw(
    @SerializedName("i") val id: String?,
    @SerializedName("d") val name: String?,
    @SerializedName("e") val events: List<EventRaw?>?,
)

data class EventRaw(
    @SerializedName("i") val id: String?,
    @SerializedName("si") val sportId: String?,
    @SerializedName("d") val name: String?,
    @SerializedName("tt") val startTime: Long?
)
