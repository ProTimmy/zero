import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
	id("com.android.library")

	// iOS
	kotlin("native.cocoapods")
	id("com.chromaticnoise.multiplatform-swiftpackage")
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

	iosX64()
	iosArm64()
	iosSimulatorArm64()

	cocoapods {
		summary = "Some description for the Shared Module"
		homepage = "Link to the Shared Module homepage"
		ios.deploymentTarget = "14.1"
		podfile = project.file("../ios/Podfile")
		framework {
			baseName = "common"
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":models"))

				// Coroutines
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
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
		val iosX64Main by getting
		val iosArm64Main by getting
		val iosSimulatorArm64Main by getting
		val iosMain by creating {
			dependsOn(commonMain)
			iosX64Main.dependsOn(this)
			iosArm64Main.dependsOn(this)
			iosSimulatorArm64Main.dependsOn(this)
		}
		val iosX64Test by getting
		val iosArm64Test by getting
		val iosSimulatorArm64Test by getting
		val iosTest by creating {
			dependsOn(commonTest)
			iosX64Test.dependsOn(this)
			iosArm64Test.dependsOn(this)
			iosSimulatorArm64Test.dependsOn(this)
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

multiplatformSwiftPackage {
	packageName("common")
	swiftToolsVersion("5.3")
	targetPlatforms {
		iOS { v("13") }
	}
}
