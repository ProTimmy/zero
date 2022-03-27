import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
    kotlin("native.cocoapods")

	id("com.android.library")

    id("kotlin-parcelize")
    id("com.squareup.sqldelight")
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

//	ios {
//		binaries {
//			framework {
//				baseName = "common"
//
//                linkerOpts.add("-lsqlite3")
//
//				// SQLDelight
////				export("com.squareup.sqldelight:native-driver:1.5.3")
//			}
//		}
//	}
    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Common Module"
        homepage = "Link to the Common Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
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
		val androidMain by getting {
		    dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
		val androidTest by getting
        val jsMain by getting
        val jsTest by getting
		val jvmMain by getting {
		    dependencies {
                implementation("com.squareup.sqldelight:sqlite-driver:1.5.3")
            }
        }
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

                // Serialization
                implementation("com.squareup.sqldelight:native-driver:1.5.3")
            }

		}
		val iosTest by getting
        val iosX64Main by getting {
            dependencies {
                dependsOn(iosMain)
            }
        }
        val iosArm64Main by getting {
            dependencies {
                dependsOn(iosMain)
            }
        }
        val iosSimulatorArm64Main by getting {
            dependencies {
                dependsOn(iosMain)
            }
        }
    }
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

sqldelight {
    database("ZeroDatabase") {
        packageName = "com.zero.db"
    }
}
