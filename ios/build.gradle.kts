plugins {
    id("org.jetbrains.gradle.apple.applePlugin") version "212.4638.14-0.14"
    kotlin("multiplatform")
}

apple {
    iosApp {
        productName = "zero"

        //productInfo["NSAppTransportSecurity"] = mapOf("NSAllowsArbitraryLoads" to true)
        //buildSettings.OTHER_LDFLAGS("")

        dependencies {
            implementation(project(":common"))
            implementation(project(":models"))
        }
    }
}
