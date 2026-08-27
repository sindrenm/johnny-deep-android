package com.sats.johnnydeep.di

import android.app.Application
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.plus
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.serialization.CircuitSerializerRegistration
import com.slack.circuit.serialization.SerializableCircuitSaver
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import dev.zacsweers.metrox.android.MetroAppComponentProviders

@DependencyGraph(AppScope::class)
interface AppGraph : MetroAppComponentProviders {
  @Provides
  @SingleIn(AppScope::class)
  fun provideCircuit(
    presenterFactories: Set<Presenter.Factory>,
    uiFactories: Set<Ui.Factory>,
    serializerRegistrations: Set<CircuitSerializerRegistration>,
  ): Circuit {
    return Circuit.Builder()
      .setCircuitSaver { fallback -> SerializableCircuitSaver(serializerRegistrations) + fallback }
      .addUiFactories(uiFactories)
      .addPresenterFactories(presenterFactories)
      .build()
  }

  @DependencyGraph.Factory
  fun interface Factory {
    fun create(@Provides application: Application): AppGraph
  }
}
