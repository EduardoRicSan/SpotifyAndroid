// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        //   classpath(libs.google.service)
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.dagger.hilt.agp)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.devtools.ksp") version "2.1.20-2.0.1" apply false
}