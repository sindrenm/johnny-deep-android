import dev.zacsweers.metro.gradle.ExperimentalMetroGradleApi

plugins {
  alias(libs.plugins.convention.android.library)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.metro)
}

metro {
  @OptIn(ExperimentalMetroGradleApi::class)
  enableCircuitCodegen.set(true)
}

android {
  namespace = "com.sats.johnnydeep.features.home.api"
}

dependencies {
  api(libs.circuit.runtime)
  api(libs.circuit.serialization)
  api(projects.core.domain.api)
}
