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
			}
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

compose.desktop {
	application {
		mainClass = "com.zero.desktop.MainKt"
	}
}
