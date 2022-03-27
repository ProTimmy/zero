import com.diffplug.gradle.spotless.SpotlessExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jlleitschuh.gradle.ktlint.KtlintExtension

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.2.0-alpha07")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
    }
}

plugins {
    kotlin("jvm") version "1.6.10" apply false // iOS

    // Compose
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev620"

    // Gradle
    id("com.github.ben-manes.versions") version "0.42.0" // TODO: Configure auto-reporting in Gradle build
    id("org.owasp.dependencycheck") version "6.5.3" // TODO: Configure auto-reporting in Gradle build

    // Documentation
    id("org.jetbrains.dokka") version "1.6.10" // TODO: Configure auto-reporting in Gradle build

    // Lint and code style
    id("com.diffplug.spotless") version "6.2.2" // TODO: Configure auto-reporting in Gradle build
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"

    // Unit Testing
    id("org.jetbrains.kotlinx.kover") version "0.5.0" // TODO: Configure auto-reporting in Gradle build
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.dokka")
        plugin("com.diffplug.spotless")
    }

    afterEvaluate {
        tasks.named("check").configure {
            dependsOn(tasks.getByName("spotlessCheck"))
        }
    }
}

// Generate Reports
tasks.register("generateReports") {
    dependsOn(tasks.getByName("dependencyUpdates")) // Plugin Versions
    dependsOn(tasks.getByName("dependencyCheckAggregate")) // OWASP Dependency Vulnerability
    dependsOn(tasks.getByName("dokkaHtmlMultiModule")) // Dokka
    dependsOn(tasks.getByName("koverMergedHtmlReport")) // Kover
}

// Kover
kover {
    coverageEngine.set(kotlinx.kover.api.CoverageEngine.INTELLIJ)
    disabledProjects = setOf(
        // "android",
    )
    generateReportOnCheck = false
}

tasks.koverMergedHtmlReport {
    isEnabled = true // false to disable report generation
    htmlReportDir.set(buildDir.resolve("kover"))

    // includes = listOf("com.zero.*") // inclusion rules for classes
    excludes = listOf("com.zero.zero.*") // exclusion rules for classes
}

// Spotless
// TODO: Set up spotlessApply with Git Hooks
configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")

        ktfmt()
        ktlint()

        // Rules
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

// ktlint
configure<KtlintExtension> {
    debug.set(true)
    enableExperimentalRules.set(true)
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(false)
    disabledRules.set(
        setOf(
            // Insert rules here
        )
    )

    filter {
        exclude { it.file.path.contains("build/") }
    }
}

// Gradle Versions Plugin
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    gradleReleaseChannel = "current"

    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "versionUpdateReport"

    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version)) {
                    reject("Release candidate")
                }
            }
        }
    }
}

// OWASP Dependency Check
dependencyCheck {
    outputDirectory = buildDir.resolve("owasp").toString()
}

// Dokka
tasks.withType<DokkaTask>().configureEach {
    outputDirectory.set(buildDir.resolve("dokka"))
}

rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class) {
    rootProject.the(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension::class).nodeVersion = "16.0.0"
}
