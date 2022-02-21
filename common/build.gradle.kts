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

				// Decompose
				export("com.arkivanov.decompose:decompose:0.5.1")

				// MVIKotlin
				export("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
			}
		}
	}
//	iosX64()
//	iosArm64()
//	iosSimulatorArm64()

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":models"))

                // Arrow
                implementation("io.arrow-kt:arrow-core:1.0.1")

				// Coroutines
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

				// Decompose
				implementation("com.arkivanov.decompose:decompose:0.5.1")

				// Reaktive
				implementation("com.badoo.reaktive:reaktive:1.2.1")

				// MVIKotlin
				implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:rx:3.0.0-beta01")
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
//		val iosX64Main by getting
//		val iosArm64Main by getting
//		val iosSimulatorArm64Main by getting
		val iosMain by getting {
			dependencies {
				// Decompose
				api("com.arkivanov.decompose:decompose:0.5.1")

				// MVIKotlin
				api("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
			}

//			iosX64Main.dependsOn(this)
//			iosArm64Main.dependsOn(this)
//			iosSimulatorArm64Main.dependsOn(this)
		}
//		val iosX64Test by getting
//		val iosArm64Test by getting
//		val iosSimulatorArm64Test by getting
		val iosTest by getting {
//			iosX64Test.dependsOn(this)
//			iosArm64Test.dependsOn(this)
//			iosSimulatorArm64Test.dependsOn(this)
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}
