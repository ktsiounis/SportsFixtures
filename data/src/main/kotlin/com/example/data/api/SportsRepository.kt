package com.example.data.api

import com.example.domain.contracts.SportsRepositoryContract
import com.example.domain.models.Sport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.example.common.Result
import com.example.domain.models.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SportsRepository(
    private val client: SportsApiClient
) : SportsRepositoryContract {

    private val _sports = MutableStateFlow(emptyList<Sport>())
    override val sports: StateFlow<List<Sport>> = _sports

    override suspend fun fetchSports(): Flow<Result<List<Sport>>> = flowOf(
        client.getSports().toSportsResult().also {
            if (it is Result.Success) {
                _sports.value = it.value
            }
        }
    )

    override fun setEventAsFavorite(event: Event, isFavorite: Boolean) {
        val updatedSports = mutableListOf<Sport>()
        _sports.value.forEach { sport ->
            if (sport.id == event.sportId) {
                val updatedEvents= sport.events.map { innerEvent ->
                    if (innerEvent.id == event.id) innerEvent.copy(isFavorite = isFavorite) else innerEvent
                }
                updatedSports.add(sport.copy(events = updatedEvents))
            } else {
                updatedSports.add(sport)
            }
        }
        _sports.value = updatedSports
    }

}