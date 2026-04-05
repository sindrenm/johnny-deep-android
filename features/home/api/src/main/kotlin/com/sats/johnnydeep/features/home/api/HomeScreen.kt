package com.sats.johnnydeep.features.home.api

import com.sats.core.domain.api.history.models.PreviousIntent
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen {
  data class State(
    val inputValue: String = "",
    val previousIntents: List<PreviousIntent> = emptyList(),
    val intentDeletedNotice: IntentDeletedNotice? = null,
    val intentFailedNotice: Boolean = false,
    val eventSink: (Event) -> Unit,
  ) : CircuitUiState

  sealed interface Event : CircuitUiEvent {
    data class InputValueChanged(val newValue: String) : Event
    data class PreviousIntentClicked(val previousIntent: PreviousIntent) : Event
    data class PreviousIntentRemoved(val previousIntent: PreviousIntent) : Event
    data class UriOpenedSuccessfully(val uri: String) : Event
    data class UriFailedToOpen(val uri: String) : Event
    data object NoticeDismissed : Event
  }

  data class IntentDeletedNotice(val undo: () -> Unit)
}
