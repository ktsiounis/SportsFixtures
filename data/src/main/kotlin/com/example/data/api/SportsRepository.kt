package com.example.data.api

import com.example.domain.contracts.SportsRepositoryContract
import com.example.domain.models.Sport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.example.common.Result
import com.example.domain.models.Event

class SportsRepository(
    private val client: SportsApiClient
) : SportsRepositoryContract {

    private var sports = emptyList<Sport>()

    override suspend fun getSports(): Flow<Result<List<Sport>>> = flowOf(
        client.getSports().toSportsResult().also {
            if (it is Result.Success) {
                sports = it.value
            }
        }
    )

    override suspend fun setEventAsFavorite(event: Event, isFavorite: Boolean): Flow<List<Sport>> {
        sports.find { it.id == event.sportId }
            ?.events?.find { it.id == event.id }
            ?.isFavorite = isFavorite
        return flowOf(sports)
    }

}