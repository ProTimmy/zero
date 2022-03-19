plugins {
    id("org.jetbrains.gradle.apple.applePlugin") version "212.4638.14-0.15"
}

version = "1.0"

apple {
    iosApp {
        productName = "zero"

        // Necessary to have app launch in full screen
        launchStoryboard = ""

        dependencies {
            implementation(project(":common"))
        }
    }
}
