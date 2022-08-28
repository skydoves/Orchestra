package com.skydoves.orchestra

object Versions {
  internal const val ANDROID_GRADLE_PLUGIN = "7.2.1"
  internal const val ANDROID_GRADLE_SPOTLESS = "6.7.0"
  internal const val GRADLE_NEXUS_PUBLISH_PLUGIN = "1.1.0"
  internal const val KOTLIN = "1.7.10"
  internal const val KOTLIN_GRADLE_DOKKA = "1.7.10"
  internal const val KOTLIN_BINARY_VALIDATOR = "0.11.0"

  internal const val APPCOMPAT = "1.4.1"
  internal const val MATERIAL = "1.6.0"
  internal const val CORE_KTX = "1.7.0"
  internal const val LIFECYCLE = "2.4.1"

  internal const val COMPOSE = "1.3.0-beta01"
  internal const val COMPOSE_COMPILER = "1.3.0"
  internal const val COMPOSE_CONSTRAINT = "1.0.1"
  internal const val COMPOSE_ACTIVITY = "1.4.0"

  internal const val BALLOON = "1.4.7"
  internal const val COLORPICKERVIEW = "2.2.4"
  internal const val POWERSPINNER = "1.2.4"

  internal const val LANDSCAPIST = "1.6.1"
}

object Dependencies {
  const val androidGradlePlugin =
    "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
  const val gradleNexusPublishPlugin =
    "io.github.gradle-nexus:publish-plugin:${Versions.GRADLE_NEXUS_PUBLISH_PLUGIN}"
  const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
  const val spotlessGradlePlugin =
    "com.diffplug.spotless:spotless-plugin-gradle:${Versions.ANDROID_GRADLE_SPOTLESS}"
  const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.KOTLIN_GRADLE_DOKKA}"
  const val kotlinBinaryValidator =
    "org.jetbrains.kotlinx:binary-compatibility-validator:${Versions.KOTLIN_BINARY_VALIDATOR}"

  const val appcompat = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
  const val material = "com.google.android.material:material:${Versions.MATERIAL}"
  const val coreKtx = "androidx.core:core-ktx:${Versions.CORE_KTX}"
  const val lifecycle = "androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE}"

  const val composeUI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
  const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
  const val composeRuntimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}"
  const val composeMaterial = "androidx.compose.material:material:${Versions.COMPOSE}"
  const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
  const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
  const val composeAnimation = "androidx.compose.animation:animation:${Versions.COMPOSE}"
  const val composeConstraint =
    "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE_CONSTRAINT}"
  const val composeActivity = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
  const val landscapist = "com.github.skydoves:landscapist-glide:${Versions.LANDSCAPIST}"

  const val balloon = "com.github.skydoves:balloon:${Versions.BALLOON}"
  const val colorPickerView = "com.github.skydoves:colorpickerview:${Versions.COLORPICKERVIEW}"
  const val powerSpinner = "com.github.skydoves:powerspinner:${Versions.POWERSPINNER}"
}
