import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

kotlin {
	jvm {
		withJava()
	}

	sourceSets {
		val jvmMain by getting {
			dependencies {
				implementation(compose.desktop.currentOs)

				implementation(project(":common"))
				api(project(":models"))
				api(project(":components"))

                // Decompose
                implementation("com.arkivanov.decompose:decompose:0.5.2")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.5.2")

                // MVIKotlin
                implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta01")
                implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta01")

                // Reaktive
                implementation("com.badoo.reaktive:reaktive:1.2.1")
                implementation("com.badoo.reaktive:coroutines-interop:1.2.1")
			}
		}
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

compose.desktop {
	application {
		mainClass = "com.zero.desktop.MainKt"
	}
}
