package com.sats.johnnydeep.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sats.johnnydeep.core.ui.theme.JohnnyDeepTheme
import com.slack.circuit.foundation.Circuit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.android.ActivityKey

@Inject
@ActivityKey
@ContributesIntoMap(AppScope::class, binding<Activity>())
class MainActivity(
  private val circuit: Circuit,
) : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()

    super.onCreate(savedInstanceState)

    setContent {
      JohnnyDeepTheme {
        MainScreen(circuit)
      }
    }
  }
}
