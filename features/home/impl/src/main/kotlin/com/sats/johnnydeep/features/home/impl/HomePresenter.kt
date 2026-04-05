package com.sats.johnnydeep.features.home.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.sats.core.domain.api.history.HistoryRepository
import com.sats.johnnydeep.features.home.api.HomeScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.launch

@CircuitInject(HomeScreen::class, AppScope::class)
@Inject
class HomePresenter(
  private val historyRepository: HistoryRepository,
) : Presenter<HomeScreen.State> {
  @Composable
  override fun present(): HomeScreen.State {
    val previousIntents by historyRepository.getIntentsHistory()
      .collectAsState(initial = emptyList())

    var inputValue by rememberSaveable { mutableStateOf("") }
    var intentFailedNotice by rememberSaveable { mutableStateOf(false) }
    var intentDeletedNotice: HomeScreen.IntentDeletedNotice? by remember { mutableStateOf(null) }

    val coroutineScope = rememberCoroutineScope()

    return HomeScreen.State(
      inputValue = inputValue,
      previousIntents = previousIntents,
      intentDeletedNotice = intentDeletedNotice,
      intentFailedNotice = intentFailedNotice,
    ) { event ->
      when (event) {
        is HomeScreen.Event.InputValueChanged -> {
          inputValue = event.newValue
        }

        is HomeScreen.Event.PreviousIntentClicked -> {
          inputValue = event.previousIntent.uri
        }

        is HomeScreen.Event.UriOpenedSuccessfully -> {
          coroutineScope.launch {
            historyRepository.addOrUpdateIntent(event.uri)
          }
        }

        is HomeScreen.Event.UriFailedToOpen -> {
          coroutineScope.launch {
            historyRepository.addOrUpdateIntent(event.uri)
            intentFailedNotice = true
          }
        }

        is HomeScreen.Event.PreviousIntentRemoved -> {
          val previousIntent = event.previousIntent
          coroutineScope.launch {
            historyRepository.removePreviousIntent(previousIntent)
            intentDeletedNotice = HomeScreen.IntentDeletedNotice(
              undo = {
                coroutineScope.launch {
                  historyRepository.addOrUpdateIntent(previousIntent)
                }
              },
            )
          }
        }

        HomeScreen.Event.NoticeDismissed -> {
          intentFailedNotice = false
          intentDeletedNotice = null
        }
      }
    }
  }
}
