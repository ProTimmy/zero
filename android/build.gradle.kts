import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
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

	buildFeatures {
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.2.0-alpha02"
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

dependencies {
	implementation(project(":common"))
	api(project(":components"))
	api(project(":models"))

	// AppCompat
	implementation("androidx.appcompat:appcompat:1.4.1")

    // Arrow
    implementation("io.arrow-kt:arrow-core:1.0.1")

    // Compose
    implementation("androidx.compose.ui:ui:1.2.0-alpha02")
	implementation("androidx.compose.material:material:1.2.0-alpha02")
	implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-alpha02")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
	implementation("androidx.activity:activity-compose:1.5.0-alpha01")
	debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-alpha02")

    // Decompose
    implementation("com.arkivanov.decompose:decompose:0.5.1")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.5.1")

    // MVIKotlin
    implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
    implementation("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta01")
    implementation("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta01")
    implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta01")

    // Testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-alpha02")
}
