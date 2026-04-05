plugins {
  alias(libs.plugins.convention.android.library)
  alias(libs.plugins.kotlin.parcelize)
}

android {
  namespace = "com.sats.johnnydeep.features.home.api"
}

dependencies {
  api(libs.circuit.runtime)
  api(projects.core.domain.api)
}
