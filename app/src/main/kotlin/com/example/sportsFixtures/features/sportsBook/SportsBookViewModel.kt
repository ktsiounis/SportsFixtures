package com.example.sportsFixtures.features.sportsBook

import androidx.lifecycle.viewModelScope
import com.example.common.Result
import com.example.domain.usecases.SportsUseCase
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.Event
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.State
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.State.Effect
import com.reydix.enter.core.mvi.CoreViewModel
import kotlinx.coroutines.launch

class SportsBookViewModel(
    private val sportsUseCase: SportsUseCase
) : CoreViewModel<Event, State, Effect>(State()) {

    override suspend fun handleEvent(event: Event) {
        when(event) {
            Event.OnScreenInitialized -> {
                sportsUseCase.fetchSports().collect{
                    when(it) {
                        is Result.Error -> {
                            setState { copy(errorMsg = it.message) }
                        }
                        else -> {}
                    }
                }

                sportsUseCase.sports.collect {
                    setState { copy(sportsWithEvents = it) }
                }
            }

            is Event.OnFavoriteButtonClicked -> {
                sportsUseCase.setEventAsFavorite(event.event, event.isFavorite)
            }
        }
    }

}