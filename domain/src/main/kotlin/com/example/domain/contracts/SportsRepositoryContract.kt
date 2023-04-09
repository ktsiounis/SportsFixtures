package com.example.domain.contracts

import com.example.domain.models.Sport
import kotlinx.coroutines.flow.Flow
import com.example.common.Result
import com.example.domain.models.Event
import kotlinx.coroutines.flow.StateFlow

interface SportsRepositoryContract {

    val sports: StateFlow<List<Sport>>
    suspend fun fetchSports(): Flow<Result<List<Sport>>>
    fun setEventAsFavorite(event: Event, isFavorite: Boolean)

}