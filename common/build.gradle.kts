import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
	id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

android {
	compileSdk = 31
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	defaultConfig {
		minSdk = 26
		targetSdk = 31
	}
}

kotlin {
	android()
	jvm()

	js(IR) {
		useCommonJs()
		browser {
			commonWebpackConfig {
				cssSupport.enabled = true
			}
		}
	}

	ios {
		binaries {
			framework {
				baseName = "common"
				transitiveExport = true
				isStatic = true

				export(project(":models"))

				// Decompose
				export("com.arkivanov.decompose:decompose:0.5.2")

				// MVIKotlin
				export("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
				export("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta01")
				export("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta01")
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":models"))

				// Coroutines
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

				// Decompose
				implementation("com.arkivanov.decompose:decompose:0.5.2")

				// Reaktive
				implementation("com.badoo.reaktive:reaktive:1.2.1")

				// MVIKotlin
				implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:3.0.0-beta01")
				implementation("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta01")
				implementation("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta01")
				implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta01")
				implementation("com.arkivanov.mvikotlin:rx:3.0.0-beta01")

                // UUID
                implementation("com.benasher44:uuid:0.4.0")
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))

				// Coroutines
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
			}
		}
		val androidMain by getting
		val androidTest by getting
		val jvmMain by getting
		val jvmTest by getting
		val iosMain by getting {
			dependencies {
				api(project(":models"))

				// Decompose
				api("com.arkivanov.decompose:decompose:0.5.2")

				// MVIKotlin
				api("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
				api("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta01")
				api("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta01")
			}
		}
		val iosTest by getting
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}
