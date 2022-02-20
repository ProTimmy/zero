import org.jetbrains.compose.compose

plugins {
	kotlin("multiplatform")
	id("com.android.library")

	// Compose
	id("org.jetbrains.compose")
}

android {
	compileSdk = 31
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	defaultConfig {
		minSdk = 26
		targetSdk = 31
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
}

kotlin {
	android()
	jvm("desktop")

	sourceSets {
		val commonMain by getting {
			dependencies {
                implementation(project(":common"))
				api(project(":models"))

                // Arrow
                implementation("io.arrow-kt:arrow-core:1.0.1")

                // Compose
				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.ui)
				implementation(compose.material)

                // Decompose
                implementation("com.arkivanov.decompose:decompose:0.5.1")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.5.1")
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}
		val desktopMain by getting
		val desktopTest by getting
		val androidMain by getting
		val androidTest by getting {
			dependencies {
				implementation(kotlin("test-junit"))
				implementation("junit:junit:4.13.2")
			}
		}
	}
}
