package com.sats.johnnydeep.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sats.johnnydeep.features.home.api.HomeScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator

@Composable
fun MainScreen(circuit: Circuit, modifier: Modifier = Modifier) {
  val backStack = rememberSaveableBackStack(root = HomeScreen)
  val navigator = rememberCircuitNavigator(backStack) {}

  CircuitCompositionLocals(circuit) {
    NavigableCircuitContent(
      navigator = navigator,
      backStack = backStack,
      modifier = modifier,
    )
  }
}
