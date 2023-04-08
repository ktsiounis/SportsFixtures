package com.example.data.api

import com.example.common.Result
import com.example.common.resolveSportIconFromId
import com.example.domain.models.Event
import com.example.domain.models.Sport

fun Result<List<SportRaw>?>.toSportsResult(): Result<List<Sport>> {
    return when(this) {
        is Result.Success -> {
            val sportList = this.value?.let {
                it.map { raw ->
                    Sport(
                        name = raw.name ?: "",
                        id = raw.id ?: "",
                        events = raw.events.toDomainEvents(),
                        icon = raw.id?.resolveSportIconFromId() ?: -1
                    )
                }
            } ?: emptyList()
            Result.Success(sportList)
        }
        is Result.Error -> this
    }
}

fun List<EventRaw?>?.toDomainEvents(): List<Event> {
    return this?.let {
        it.map { raw ->
            Event(
                name = raw?.name ?: "N/A",
                id = raw?.id ?: "",
                sportId = raw?.sportId ?: "",
                startTime = raw?.startTime ?: -1
            )
        }
    } ?: emptyList()
}