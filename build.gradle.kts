// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    repositories {
        mavenCentral()

    }
    dependencies {
        // ... other dependencies
        classpath(libs.androidx.navigation.safe.args.gradle.plugin) // Use the latest version
    }
}
