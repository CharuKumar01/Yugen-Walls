plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}
buildscript {

    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com/")
    }
    dependencies {
        classpath(libs.google.services)
        classpath(libs.gradle)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
    }
}