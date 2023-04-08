package com.example.sportsFixtures.features.sportsBook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.designsystem.Theme.spacing
import com.example.domain.models.Sport
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.Event
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun SportBookScreen(
    modifier: Modifier
) {
    val viewModel: SportsBookViewModel = koinViewModel()
    val state = viewModel.state().collectAsState()

    viewModel.onEvent(Event.OnScreenInitialized)

    SportsBookContent(
        modifier = modifier,
        uiState = state.value
    )
}

@Composable
private fun SportsBookContent(
    modifier: Modifier,
    uiState: State
) {

    LazyColumn(
        modifier = modifier
    ) {
        items(uiState.sportsWithEvents) {
            Text(text = it.name)
        }
    }

}

@Composable
private fun SportSectionView(
    sport: Sport
) {

    Column(
        modifier = Modifier.padding(vertical = spacing.spacing01)
    ) {

        Row(

        ) {
            Text(
                text = sport.name
            )
        }

    }

}