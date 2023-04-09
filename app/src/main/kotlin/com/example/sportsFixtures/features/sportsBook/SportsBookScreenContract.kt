package com.example.sportsFixtures.features.sportsBook

import com.example.domain.models.Sport
import com.reydix.enter.core.mvi.UiEffect
import com.reydix.enter.core.mvi.UiEvent
import com.reydix.enter.core.mvi.UiState
import com.example.domain.models.Event as SportEvent

interface SportsBookScreenContract {

    sealed interface Event : UiEvent {

        object OnScreenInitialized : Event
        data class OnFavoriteButtonClicked(val event: SportEvent, val isFavorite: Boolean) : Event

    }

    data class State(
        val sportsWithEvents: List<Sport> = emptyList(),
        val errorMsg: String? = null,
    ) : UiState {

        sealed interface Effect : UiEffect

    }

}