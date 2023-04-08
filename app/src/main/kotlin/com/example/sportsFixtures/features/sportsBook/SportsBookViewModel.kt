package com.example.sportsFixtures.features.sportsBook

import com.example.common.Result
import com.example.domain.usecases.SportsUseCase
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.Event
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.State
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.State.Effect
import com.reydix.enter.core.mvi.CoreViewModel

class SportsBookViewModel(
    private val sportsUseCase: SportsUseCase
) : CoreViewModel<Event, State, Effect>(State()) {

    override suspend fun handleEvent(event: Event) {
        when(event) {
            Event.OnScreenInitialized -> {
                sportsUseCase.getSports().collect{
                    when(it) {
                        is Result.Success -> {
                            setState { copy(isLoading = false, sportsWithEvents = it.value) }
                        }
                        is Result.Error -> {

                        }
                    }
                }
            }
        }
    }

}