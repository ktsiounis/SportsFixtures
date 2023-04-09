package com.example.domain.usecases

import com.example.domain.contracts.SportsRepositoryContract
import com.example.domain.models.Event

class SportsUseCase(
    private val restRepository: SportsRepositoryContract,
) {

    val sports = restRepository.sports

    suspend fun fetchSports() = restRepository.fetchSports()
    fun setEventAsFavorite(event: Event, isFavorite: Boolean) = restRepository.setEventAsFavorite(event, isFavorite)

}