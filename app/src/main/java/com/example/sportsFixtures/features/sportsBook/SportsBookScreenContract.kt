package com.example.sportsFixtures.features.sportsBook

import com.example.domain.models.Sport
import com.reydix.enter.core.mvi.UiEffect
import com.reydix.enter.core.mvi.UiEvent
import com.reydix.enter.core.mvi.UiState

interface SportsBookScreenContract {

    sealed interface Event : UiEvent {

        object OnScreenInitialized : Event

    }

    data class State(
        val sportsWithEvents: List<Sport> = emptyList(),
        val isLoading: Boolean = false,
    ) : UiState {

        sealed interface Effect : UiEffect

    }

}