import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	kotlin("plugin.serialization") version "1.6.10"

	kotlin("multiplatform")
	id("com.android.library")

	kotlin("native.cocoapods")
}

version = "1.0"

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
	jvm()
	js(IR) {
		useCommonJs()
		browser {
			commonWebpackConfig {
				cssSupport.enabled = true
			}
		}
	}

	val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
		when {
			System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
			System.getenv("NATIVE_ARCH")
				?.startsWith("arm") == true -> ::iosSimulatorArm64 // available to KT 1.5.30
			else -> ::iosX64
		}
	iosTarget("ios") {}

	cocoapods {
		summary = "Some description for the Models Module"
		homepage = "Link to the Models Module homepage"
		ios.deploymentTarget = "14.1"
		podfile = project.file("../ios/Podfile")
		framework {
			baseName = "models"
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation("io.arrow-kt:arrow-core:1.0.1")

				implementation("com.benasher44:uuid:0.4.0")

				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}
		val jvmMain by getting
		val jvmTest by getting
		val jsMain by getting
		val jsTest by getting
		val iosMain by getting
		val iosTest by getting
	}
}
