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

// compose.experimental {
//     web.application {}
//     uikit.application {}
// }

kotlin {
	android()
	jvm("desktop")

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":models"))

				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.ui)
				implementation(compose.material)
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
