package com.example.domain.usecases

import com.example.domain.contracts.SportsRepositoryContract
import com.example.domain.models.Event

class SportsUseCase(
    private val restRepository: SportsRepositoryContract,
) {

    suspend fun getSports() = restRepository.getSports()
    suspend fun setEventAsFavorite(event: Event, isFavorite: Boolean) = restRepository.setEventAsFavorite(event, isFavorite)

}