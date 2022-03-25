import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	kotlin("plugin.serialization") version "1.6.10"

	kotlin("multiplatform")
	id("com.android.library")
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

	ios()

	sourceSets {
		val commonMain by getting {
			dependencies {
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
//		val iosX64Main by getting
//		val iosArm64Main by getting
//		val iosSimulatorArm64Main by getting
		val iosMain by getting {
//			dependsOn(commonMain)
//			iosX64Main.dependsOn(this)
//			iosArm64Main.dependsOn(this)
//			iosSimulatorArm64Main.dependsOn(this)
		}
//		val iosX64Test by getting
//		val iosArm64Test by getting
//		val iosSimulatorArm64Test by getting
		val iosTest by getting {
//			dependsOn(commonTest)
//			iosX64Test.dependsOn(this)
//			iosArm64Test.dependsOn(this)
//			iosSimulatorArm64Test.dependsOn(this)
		}
	}
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}
