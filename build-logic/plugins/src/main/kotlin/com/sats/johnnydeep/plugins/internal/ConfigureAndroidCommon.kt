package com.sats.johnnydeep.plugins.internal

import com.android.build.api.dsl.CommonExtension

internal fun CommonExtension.configureCommon() {
  compileSdk {
    version = release(37) {
      minorApiLevel = 2
    }
  }

  defaultConfig.minSdk {
    version = release(28)
  }
}
