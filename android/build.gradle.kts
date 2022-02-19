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

dependencies {
	implementation(project(":common"))
	api(project(":components"))
	api(project(":models"))

	// AppCompat
	implementation("androidx.appcompat:appcompat:1.4.1")

	// Compose
	implementation("androidx.compose.ui:ui:1.2.0-alpha02")
	implementation("androidx.compose.material:material:1.2.0-alpha02")
	implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-alpha02")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
	implementation("androidx.activity:activity-compose:1.5.0-alpha01")
	debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-alpha02")

    // Decompose
    implementation("com.arkivanov.decompose:decompose:0.5.1")

	// Testing
	androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-alpha02")
}
