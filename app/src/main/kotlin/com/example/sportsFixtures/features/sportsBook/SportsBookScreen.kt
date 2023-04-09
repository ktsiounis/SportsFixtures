package com.example.sportsFixtures.features.sportsBook

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.BlueSportSectionSep
import com.example.designsystem.DarkBlueBackground
import com.example.designsystem.StarBorder
import com.example.designsystem.StarFilled
import com.example.designsystem.Theme.sizing
import com.example.designsystem.Theme.spacing
import com.example.designsystem.Typography
import com.example.domain.models.Sport
import com.example.sportsFixtures.R
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.Event
import com.example.sportsFixtures.features.sportsBook.SportsBookScreenContract.State
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

import com.example.domain.models.Event as SportEvent

@Composable
fun SportBookScreen(
    modifier: Modifier,
    viewModel: SportsBookViewModel = koinViewModel()
) {
    val state by viewModel.state().collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(Event.OnScreenInitialized)
    }

    SportsBookContent(
        modifier = modifier,
        uiState = state,
        onEvent = { viewModel.onEvent(it) }
    )

    if (!state.errorMsg.isNullOrBlank()) {
        Toast.makeText(LocalContext.current, state.errorMsg, LENGTH_SHORT).show()
        viewModel.onEvent(Event.OnErrorMessageShown)
    }
}

@Composable
private fun SportsBookContent(
    modifier: Modifier,
    uiState: State,
    onEvent: (Event) -> Unit
) {

    if (uiState.sportsWithEvents.isNotEmpty()) {
        SportList(
            modifier = modifier,
            sportsWithEvents = uiState.sportsWithEvents,
            onEvent = onEvent
        )
    } else {
        EmptyState(modifier)
    }

}

@Composable
private fun SportList(
    modifier: Modifier,
    sportsWithEvents: List<Sport>,
    onEvent: (Event) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = DarkBlueBackground)
    ) {
        items(sportsWithEvents) {
            SportSectionView(
                sport = it,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun SportSectionView(
    sport: Sport,
    onEvent: (Event) -> Unit
) {

    var isSectionExpanded by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spacing01)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = BlueSportSectionSep)
                .padding(vertical = spacing.spacing01)
                .clickable {
                    isSectionExpanded = !isSectionExpanded
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = spacing.spacing01)
                    .size(sizing.large),
                painter = painterResource(id = sport.icon),
                contentDescription = stringResource(R.string.sport_section_icon_content_description),
                tint = Color.White
            )
            Text(
                text = sport.name,
                style = Typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .padding(horizontal = spacing.spacing01)
                    .size(sizing.xLarge)
                    .rotate(if (!isSectionExpanded) 180f else 0f),
                painter = painterResource(id = R.drawable.arrow_down_24),
                contentDescription = stringResource(R.string.arrow_icon_for_section_expansion_content_description),
                tint = Color.White
            )
        }

        AnimatedVisibility(
            visible = isSectionExpanded,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.spacing0_05)
            ) {
                items(sport.events.sortedBy { event -> !event.isFavorite }) {
                    EventView(
                        event = it,
                        onEvent = onEvent
                    )
                }
            }
        }

    }

}

@Composable
private fun EventView(
    event: SportEvent,
    onEvent: (Event) -> Unit
) {
    Column(
        modifier = Modifier
            .width(sizing.sportEventItemWidth)
            .padding(vertical = spacing.spacing01)
            .padding(horizontal = spacing.spacing0_05),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CountdownTimer(endDate = event.startTime)

        Icon(
            modifier = Modifier
                .padding(vertical = spacing.spacing0_05)
                .clickable {
                    onEvent(Event.OnFavoriteButtonClicked(event, !event.isFavorite))
                },
            painter = painterResource(id = if (event.isFavorite) R.drawable.baseline_star_24 else R.drawable.star_border_24),
            contentDescription = stringResource(R.string.favorite_icon_content_description),
            tint = if (event.isFavorite) StarFilled else StarBorder
        )

        Text(
            text = event.name.split("-")[0].trim(),
            style = Typography.bodySmall,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = event.name.split("-")[1].trim(),
            style = Typography.bodySmall,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun CountdownTimer(endDate: Long) {
    val timeRemaining = remember { mutableStateOf("") }
    val date = Date(endDate*1000)
    val currentTime = remember { mutableStateOf(Calendar.getInstance()) }
    val context = LocalContext.current

    LaunchedEffect(endDate) {
        while (currentTime.value.timeInMillis < date.time) {
            val diff = date.time - currentTime.value.timeInMillis
            val seconds = diff / 1000 % 60
            val minutes = diff / (1000 * 60) % 60
            val hours = diff / (1000 * 60 * 60) % 24

            timeRemaining.value = "$hours:$minutes:$seconds"

            delay(1000)
            currentTime.value = Calendar.getInstance()
        }
        timeRemaining.value = context.getString(R.string.event_completed)
    }

    Text(
        modifier = Modifier
            .border(
                border = BorderStroke(sizing.borderDefault, Color.White),
                shape = RoundedCornerShape(sizing.strokeDefault)
            )
            .padding(spacing.spacing0_05),
        text = timeRemaining.value,
        style = Typography.bodySmall,
        color = Color.White,
    )

}

@Composable
private fun EmptyState(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = DarkBlueBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(sizing.xLarge),
            painter = painterResource(id = R.drawable.warning_amber_24),
            contentDescription = stringResource(R.string.warning_icon_for_empty_state_content_description),
            tint = Color.White
        )
        Text(
            modifier = Modifier
                .padding(horizontal = spacing.spacing05)
                .padding(top = spacing.spacing01),
            text = stringResource(R.string.empty_state_message),
            style = Typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreviewSportSectionView() {
    SportSectionView(
        sport = Sport(
            name = "FOOTBALL",
            id = "FOOT",
            icon = com.example.common.R.drawable.soccer_24,
            events = MutableList(6) {
                SportEvent(
                    id = "",
                    sportId = "",
                    name = "Panathinaikos - Olympiakos",
                    startTime = 1681795200,
                    isFavorite = Random.nextBoolean()
                )
            }
        ),
        onEvent = {}
    )
}

@Preview
@Composable
private fun PreviewSportsBookContent() {
    SportsBookContent(
        modifier = Modifier,
        uiState = State(
            sportsWithEvents = MutableList(7) {
                Sport(
                    name = "FOOTBALL",
                    id = "FOOT",
                    icon = com.example.common.R.drawable.soccer_24,
                    events = MutableList(6) {
                        SportEvent(
                            id = "",
                            sportId = "",
                            name = "Panathinaikos - Olympiakos",
                            startTime = 1681795200,
                            isFavorite = Random.nextBoolean()
                        )
                    }
                )
            }
        ),
        onEvent = {}
    )
}

@Preview
@Composable
private fun PreviewSportsBookContentEmpty() {
    SportsBookContent(
        modifier = Modifier,
        uiState = State(),
        onEvent = {}
    )
}