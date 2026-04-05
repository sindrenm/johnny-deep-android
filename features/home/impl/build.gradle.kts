import dev.zacsweers.metro.gradle.ExperimentalMetroGradleApi

plugins {
  alias(libs.plugins.convention.android.library)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.metro)
}

metro {
  @OptIn(ExperimentalMetroGradleApi::class)
  enableCircuitCodegen.set(true)
}

android {
  namespace = "com.sats.johnnydeep.features.home.impl"
}

dependencies {
  api(libs.circuit.runtime)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.core)
  implementation(libs.circuit.foundation)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.datetime)
  implementation(projects.core.domain.api)
  implementation(projects.features.home.api)
}
