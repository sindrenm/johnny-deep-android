import dev.zacsweers.metro.gradle.ExperimentalMetroGradleApi

plugins {
  alias(libs.plugins.convention.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.metro)
}

@OptIn(ExperimentalMetroGradleApi::class)
metro {
  enableCircuitCodegen.set(true)
}

android {
  namespace = "com.sats.johnnydeep"

  defaultConfig {
    applicationId = "com.sats.johnnydeep"

    versionCode = 1
    versionName = "0.1.0"
  }
}

dependencies {
  implementation(libs.androidx.activity.compose)
  implementation(libs.circuit.foundation)
  implementation(libs.metrox.android)
  implementation(projects.core.domain.api)
  implementation(projects.core.domain.impl)
  implementation(projects.core.ui.theme)
  implementation(projects.features.home.api)
  implementation(projects.features.home.impl)
}
