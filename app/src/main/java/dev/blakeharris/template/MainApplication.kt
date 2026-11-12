package dev.blakeharris.template

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The app's [Application]. `@HiltAndroidApp` makes this the root of the dependency graph: Hilt
 * collects every module's `di/` bindings here and checks the entire graph at build time.
 */
@HiltAndroidApp
class MainApplication : Application()
