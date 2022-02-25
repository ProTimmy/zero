import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("com.android.application")

    id("org.jetbrains.compose")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.zero.android"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha03"
    }
}

tasks.withType<KotlinCompile> {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
}

kotlin {
    android()
    sourceSets {
        val androidAndroidTest by getting {
            dependencies {
                // Testing
                implementation("androidx.compose.ui:ui-test-junit4:1.2.0-alpha02")
            }
        }
        val androidDebug by getting {
            dependencies {
                implementation("androidx.compose.ui:ui-tooling:1.2.0-alpha02")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":common"))
                api(project(":components"))
                api(project(":models"))

                // AppCompat
                implementation("androidx.appcompat:appcompat:1.4.1")

                // Compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
                implementation("androidx.activity:activity-compose:1.5.0-alpha01")

                // Decompose
                implementation("com.arkivanov.decompose:decompose:0.5.1")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.5.1")

                // MVIKotlin
                implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta01")
            }
        }
    }
}
